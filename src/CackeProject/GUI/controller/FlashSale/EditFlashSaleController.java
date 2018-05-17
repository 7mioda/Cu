package CackeProject.GUI.controller.FlashSale;

import CackeProject.Entities.FlashSale;
import CackeProject.Entities.Offre;
import CackeProject.Entities.Product;
import CackeProject.Entities.User;
import CackeProject.GUI.Alert.AlertHelper;
import CackeProject.Interfaces.FlashSaleInterface;
import CackeProject.Services.CRUDProduct;
import CackeProject.Services.CRUDUser;
import CackeProject.Services.Sales_Managment.CRUDFlashSale;
import CackeProject.Services.Sales_Managment.CRUDOffer;
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
import org.quartz.SchedulerException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class EditFlashSaleController implements Initializable, FlashSaleInterface {

    @FXML
    private TextField price;
    @FXML
    private TableView<Product> productlist;
    @FXML
    private TableColumn<FlashSale, String> ID;
    @FXML
    private TableColumn<FlashSale, String> Name;
    @FXML
    private TableView<Product> productlist1;
    @FXML
    private TableColumn<FlashSale, String> ID1;
    @FXML
    private TableColumn<FlashSale, String> Name1;
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
    @FXML
    private Button remove;

    private List<Product> list;

    private FlashSale flashSale;



    public void initVariable(int id){
        this.flashSale = (new CRUDFlashSale()).showFlashSale(id);
        description.setPromptText("");
        description.setText(this.flashSale.getDescription());
        price.setPromptText("");
        price.setText(String.valueOf(this.flashSale.getPrice()));
        endDate.setValue(this.flashSale.getEndDate().toLocalDate());

        this.list = this.flashSale.getProduct();

        CRUDProduct crudProduct = new CRUDProduct();
        List<Product> myList = crudProduct.showProduct();
        ObservableList<Product> myOb=FXCollections.observableArrayList(myList);
        ID.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        productlist.setItems(myOb);


        ObservableList<Product> myOb1=FXCollections.observableArrayList(this.flashSale.getProduct());
        ID1.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        Name1.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        productlist1.setItems(myOb1);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

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
        if(endDate.getValue() == null) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Veuillez choisir une date limite pour la VenteFlash");
            return;
        }

        this.flashSale.setDescription(description.getText());
        this.flashSale.setPrice(Double.parseDouble(price.getText()));
        this.flashSale.setEndDate(java.sql.Date.valueOf(endDate.getValue()));
        this.flashSale.setState("ON");
        this.flashSale.setProduct(this.list);
        CRUDFlashSale crudFlashSale = new CRUDFlashSale();
        crudFlashSale.updateFlashSale(this.flashSale,this.flashSale.getId());
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Successful!",
                "L'offre est bien ajouté");
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
        this.flashSale.setDescription(description.getText());
        this.flashSale.setPrice(Double.parseDouble(price.getText()));
        this.flashSale.setEndDate(java.sql.Date.valueOf(endDate.getValue()));
        this.flashSale.setState("ON");
        this.flashSale.setProduct(this.list);
        CRUDFlashSale crudFlashSale = new CRUDFlashSale();
        crudFlashSale.updateFlashSale(this.flashSale,this.flashSale.getId());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/EditFlashSale.fxml"));
            Parent root = loader.load();
            EditFlashSaleController controller = loader.getController();
            controller.initVariable(this.flashSale.getId());
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Edition");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.print("unknow err");
        }
    }

    @FXML
    protected void handleRemoveButtonAction(ActionEvent event) {
        int id = productlist1.getSelectionModel().getSelectedItem().getId();
        CRUDProduct crudProduct = new CRUDProduct();
        this.list.remove(crudProduct.showProduct(id));

        this.flashSale.setDescription(description.getText());
        this.flashSale.setPrice(Double.parseDouble(price.getText()));
        this.flashSale.setEndDate(java.sql.Date.valueOf(endDate.getValue()));
        this.flashSale.setState("ON");
        this.flashSale.setProduct(this.list);
        CRUDFlashSale crudFlashSale = new CRUDFlashSale();
        crudFlashSale.updateFlashSale(this.flashSale,this.flashSale.getId());


        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/EditFlashSale.fxml"));
            Parent root = loader.load();
            EditFlashSaleController controller = loader.getController();
            controller.initVariable(this.flashSale.getId());
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Edition");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.print("unknow err");
        }

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

