package CackeProject.Services;


import CackeProject.Entities.Offre;
import CackeProject.Utils.DataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CRUDOffer {

    public void addOffer(Offre offer) {
            try {
                String query = "INSERT INTO CupCake.Offer (percentage, product, price) values(?,?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setFloat(1, offer.getPercentage());
                statement.setInt(2, offer.getProduct().getId());
                statement.setDouble(3, offer.getPrice());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }


    public void deleteOffer(int id){
        try {
            String query="DELETE FROM CupCake.Offer where id = ?";
        PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateOffer(Offre offer,int id){

        try {
            String query="UPDATE CupCake.Offer SET percentage=? , product=? ,price=? where id=? ";
        PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
        statement.setFloat(1, offer.getPercentage());
        statement.setInt(2, offer.getProduct().getId());
        statement.setDouble(3,offer.getPrice());
        statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Offre> showOffer(){
        List<Offre> myList = new ArrayList<>();
        CRUDProduct crudProduct = new CRUDProduct();
        try {
            String query = "SELECT * FROM CupCake.Offer";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                Offre offre = new Offre();
                offre.setId(result.getInt(1));
                offre.setPercentage(result.getFloat(2));
                offre.setProduct(crudProduct.showProduct(result.getInt(3)));
                offre.setPrice(result.getDouble(4));

                myList.add(offre);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }


    public Offre showOffer(int id){
        Offre offre = new Offre();
        CRUDProduct crudProduct = new CRUDProduct();
        try {
            String query = "SELECT * FROM CupCake.Offer WHERE id =?";
            PreparedStatement statement=  DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                offre.setId(result.getInt(1));
                offre.setPercentage(result.getFloat(2));
                offre.setProduct(crudProduct.showProduct(result.getInt(3)));
                offre.setPrice(result.getDouble(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offre;
    }

}
