package CackeProject.Services;

import CackeProject.Entities.Confectioner;
import CackeProject.Entities.Customer;
import CackeProject.Entities.User;
import CackeProject.Utils.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Manipulation of Users
 */

public class CRUDNote {
    /**
     * Add User
     * @param user User to add
     */
    public void addUser(User user) {

        if(user.getClass().getName().equals("CackeProject.Entities.Confectioner")) {
            try {
                String query = "INSERT INTO CapCake.User (name,surname,adresse,phone,email,username,password,taxregnum,role) values(?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getAdress());
                statement.setString(4, user.getPhoneNum());
                statement.setString(5, user.getEmail());
                statement.setString(6, user.getUsername());
                statement.setString(7, user.getPassword());
                statement.setString(8,((Confectioner) user).getTaxregnum());
                statement.setString(9, Confectioner.getRole());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if(user.getClass().getName().equals("CackeProject.Entities.Customer")){
            try {
                String query = "INSERT INTO CapCake.User (name,surname,adresse,phone,email,username,password,role) values(?,?,?,?,?,?,?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getAdress());
                statement.setString(4, user.getPhoneNum());
                statement.setString(5, user.getEmail());
                statement.setString(6, user.getUsername());
                statement.setString(7, user.getPassword());
                statement.setString(8, Customer.getRole());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            try {
                String query = "INSERT INTO CapCake.User (name,surname,adresse,phone,email,username,password,role) values(?,?,?,?,?,?,?,'user')";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getAdress());
                statement.setString(4, user.getPhoneNum());
                statement.setString(5, user.getEmail());
                statement.setString(6, user.getUsername());
                statement.setString(7, user.getPassword());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Delete User
     * @param id User id to Delete
     */
    public void deleteUser(int id){
        String query="DELETE FROM CapCake.User where id = ?";
        try {
        PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setInt(1,id);
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

        try {
            String query="UPDATE CapCake.User SET name=? , surname=? ,adresse=? , phone=?, email=? , username=? where id=? ";
        PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setString(3,user.getAdress());
        statement.setString(4,user.getPhoneNum());
        statement.setString(5,user.getEmail());
        statement.setString(6,user.getUsername());
        statement.setString(7,user.getPassword());
        statement.setString(8,id);
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
