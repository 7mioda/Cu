package CackeProject.Entities;

public class Offre {
    private int id;
    private Product Product;
    private double Price;
    private double Old_price;

    public double getOld_price() {
        return Old_price;
    }

    public void setOld_price(double old_price) {
        Old_price = old_price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private String Description;
    private float Percentage;

    public Offre() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CackeProject.Entities.Product getProduct() {

        return Product;
    }

    public void setProduct(CackeProject.Entities.Product product) {

        Product = product;
    }

    public double getPrice() {

        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public float getPercentage() {

        return Percentage;
    }

    public void setPercentage(float percentage) {

        Percentage = percentage;
    }
}
