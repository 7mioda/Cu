package CackeProject.Entities;

import java.util.List;

/**
 * Costumer Orders
 */
public class Order {


    private List<OrderLine> Orders;

    public Order(List<OrderLine> orders) {
        Orders = orders;
    }

    public List<OrderLine> getOrders() {
        return Orders;
    }

    public void setOrders(List<OrderLine> orders) {
        Orders = orders;
    }
}
