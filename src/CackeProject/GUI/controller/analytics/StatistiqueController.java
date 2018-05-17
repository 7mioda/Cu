package CackeProject.GUI.controller.analytics;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import CackeProject.Utils.DataBase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhmedMekni
 */
public class StatistiqueController implements Initializable {

    @FXML
    private AnchorPane sidebar;
    @FXML
    private ImageView logOut;
    @FXML
    private AnchorPane show;
    @FXML
    private PieChart piechart;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button buttonclear;
    @FXML
    private ImageView stat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableList data = FXCollections.observableArrayList();
        
        
         try {

            String req = "SELECT `name` FROM `User` WHERE `state` = 1";
            Connection cnx = DataBase.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req); 
            int somme;
            while (res.next()) {

                String th = res.getString(1);
                System.out.println(th);
                ResultSet x = cnx.createStatement().executeQuery("select count(*) from User where name like '" + res.getString(1) + "'");
                    while(x.next()){
                // int somme=Integer.parseInt((int) x.getObject(1));
               somme = x.getInt(1);
                data.add(new PieChart.Data(res.getString(1), somme));
                    }
            }
            piechart.setTitle("les utilisateur banées ");
            piechart.setData(data);
            

        } catch (SQLException ex) {
            Logger.getLogger(Stat_ENS_TH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    

    @FXML
    private void handleButton1Action(ActionEvent event) {
         ObservableList data = FXCollections.observableArrayList();
        
        
         try {

           // String req = "SELECT DISTINCT name FROM `user`";
           String req = "SELECT DISTINCT role FROM `User`";
            Connection cnx = DataBase.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req); 
            int somme;
            while (res.next()) {

                String th = res.getString(1);
                System.out.println(th);
                ResultSet x = cnx.createStatement().executeQuery("select count(*) from User where role like '" + res.getString(1) + "'");
                    while(x.next()){
                // int somme=Integer.parseInt((int) x.getObject(1));
               somme = x.getInt(1);
                data.add(new PieChart.Data(res.getString(1), somme));
                    }
            }
            piechart.setTitle("Pourcentage des Utilisateur");
            piechart.setData(data);
            

        } catch (SQLException ex) {
            Logger.getLogger(Stat_ENS_TH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleButton2Action(ActionEvent event) {
        
        //show Product
        
        
        
    }

    @FXML
    private void handleButtonClearAction(ActionEvent event) throws IOException {
        
         ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList();
        piechart.setTitle("");
        piechart.setData(pieChartData);
        
            Parent subscibe_page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/DashbPrincipale.fxml"));
                System.out.println("ROOT login !");

                Scene scene = new Scene(subscibe_page);
                System.out.println("NEW SCENE login Retour!");

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setTitle("you are in your Login page");
                stage.setScene(scene);
                stage.show();
    }

    @FXML
    private void goto_stat(MouseEvent event) {
        
//        ********************************************************************** 
//***
//***** initialize
//*************************************************************************************************
         ObservableList data = FXCollections.observableArrayList();
        
        
         try {

            String req = "SELECT `name` FROM `User` WHERE `state` = 1";
            Connection cnx = DataBase.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req); 
            int somme;
            while (res.next()) {

                String th = res.getString(1);
                System.out.println(th);
                ResultSet x = cnx.createStatement().executeQuery("select count(*) from User where name like '" + res.getString(1) + "'");
                    while(x.next()){
                // int somme=Integer.parseInt((int) x.getObject(1));
               somme = x.getInt(1);
                data.add(new PieChart.Data(res.getString(1), somme));
                    }
            }
            piechart.setTitle("les utilisateur banées");
            piechart.setData(data);
            

        } catch (SQLException ex) {
            Logger.getLogger(Stat_ENS_TH.class.getName()).log(Level.SEVERE, null, ex);
        }
//*****************************************************************************************************************
//***
//***** initialize
//*************************************************************************************************
  
        
        
        
    }

    @FXML
    private void go_accuille(MouseEvent event) {
    }

    @FXML
    private void go_promo(MouseEvent event) {
    }

    @FXML
    private void getProduct(MouseEvent event) {
    }

    @FXML
    private void go_commander_prod(MouseEvent event) {
    }


    @FXML
    private void LogOUT(MouseEvent event) throws IOException {
        System.out.println("you have been Logged out");

        Parent subscibe_page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/Login.fxml"));
        System.out.println("ROOT login !");

        Scene scene = new Scene(subscibe_page);
        System.out.println("NEW SCENE login Retour!");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Login page");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void RSS(MouseEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/RSS/Show.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("RSS");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void FlashsaleAdmin(MouseEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/ShowAdmin.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("FlashSale");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Analytics(MouseEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/analytics/Statistique.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Statistique");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void Products(MouseEvent event) throws IOException {
        Parent page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/product/AddProduct.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Statistique");
        stage.setScene(scene);
        stage.show();
    }
}
