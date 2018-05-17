package CackeProject.Services;
import CackeProject.Entities.hystorique;
import CackeProject.Utils.DataBase;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;





public class CRUDhystorique {
    
   
    
     public Connection con = DataBase.getInstance().getCnx();
     private Statement ste;
     hystorique user = new hystorique();
    
     
    public CRUDhystorique() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDhystorique.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
           
           //INSERT INTO `hystorique`(`userid`, `dateAjout`, `Bancount`, `releasetime`) VALUES (?,?,?,?)
     public void addHystorique(hystorique user) {
            try {
                String query = "INSERT INTO `historique` (`userid`) VALUES (?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setInt(1, user.getUserid());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }
    
     public void deleteHystorique(int id){
        String query="DELETE FROM `historique` WHERE `userid` = ?";
        try {
        PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

//`hystorique`(`userid`, `dateAjout`, `Bancount`, `releasetime`) VALUES (?,?,?,?)
 public void updateHystorique(hystorique hist,int id){

        try {
            Date temp = hist.getReleasedate();
            temp.setDate(hist.getReleasedate().getDay()+hist.getBancount()+1);
            String query="UPDATE `historique` SET `Bancount` = '?', `releasetime` = '"
                    +temp.toString()
                    +hist.getReleaseTime()+"' WHERE `historique`.`userid` = ?;";
            
        PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setInt(1, hist.getBancount()+1);
        statement.setInt(2, hist.getUserid());
        
        
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } // String query = "SELECT * FROM `historique` WHERE `userid`= \"" + hist.getId()+ "\" ";
  public hystorique getHistoryById(int id) throws SQLException{
      hystorique hist=null;
      
     // String query = "SELECT * FROM `historique` WHERE `userid`= \"" +id+ "\" ";
       String query = "SELECT * FROM `historique` WHERE `userid`= \"" +id+ "\" ";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                    hist = new hystorique(result.getInt(1),result.getString(2), result.getInt(3),result.getString(4)) ;
                      }
            return hist;
  }
 
}


/*
    public User showUser(int id){
        User user = new User();
        try {
            String query = "SELECT * FROM User WHERE id =?";
            PreparedStatement statement=  DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setSurname(result.getString(3));
                user.setAdress(result.getString(4));
                user.setPhoneNum(result.getString(5));
                user.setEmail(result.getString(6));
                user.setUsername(result.getString(9));
                user.setPassword(result.getString(10));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
*/