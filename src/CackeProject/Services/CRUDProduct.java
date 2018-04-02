package CackeProject.Services;

import CackeProject.Entities.Product;
import CackeProject.Utils.DataBase;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manipulation of Users
 */

public class CRUDProduct {

    /**
     * Add a Product
     * @param product to Add
     */
    public void addProduct(Product product) {
        String query="INSERT INTO CapCake.Product (price,quantity,name,category) values(?,?,?,?)";

        try {
            PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setDouble(1, product.getPrice());
            statement.setFloat(2,product.getQuantity());
            statement.setString(3,product.getName());
            statement.setObject(4, product.getCategory().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete Product
     * @param id Product id to Delete
     */
    public void deleteProduct(String id){
        try {
            String query="DELETE FROM CapCake.Product where id = ?";
            PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setString(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update a product
     * @param product to ADD
     * @param id of product to Update
     */
    public void updateProduct(Product product,String id){
        String query="UPDATE CapCake.Product SET price=? , quantity=? where id=? ";
        try {
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setDouble(1, product.getPrice());
            statement.setFloat(2, product.getQuantity());
            statement.setString(3,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show all Products
     * @return List of Products
     */
    public List<Product> showProduct(){
        List<Product> myList = new ArrayList<>();
        try {
            String query = "SELECT * FROM CapCake.Product";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Product product = new Product();
                product.setPrice(result.getDouble(2));
                product.setQuantity(result.getFloat(3));
                myList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return myList;
    }

    /**
     * Show a specific Product
     * @param id Product id to show
     * @return
     */
    public Product showProduct(int id){
        Product product = new Product();
        try {
            String query = "SELECT * FROM Product WHERE id= ? ";
            PreparedStatement statement =  DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            product.setId(result.getInt(1));
            product.setPrice(result.getDouble(3));
            product.setQuantity(result.getFloat(4));
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

}
