package CackeProject.Services;


import CackeProject.Entities.Order;
import CackeProject.Entities.OrderLine;
import CackeProject.Utils.DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boukla7mid on 02/04/18.
 */
public class CRUDOrderLine {
    public void addOrderLine(OrderLine orderLine ) {

        try {
                String query ="INSERT INTO CapCake.OrderLine (quantity, product, orders) values(?,?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setDouble(1, orderLine.getQuantity());
                statement.setInt(2, orderLine.getProduct().getId());
                statement.setInt(3,orderLine.getOrder().getId());
                statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCategory(int id){

        try {
            String query="DELETE FROM CapCake.OrderLine where id = ?";
            PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void updateOrderLine(OrderLine orderLine , int id){

        try {
            String query="UPDATE CapCake.OrderLine SET quantity=? , product=? , orders=? where id=? ";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setDouble(1, orderLine.getQuantity());
            statement.setInt(2, orderLine.getProduct().getId());
            statement.setInt(3, orderLine.getOrder().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<OrderLine> showOrderLine(){
        List<OrderLine> myList = new ArrayList<>();
        CRUDProduct crudProduct = new CRUDProduct();
        CRUDOrder crudOrder = new CRUDOrder();
        try {
            String query = "SELECT * FROM CapCake.OrderLine";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                OrderLine orderLine = new OrderLine();
                orderLine.setId(result.getInt(1));
                orderLine.setProduct(crudProduct.showProduct(result.getInt(2)));
                orderLine.setOrder(crudOrder.showOrder(result.getInt(3)));
                myList.add(orderLine);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }

    public List<OrderLine> showOrderLineByOredr(Order order){
        List<OrderLine> myList = new ArrayList<>();
        CRUDProduct crudProduct = new CRUDProduct();
        CRUDOrder crudOrder = new CRUDOrder();
        try {
            String query = "SELECT * FROM CapCake.OrderLine WHERE orders=?";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,order.getId());
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                OrderLine orderLine = new OrderLine();
                orderLine.setId(result.getInt(1));
                orderLine.setProduct(crudProduct.showProduct(result.getInt(2)));
                orderLine.setOrder(crudOrder.showOrder(result.getInt(3)));
                myList.add(orderLine);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }

    public OrderLine showOrderLine(int id){
        OrderLine orderLine = new OrderLine();
        CRUDProduct crudProduct = new CRUDProduct();
        CRUDOrder crudOrder = new CRUDOrder();
        try {
            String query = "SELECT * FROM CapCake.OrderLine WHERE id = ? ";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery(query);

            orderLine.setId(result.getInt(1));
            orderLine.setProduct(crudProduct.showProduct(result.getInt(2)));
            orderLine.setOrder(crudOrder.showOrder(result.getInt(3)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderLine;
    }

}
