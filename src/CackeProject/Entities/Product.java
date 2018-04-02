package CackeProject.Entities;


public class Product {
    private Category Category;
    private Recipe Recipe;
    private int id;
    private String name;
    private double Price;
    private float Quantity;

    public Product(){

    }

    public Product(CackeProject.Entities.Category category, CackeProject.Entities.Recipe recipe, String name, double price, float quantity) {
        Category = category;
        Recipe = recipe;
        this.name = name;
        Price = price;
        Quantity = quantity;
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

    public CackeProject.Entities.Recipe getRecipe() {
        return Recipe;
    }

    public void setRecipe(CackeProject.Entities.Recipe recipe) {
        Recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (Double.compare(product.getPrice(), getPrice()) != 0) return false;
        if (Float.compare(product.getQuantity(), getQuantity()) != 0) return false;
        return getCategory().equals(product.getCategory());
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

