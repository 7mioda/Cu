package CackeProject.Services.Sales_Managment;


import CackeProject.Entities.FlashSale;

import CackeProject.Entities.Product;
import CackeProject.Entities.User;
import CackeProject.Services.CRUDProduct;
import CackeProject.Services.CRUDUser;
import CackeProject.Utils.DataBase;
import CackeProject.Utils.SMSApi;
import org.apache.log4j.BasicConfigurator;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


import java.sql.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


public class CRUDFlashSale  {

    public void addFlashSale(FlashSale flashSale,int Userid) throws SchedulerException  {
            try {
                String query = "INSERT INTO CupCake.FlashSale(price,description,State,date,endDate,user_id) values(?,?,?,?,?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setDouble(1, flashSale.getPrice());
                statement.setString(2, flashSale.getDescription());
                statement.setString(3, flashSale.getState());
                Calendar currenttime = Calendar.getInstance();
                Date date = new Date((currenttime.getTime()).getTime());
                statement.setTimestamp(4, new Timestamp( date.getTime()));
                statement.setTimestamp(5, new Timestamp(flashSale.getEndDate().getTime()));
                statement.setInt(6, Userid);
                statement.executeUpdate();
                } catch (SQLException e) {
                e.printStackTrace();}

                try {
                String query = "SELECT LAST_INSERT_ID()";
                Statement statement11 = DataBase.getInstance().getCnx().createStatement();
                ResultSet result = statement11.executeQuery(query);
                while(result.next()){
                    flashSale.setId(result.getInt(1));
                }
                    CRUDUser crudUser = new CRUDUser();
                    List<User> list = crudUser.showUser();
                    list.forEach(e->System.out.println(e.getPhoneNum()));
                    list.forEach(e->SMSApi.sendSms(e.getPhoneNum(),flashSale.getDescription()+" Avec un prix imbattable "+flashSale.getPrice()));
                } catch (SQLException e) {
                 e.printStackTrace();
                }

                flashSale.getProduct().forEach(e->{
                     try {
                           String query1 = "INSERT INTO CupCake.FlashSaleProduct(flashsale,product) values(?,?)";
                           PreparedStatement statement2 = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query1);
                           statement2.setInt(1, flashSale.getId());
                           statement2.setInt(2, e.getId());
                           statement2.executeUpdate();
                     } catch (SQLException e1) {
                         e1.printStackTrace();
                     }
                 });


           JobDataMap jobDataMap = new JobDataMap();
            jobDataMap.putIfAbsent("flashsale",flashSale);
         //FlashSale Managment

           BasicConfigurator.configure();

           SchedulerFactory sf = new StdSchedulerFactory();
           Scheduler scheduler = null;
            scheduler = sf.getScheduler();
        JobDetail job = newJob(FlashSaleHandler.class)
                .withIdentity("job1", "group1")
                .setJobData(jobDataMap)
                .build();
        Trigger trigger = newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule().withIntervalInMinutes(5)).build();
            assert scheduler != null;
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
    }


    public void deleteFlashSale(int id){
        try {
            String query="DELETE FROM CupCake.FlashSaleProduct where FlashSale = ?";
            PreparedStatement statement=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
            query = "DELETE FROM CupCake.FlashSale where id = ?";
            PreparedStatement statement2=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
            statement2.setInt(1,id);
            statement2.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateFlashSale(FlashSale flashSale,int id){

        try {
             String query = "DELETE FROM CupCake.FlashSaleProduct where FlashSale = ?";
             PreparedStatement statement2=(PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
             statement2.setInt(1,id);
             statement2.executeUpdate();
         } catch (SQLException e) {
          e.printStackTrace();
          }
        try {
            String query="UPDATE CupCake.FlashSale SET price=? ,endDate=?,description=?,state=? where id=? ";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setDouble(1, flashSale.getPrice());
            statement.setDate(2,flashSale.getEndDate());
            statement.setString(3,flashSale.getDescription());
            statement.setString(4,flashSale.getState());
            statement.setInt(5,id);
            statement.executeUpdate();

            flashSale.getProduct().forEach(e->{
                try {
                    String query1 = "INSERT INTO CupCake.FlashSaleProduct(flashsale,product) values(?,?)";
                    PreparedStatement statement2 = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query1);
                    statement2.setInt(1, flashSale.getId());
                    statement2.setInt(2, e.getId());
                    statement2.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<FlashSale> showFlashSaleByUser(int Userid){
        List<FlashSale> myList = new ArrayList<>();
        try {
            String query = "SELECT * FROM CupCake.FlashSale WHERE user_id =?";
            PreparedStatement statement=  DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,Userid);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                FlashSale flashSale = new FlashSale();
                flashSale.setId(result.getInt(1));
                flashSale.setPrice(result.getDouble(2));
                flashSale.setEndDate(result.getDate(4));
                flashSale.setDescription(result.getString(5));
                flashSale.setState(result.getString(6));
                myList.add(flashSale);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }

    public List<FlashSale> showFlashSale(){
        List<FlashSale> myList = new ArrayList<>();
        try {
            String query = "SELECT * FROM CupCake.FlashSale";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                FlashSale flashSale = new FlashSale();
                flashSale.setId(result.getInt(1));
                flashSale.setPrice(result.getDouble(2));
                flashSale.setEndDate(result.getDate(4));
                flashSale.setDescription(result.getString(5));
                flashSale.setState(result.getString(6));
                myList.add(flashSale);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }

    public FlashSale showFlashSale(int id){
        FlashSale flashSale = new FlashSale();
        CRUDProduct crudProduct = new CRUDProduct();
        List<Product> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM CupCake.FlashSale WHERE id =?";
            PreparedStatement statement=  DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                flashSale.setId(result.getInt(1));
                flashSale.setPrice(result.getDouble(2));
                flashSale.setEndDate(result.getDate(4));
                flashSale.setDescription(result.getString(5));
                flashSale.setState(result.getString(6));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        try {
            String query = "SELECT Product FROM CupCake.FlashSaleProduct WHERE FlashSale =?";
            PreparedStatement statement=  DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            while(result.next()){
                list.add(crudProduct.showProduct(result.getInt(1)));
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        flashSale.setProduct(list);
        return flashSale;
    }
}