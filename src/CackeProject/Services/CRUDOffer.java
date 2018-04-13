package CackeProject.Services;


import CackeProject.Entities.Offre;
import CackeProject.Entities.User;
import CackeProject.Utils.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CRUDOffer {

    public void addOffer(Offre offer) {
            try {
                String query = "INSERT INTO CapCake.Offer (percentage, product, price) values(?,?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setFloat(1, offer.getPercentage());
                statement.setInt(2, offer.getProduct().getId());
                statement.setDouble(3, offer.getPrice());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    public void deleteOffer(int id){
        try {
            String query="DELETE FROM CapCake.Offer where id = ?";
        PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateOffer(Offre offer,int id){

        try {
            String query="UPDATE CapCake.Offer SET percentage=? , product=? ,price=? where id=? ";
        PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setFloat(1, offer.getPercentage());
        statement.setInt(2, offer.getProduct().getId());
        statement.setDouble(3,offer.getPrice());
        statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Offre> showOffer(){
        List<User> myList = new ArrayList<>();
        try {
            String query = "SELECT * FROM CapCake.User";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                User user = new User();
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setSurname(result.getString(3));
                user.setAdress(result.getString(4));
                user.setPhoneNum(result.getString(5));
                user.setEmail(result.getString(6));
                user.setUsername(result.getString(7));
                user.setPassword(result.getString(8));
                myList.add(user);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }

    /**
     * Show a specific User
     * @param id User id to show
     * @return
    */
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
                user.setUsername(result.getString(7));
                user.setPassword(result.getString(8));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
