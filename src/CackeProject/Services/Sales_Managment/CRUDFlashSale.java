package CackeProject.Services.Sales_Managment;


import CackeProject.Entities.FlashSale;

import CackeProject.Utils.DataBase;
import CackeProject.Services.Sales_Managment.FlashSaleHandler;
import org.apache.log4j.BasicConfigurator;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;


import java.sql.*;

import java.util.Calendar;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


public class CRUDFlashSale  {

    public void addFlashSale(FlashSale flashSale) throws SchedulerException  {
            try {
                String query = "INSERT INTO CupCake.FlashSale(price,description,State,date,endDate) values(?,?,?,?,?)";
                PreparedStatement statement = (PreparedStatement) DataBase.getInstance().getCnx().prepareStatement(query);
                statement.setDouble(1, flashSale.getPrice());
                statement.setString(2, flashSale.getDescription());
                statement.setString(3, flashSale.getState());
                Calendar currenttime = Calendar.getInstance();
                Date date = new Date((currenttime.getTime()).getTime());
                statement.setTimestamp(4, new Timestamp( date.getTime()));
                statement.setTimestamp(5, new Timestamp(flashSale.getEndDate().getTime()));
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
                .withSchedule(simpleSchedule().withIntervalInMinutes(flashSale.getDate().getMinutes())).build();
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
            String query="UPDATE CupCake.FlashSale SET price=? , date=? ,endDate=?,description=?,state=? where id=? ";
            PreparedStatement statement= DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setDouble(1, flashSale.getPrice());
            statement.setDate(2,flashSale.getDate());
            statement.setDate(3,flashSale.getEndDate());
            statement.setString(4,flashSale.getDescription());
            statement.setString(5,flashSale.getState());
            statement.setInt(6,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


/*
    public List<Offre> showFlashSale(){
        List<User> myList = new ArrayList<>();
        try {
            String query = "SELECT * FROM CapCake.User";
            Statement statement= DataBase.getInstance().getCnx().createStatement();
            ResultSet result = statement.executeQuery(query);
            while(result.next()){
                User user = new User();
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setSurname(result.getString(3));
                user.setAdress(result.getString(4));
                user.setPhoneNum(result.getString(5));
                user.setEmail(result.getString(6));
                user.setUsername(result.getString(7));
                user.setPassword(result.getString(8));
                myList.add(user);
            }
            return myList;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return myList;
    }

    /**
     * Show a specific User
     * @param id User id to show
     * @return

    public User showFlashSale(int id){
        User user = new User();
        try {
            String query = "SELECT * FROM User WHERE id =?";
            PreparedStatement statement=  DataBase.getInstance().getCnx().prepareStatement(query);
            statement.setInt(1,id);
            ResultSet result = statement.executeQuery();
            if(result.next()){
                user.setId(result.getInt(1));
                user.setName(result.getString(2));
                user.setSurname(result.getString(3));
                user.setAdress(result.getString(4));
                user.setPhoneNum(result.getString(5));
                user.setEmail(result.getString(6));
                user.setUsername(result.getString(7));
                user.setPassword(result.getString(8));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }*/

}