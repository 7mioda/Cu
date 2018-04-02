package CackeProject.Entities;

import java.util.List;

public class Recipe {
    private List<Ingredient> ingredients;
    private Product product;
    private int id;
    private float quantity;

    public Recipe() {

    }
    public Recipe(List<Ingredient> ingredients, Product product, int id, float quantity) {
        this.ingredients = ingredients;
        this.product = product;
        this.id = id;
        this.quantity = quantity;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }
}
