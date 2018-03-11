package CackeProject.Services;

import CackeProject.Utils.MyConnection;
import CackeProject.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class CRUDUSER {

    public void addUser(User user) {
        String requete="INSERT INTO USER(nom,prenom) values(?,?)";
        PreparedStatement pst=(PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, user.getName());
        pst.setString(2,user.getSurname());
        pst.executeUpdate();
    }
    public void deleteUser(int id){
        String requete="DELETE FROM USER where id = ?";
        PreparedStatement pst=(PreparedStatement) MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1,id);
        pst.executeUpdate();
    }
    public void updateUser(){
        String requete="UPDATE USER SET nom=? where id=? ";
        PreparedStatement pst= MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(2, 3);
        pst.setString(1, pwd);
        pst.executeUpdate();
    }
    public void showUser(){
        String requete = "SELECT*FROM USER";
        Statement st=MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs= st.executeQuery(requete);
        while(rs.next()){
            System.out.println("ID:"+rs.getInt(1));
            System.out.println("Nom:"+rs.getString(2));
            System.out.println("Prenom:"+rs.getString("prenom"));
            System.out.println("*****");
        }
    }

}
