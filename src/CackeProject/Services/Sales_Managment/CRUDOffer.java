package CackeProject.Services.Sales_Managment;


import CackeProject.Entities.Offre;
import CackeProject.Entities.User;
import CackeProject.Services.CRUDProduct;
import CackeProject.Services.CRUDUser;
import CackeProject.Utils.DataBase;
import CackeProject.Utils.SMSApi;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class CRUDOffer {

    public void addOffer(Offre offer,int userid) {
            try {
                String query = "INSERT INTO CupCake.Offer (product, price,old_price,description,user_id) values(?,?,?,?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setInt(1, offer.getProduct().getId());
                statement.setDouble(2, offer.getPrice());
                statement.setDouble(3, offer.getProduct().getPrice());
                statement.setString(4, offer.getDescription());
                statement.setInt(5, userid);

                statement.executeUpdate();
                CRUDUser crudUser = new CRUDUser();
                List<User> list = crudUser.showUser();
                list.forEach(e->System.out.println(e.getPhoneNum()));
                list.forEach(e-> SMSApi.sendSms(e.getPhoneNum(),offer.getDescription()+" Avec un prix imbattable "+offer.getPrice()));
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
            String query="UPDATE CupCake.Offer SET  product=? , price=?, old_price=?, description=?  where id=? ";
        PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1, offer.getProduct().getId());
            statement.setDouble(2, offer.getPrice());
            statement.setDouble(3, offer.getProduct().getPrice());
            statement.setString(4, offer.getDescription());
        statement.setInt(5,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Offre> showOfferByUser(int userid){
        List<Offre> myList = new ArrayList<>();
        CRUDProduct crudProduct = new CRUDProduct();
        try {
            String query = "SELECT * FROM CupCake.Offer WHERE user_id =?";
            PreparedStatement statement=  DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,userid);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                Offre offre = new Offre();
                offre.setId(result.getInt(1));
                offre.setOld_price(result.getFloat(2));
                offre.setProduct(crudProduct.showProduct(result.getInt(3)));
                offre.setPrice(result.getDouble(4));
                offre.setDescription(result.getString(5));

                myList.add(offre);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
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
                offre.setOld_price(result.getFloat(2));
                offre.setProduct(crudProduct.showProduct(result.getInt(3)));
                offre.setPrice(result.getDouble(4));
                offre.setDescription(result.getString(5));

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
                offre.setOld_price(result.getFloat(2));
                offre.setProduct(crudProduct.showProduct(result.getInt(3)));
                offre.setPrice(result.getDouble(4));
                offre.setDescription(result.getString(5));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return offre;
    }

}
