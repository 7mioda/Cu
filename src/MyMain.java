import CackeProject.Entities.*;
import CackeProject.Services.*;


public class MyMain {
    public static void main(String[] args) {

        /*CRUDProduct crudProduct = new CRUDProduct();
        CRUDOrder crudOrder = new CRUDOrder();
        Order order = new Order();
        CRUDOrderLine crudOrderLine = new CRUDOrderLine();
        CRUDUser crudUser = new CRUDUser();
        User user = crudUser.showUser().stream().findFirst().get();
        Product product = crudProduct.showProduct().stream().findFirst().get();
        order = crudOrder.showOrder(1);
        order.setUser(user);
        crudOrder.addOrder(order);
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(10);
        orderLine.setOrder(order);
        orderLine.setProduct(product);
        crudOrderLine.addOrderLine(orderLine);*/
        CRUDProduct crudProduct = new CRUDProduct();
        CRUDOrder crudOrder = new CRUDOrder();
        CRUDUser crudUser = new CRUDUser();
        CRUDOrderLine crudOrderLine = new CRUDOrderLine();
        CRUDCategory crudCategory = new CRUDCategory();
        CRUDIngredient crudIngredient = new CRUDIngredient();

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(10);
        orderLine.setProduct(crudProduct.showProduct(14));
        orderLine.setOrder(crudOrder.showOrder(33));
        crudOrderLine.addOrderLine(orderLine);











    }
}
