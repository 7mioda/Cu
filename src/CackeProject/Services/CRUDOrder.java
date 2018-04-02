package CackeProject.Services;

import CackeProject.Entities.Order;
import CackeProject.Utils.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Manipulation of Orders
 */

public class CRUDOrder {

    public void addOrder(Order order ) {

        try {
            String query="INSERT INTO CapCake.Orders(costumer,order_date) values(?,?)";
            PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1, order.getUser().getId());
            statement.setDate(2, order.getOrder_date());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(int id){

        try {
            String query="DELETE FROM CapCake.Order where id = ?";
            PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateOrder(Order order,int id){
        try {
            String query="UPDATE CapCake.Orders SET costumer=? , order_date=? where id=? ";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1, order.getUser().getId());
            statement.setDate(2, order.getOrder_date());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> showOrder(){
        List<Order> myList = new ArrayList<>();
        CRUDUser crudUser = new CRUDUser();
        CRUDOrderLine crudOrderLine = new CRUDOrderLine();
        try {
            String query = "SELECT * FROM CapCake.Orders";
            Statement statement= (PreparedStatement) DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Order order = new Order();
                order.setId(result.getInt(0));
                order.setUser(crudUser.showUser(result.getInt(2)));
                order.setOrders(crudOrderLine.showOrderLine());
                myList.add(order);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }


    public Order showOrder(int id){
        Order order = new Order();
        CRUDUser crudUser = new CRUDUser();
        CRUDOrderLine crudOrderLine = new CRUDOrderLine();
        try {
            String query = "SELECT * FROM Orders WHERE id =? ";
            PreparedStatement statement= (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
             if(result.next()){
                 order.setId(result.getInt(1));
                 order.setUser(crudUser.showUser(result.getInt(2)));
                 order.setOrders(crudOrderLine.showOrderLineByOredr(order));
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

}
