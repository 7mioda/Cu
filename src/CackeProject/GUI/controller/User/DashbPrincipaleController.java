package CackeProject.GUI.controller.User;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import CackeProject.Entities.User;
import CackeProject.Services.CRUDUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhmedMekni
 */
public class DashbPrincipaleController implements Initializable {

    @FXML
    private AnchorPane sidebar;
    @FXML
    private ImageView logOut;
    @FXML
    private AnchorPane show;
    @FXML
    private TableView<User> user;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> surname;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, String> phoneNum;
    @FXML
    private TableColumn<User, String> Email;
    @FXML
    private TableColumn<User, String> username;
    @FXML
    private ImageView gotoStat;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<User> myList = (ArrayList<User>) new CRUDUser().showUser();
        
         ObservableList<User> myOb=FXCollections.observableArrayList(myList);
         user.setItems(myOb);
         
         name.setCellValueFactory(
                   new PropertyValueFactory<>("Name"));
         surname.setCellValueFactory(
                   new PropertyValueFactory<>("Surname"));
         adresse.setCellValueFactory(
                   new PropertyValueFactory<>("Adress"));
         phoneNum.setCellValueFactory(
                   new PropertyValueFactory<>("PhoneNum"));
         Email.setCellValueFactory(
                   new PropertyValueFactory<>("Email"));
         username.setCellValueFactory(
                   new PropertyValueFactory<>("Username"));
         
    }    

    @FXML
    private void Logout(MouseEvent event) throws IOException {
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
    private void BanUser(ActionEvent event) throws SQLException {
         User u = user.getItems().get(user.getSelectionModel().getSelectedIndex());
         CRUDUser us = new CRUDUser();
//       // System.out.println(SessionUser.getUsername());
        us.BanUser(u);
        System.out.println( "user has been banned");
        
//        hystorique hist = new hystorique();
//        CRUDhystorique CH =new CRUDhystorique();
//        hist=CH.getHistoryById(u.getId());
//        CH.updateHystorique(hist, u.getId());
        
    }
    @FXML
    private void ignoreBanUser(ActionEvent event) throws SQLException {
         User u = user.getItems().get(user.getSelectionModel().getSelectedIndex());
        CRUDUser us = new CRUDUser();
        us.IgnoreBanUser(u);
        System.out.println( "user has been activated");
    }
    
    @FXML
    private void goto_stat(MouseEvent event) throws IOException {
        
 Parent subscibe_page = FXMLLoader.load(getClass().getResource("Statistique.fxml"));
                System.out.println("ROOT statistique !");
                Scene scene = new Scene(subscibe_page);
                System.out.println("NEW SCENE statistique !");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("you are in state page , les utilisateurs banés ");
                stage.setScene(scene);
                stage.show();
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
