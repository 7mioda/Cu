package CackeProject.Services;
import CackeProject.Utils.DataBase;
import CackeProject.Entities.User;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manipulation of Users
 */

public class CRUDUser {
    /**
     * Add User
     * @param user User to add
     */
    public void addUser(User user) {

            try {
                String query = "INSERT INTO CupCake.User (name,surname,adresse,phone,email,username,password,taxregnum,role,state) values(?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getAdress());
                statement.setString(4, user.getPhoneNum());
                statement.setString(5, user.getEmail());
                statement.setString(6, user.getUsername());
                statement.setString(7, user.getPassword());
                statement.setString(8, user.getTaxeregnum());
                statement.setString(9, user.getRole());
                statement.setInt(10, user.getState());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    /**
     * Delete User
     * @param id User id to Delete
     */
    public void deleteUser(int id){
        String query="DELETE FROM CupCake.User where id = ?";
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
    public void updateUser(User user,int id){

        try {
            String query="UPDATE CupCake.User SET name=? , surname=? ,adresse=? , phone=?, email=? , username=? where id=? ";
        PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setString(1, user.getName());
        statement.setString(2, user.getSurname());
        statement.setString(3,user.getAdress());
        statement.setString(4,user.getPhoneNum());
        statement.setString(5,user.getEmail());
        statement.setString(6,user.getUsername());
        statement.setInt(7,id);
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
            String query = "SELECT * FROM CupCake.User";
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
                user.setTaxeregnum(result.getString(7));
                user.setUsername(result.getString(9));
                user.setPassword(result.getString(10));
                user.setRole(result.getString(8));
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
            String query = "SELECT * FROM CupCake.User WHERE id =?";
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
                user.setTaxeregnum(result.getString(7));
                user.setUsername(result.getString(9));
                user.setPassword(result.getString(10));
                user.setRole(result.getString(8));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void BanUser(User user) throws SQLException {
        String req = "UPDATE User SET State=1 where id=? ";
        PreparedStatement pre = DataBase.getInstance().getCnx().prepareStatement(req);
        pre.setInt(1,user.getId());
        pre.executeUpdate();
        System.out.println("User est banné avec succee il ne peut pas se connecter !");
    }
    public void IgnoreBanUser(User user) throws SQLException {
        String req = "UPDATE User SET State=0 where id=? ";
        PreparedStatement pre = DataBase.getInstance().getCnx().prepareStatement(req);
        pre.setInt(1,user.getId());
        pre.executeUpdate();
        System.out.println("User  banne annulé avec succee il  peut  se connecter !");
    }

}
