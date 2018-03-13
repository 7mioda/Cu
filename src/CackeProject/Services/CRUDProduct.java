package CackeProject.Services;

import CackeProject.Entities.Product;
import CackeProject.Utils.DataBase;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Manipulation of Users
 */

public class CRUDProduct {

    /**
     * Add a Product
     * @param product to Add
     */
    public void addProduct(Product product) {
        String query="INSERT INTO Product(id,price,quantity) values(?,?,?)";

        try {
            PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setObject(1, UUID.randomUUID());
            statement.setDouble(2, product.getPrice());
            statement.setFloat(3,product.getQuantity());

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
            String query="DELETE FROM Product where id = ?";
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
        String query="UPDATE Product SET price=? , quantity=? where id=? ";
        try {
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setString(1, product.getPrice());
            statement.setString(2, product.getQuantity());
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
            String query = "SELECT * FROM Product";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Product product = new Product();
                product.setPrice(result.getString(2));
                product.setQuantity(result.getString(3));
                myList.add(product);
            }
            return myList;
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
    public Product showProduct(String id){
        String query = "SELECT * FROM Product WHERE id = ' ? '";
        try {
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setString(1,id);
            ResultSet result = statement.executeQuery(query);
            Product product = new Product();
            product.setPrice(result.getString(2));
            product.setQuantity(result.getString(3));
            return product;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
