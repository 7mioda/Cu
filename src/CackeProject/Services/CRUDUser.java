package CackeProject.Services;

import CackeProject.Utils.DataBase;
import CackeProject.Entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;


public class CRUDUser {
    /**
     *
     * @param user
     */
    public void addUser(User user) {
        String query="INSERT INTO User(id,name,surname) values(?,?,?)";

        try {
        PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setObject(1, UUID.randomUUID());
        statement.setString(2, user.getName());
        statement.setString(3,user.getSurname());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     */
    public void deleteUser(String id){
        String query="DELETE FROM User where id = ?";
        try {
        PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setString(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateUser(){
        String query="UPDATE USER SET nom=? where id=? ";
        try {
        PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setInt(2, 3);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public ResultSet showUser(){
        String query = "SELECT*FROM USER";
        try {
        Statement statement= DataBase.getInstance().getCnx().createStatement();
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
         return null;
    }

    public ResultSet showUser(String id){
        String query = "SELECT * FROM User WHERE id = ' ? '";
        try {
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setString(1,id);
            return statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
