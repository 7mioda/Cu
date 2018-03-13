package CackeProject.Services;

import CackeProject.Utils.DataBase;
import CackeProject.Entities.User;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Manipulation of Users
 */

public class CRUDUser {
    /**
     * Add User
     * @param user User to add
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
     * Delete User
     * @param id User id to Delete
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

    /**
     * Update a User
     * @param user New User
     * @param id User id to Update
     */
    public void updateUser(User user,String id){
        String query="UPDATE User SET name=? , surname=? where id=? ";
        try {
        PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setString(3,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show all Users
     * @return List of Users
     */
    public List<User> showUser(){
        List<User> myList = new ArrayList<>();
        try {
            String query = "SELECT * FROM User";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                User user = new User();
                user.setName(result.getString(2));
                user.setSurname(result.getString(3));
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
