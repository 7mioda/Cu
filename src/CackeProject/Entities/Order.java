package CackeProject.Entities;

import java.sql.Date;
import java.util.List;

/**
 * Costumer Orders
 */
public class Order {


    private List<OrderLine> Orders;
    private User user;
    private int id;
    private Date order_date;

    public Order(){

    }

    public Order(List<OrderLine> orders) {
        Orders = orders;
    }

    public List<OrderLine> getOrders() {
        return Orders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setOrders(List<OrderLine> orders) {
        Orders = orders;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }
}
