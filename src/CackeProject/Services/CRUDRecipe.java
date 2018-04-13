package CackeProject.Services;


import CackeProject.Entities.Category;
import CackeProject.Entities.Ingredient;
import CackeProject.Entities.Product;
import CackeProject.Entities.Recipe;
import CackeProject.Utils.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CRUDRecipe {

    public void addRecipe(Recipe recipe) {

        try {
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                String query = "INSERT INTO CapCake.Recipe(product,ingredient) values(?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setInt(1, recipe.getProduct().getId());
                statement.setInt(2, recipe.getIngredients().get(i).getId());
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteRecipe(int id){
        try {
            String query="DELETE FROM CapCake.Recipe where id = ?";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateRecipe(Recipe recipe,int idProduct){
        try {
            for (int i = 0; i < recipe.getIngredients().size(); i++) {
                String query="UPDATE CapCake.Recipe SET ingredient=? where product=?";
                PreparedStatement statement =  DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setInt(1, recipe.getIngredients().get(i).getId());
                statement.setInt(1, idProduct);
                statement.executeUpdate();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }



    public Recipe showRecipe(Product product){
        Recipe recipe = new Recipe();
        CRUDIngredient crudIngredient = new CRUDIngredient();
        try {
            String query = "SELECT * FROM CapCake.Recipe WHERE product = ?";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,product.getId());
            ResultSet result = statement.executeQuery();
            recipe.setId(result.getInt(1));
            recipe.setProduct(product);
            while(result.next()){
                Ingredient ingredient = crudIngredient.showingredient(result.getInt(2));
                recipe.getIngredients().add(ingredient);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return recipe;
    }
}
