package CackeProject.Services;

import CackeProject.Entities.Category;
import CackeProject.Entities.Unit;
import CackeProject.Utils.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 * Manipulation of Orders
 */

public class CRUDCategory {

    public void addCategory(Category category) {

        String query="INSERT INTO CapCake.Category(designation,unit) values(?,?)";
        try {
            PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setString(1, category.getDesignation());
            statement.setString(2, category.getUnit());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(int id){
        String query="DELETE FROM CapCake.Category where id = ?";
        try {
            PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateCategory(Category category,int id){
        String query="UPDATE CapCake.Category SET designation=? , unit=? where id=? ";
        try {
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setString(1, category.getDesignation());
            statement.setString(2, category.getUnit());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Category> showCategory(){
        List<Category> myList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Category";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Category category = new Category();
                category.setId(result.getInt(1));
                category.setDesignation(result.getString(2));
                category.setUnit(result.getString(3));
                myList.add(category);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }


    public Category showCategory(String id){
        Category category = new Category();
        try {
            String query = "SELECT * FROM CapCake.Category WHERE id = ' ? '";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setString(1,id);
            ResultSet result = statement.executeQuery(query);
            category.setDesignation(result.getString(1));
            category.setUnit(result.getString(2));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

}
