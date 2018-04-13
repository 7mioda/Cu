package CackeProject.Entities;

public class Offre {
    private int id;
    private Product Product;
    private double Price;
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
