package CackeProject.Entities;

import java.sql.Date;
import java.util.List;


public class FlashSale {
    private int id;
    private List<Product> Product;
    private double Price;
    private String Description;
    private String State;
    private Date Date;
    private Date EndDate;


    public FlashSale() {
    }

    public FlashSale(int id, List<CackeProject.Entities.Product> product, double price, String description, String state, java.sql.Date date, java.sql.Date endDate) {
        this.id = id;
        Product = product;
        Price = price;
        Description = description;
        State = state;
        Date = date;
        EndDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CackeProject.Entities.Product> getProduct() {
        return Product;
    }

    public void setProduct(List<CackeProject.Entities.Product> product) {
        Product = product;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public java.sql.Date getDate() {
        return Date;
    }

    public void setDate(java.sql.Date date) {
        Date = date;
    }

    public java.sql.Date getEndDate() {
        return EndDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        EndDate = endDate;
    }
}
