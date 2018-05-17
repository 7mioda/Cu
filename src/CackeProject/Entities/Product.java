package CackeProject.Entities;


public class Product {
    private Category Category;
    private String Recipe;
    private int id;
    private String name;
    private double Price;
    private float Quantity;

    public Product(){

    }


    public CackeProject.Entities.Category getCategory() {
        return Category;
    }

    public void setCategory(CackeProject.Entities.Category category) {
        Category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public float getQuantity() {
        return Quantity;
    }

    public void setQuantity(float quantity) {
        Quantity = quantity;
    }

    public String getRecipe() {
        return Recipe;
    }

    public void setRecipe(String recipe) {
        Recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object p) {
        Product product = (Product) p;
        return  product.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getCategory().hashCode();
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getQuantity() != +0.0f ? Float.floatToIntBits(getQuantity()) : 0);
        return result;
    }
}

