package CackeProject.Services;

import CackeProject.Entities.Ingredient;
import CackeProject.Utils.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Manipulation of ingredients
 */

public class CRUDIngredient {
    /**
     * Add ingredient
     * @param ingredient ingredient to add
     */
    public void addIngredient(Ingredient ingredient) {
        String query="INSERT INTO CapCake.Ingredient(name,description) values(?,?)";

        try {
        PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setString(1, ingredient.getName());
        statement.setString(2, ingredient.getDescription());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete ingredient
     * @param id ingredient id to Delete
     */
    public void deleteIngredient(String id){

        try {
        String query="DELETE FROM CapCake.Ingredient where id = ?";
        PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setString(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Update a ingredient
     * @param ingredient New ingredient
     * @param id ingredient id to Update
     */
    public void updateingredient(Ingredient ingredient,int id){
        try {
        String query="UPDATE CapCake.Ingredient SET name=? , description=? where id=? ";
        PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setString(1, ingredient.getName());
        statement.setString(2, ingredient.getDescription());
        statement.setInt(3,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Show all ingredients
     * @return List of ingredients
     */
    public List<Ingredient> showingredient(){
        List<Ingredient> myList = new ArrayList<>();
        try {
            String query = "SELECT * FROM CapCake.Ingredient";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Ingredient ingredient = new Ingredient();
                ingredient.setId(result.getInt(1));
                ingredient.setName(result.getString(2));
                ingredient.setDescription(result.getString(3));
                myList.add(ingredient);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }

    /**
     * Show a specific ingredient
     * @param id ingredient id to show
     * @return
     */
    public Ingredient showingredient(int id){
        Ingredient ingredient = new Ingredient();
        try {
            String query = "SELECT * FROM CapCake.Ingredient WHERE id = ? ";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            ingredient.setId(result.getInt(1));
            ingredient.setName(result.getString(2));
            ingredient.setDescription(result.getString(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingredient;
    }

}
