package CackeProject.GUI.controller.FlashSale;

import CackeProject.Entities.FlashSale;
import CackeProject.Entities.Product;
import CackeProject.Entities.User;
import CackeProject.GUI.Alert.AlertHelper;
import CackeProject.Interfaces.FlashSaleInterface;
import CackeProject.Services.CRUDProduct;
import CackeProject.Services.Sales_Managment.CRUDFlashSale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.quartz.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;



public class AddFlashSaleController implements Initializable, FlashSaleInterface {

    @FXML
    private TextField price;

    @FXML
    private TableView<Product> productlist;

    @FXML
    private TableColumn<FlashSale, String> ID;
    @FXML
    private TableColumn<FlashSale, String> Name;
    @FXML
    private TableColumn<FlashSale, String> Price;
    @FXML
    private TableColumn<FlashSale, String> Quantity;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextArea description;

    @FXML
    private Button submitButton;
    @FXML
    private Button back;
    @FXML
    private Button addP;

    private FlashSale flashSale;

    private List<Product> list;


    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        this.list = new ArrayList<>();
        CRUDProduct crudProduct = new CRUDProduct();
        List<Product> myList = crudProduct.showProduct();
        ObservableList<Product> myOb=FXCollections.observableArrayList(myList);
        ID.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        Price.setCellValueFactory(
                new PropertyValueFactory<>("Price"));
        Quantity.setCellValueFactory(
                new PropertyValueFactory<>("Quantity"));
        productlist.setItems(myOb);
    }


    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {
        Window owner = submitButton.getScene().getWindow();
        if(price.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Le prix ne doit etre vide");
            return;
        }
        if(description.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "La description ne doit pas etre vide");
            return;
        }
        if(this.list == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Ajouter des produits a la venteflash");
            return;
        }
        if(endDate.getValue() == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Veuillez choisir une date limite pour l'offre");
            return;
        }

        CRUDFlashSale crudFlashSale = new CRUDFlashSale();
        FlashSale flashSale = new FlashSale();
        flashSale.setDescription(description.getText());
        flashSale.setState("ON");
        flashSale.setProduct(this.list);
        flashSale.setPrice(Double.parseDouble(price.getText()));
        flashSale.setEndDate(java.sql.Date.valueOf(endDate.getValue()));
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Successful!",
                "La venteflash est bien ajouté");
        try {
            crudFlashSale.addFlashSale(flashSale, User.getInstance().getId());
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/ShowAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("FlashSales");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.print("unknow err");
        }
    }

    @FXML
    protected void handleBackButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/ShowAdmin.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("FlashSales");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.print("unknow err");
        }
    }

    @FXML
    protected void handleAddButtonAction(ActionEvent event) {
        int id = productlist.getSelectionModel().getSelectedItem().getId();
        CRUDProduct crudProduct = new CRUDProduct();
        this.list.add(crudProduct.showProduct(id));
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

