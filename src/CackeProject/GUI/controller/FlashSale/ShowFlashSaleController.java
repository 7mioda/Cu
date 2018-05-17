package CackeProject.GUI.controller.FlashSale;

import CackeProject.Entities.FlashSale;
import CackeProject.Entities.Product;
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
import org.quartz.SchedulerException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class ShowFlashSaleController implements Initializable, FlashSaleInterface {

    @FXML
    private TextField price;

    @FXML
    private TextField date;

    @FXML
    private TableView<Product> products;

    @FXML
    private TableColumn<FlashSale, String> ID;
    @FXML
    private TableColumn<FlashSale, String> Name;
    @FXML
    private TableColumn<FlashSale, String> Price;
    @FXML
    private TableColumn<FlashSale, String> Quantity;

    @FXML
    private TextArea description;

    @FXML
    private ChoiceBox<String> productList;

    @FXML
    private Button submitButton;

    private FlashSale flashSale;



    public void initVariable(int id){
        this.flashSale = (new CRUDFlashSale()).showFlashSale(id);
        description.setPromptText("");
        description.setText(this.flashSale.getDescription());
        description.setEditable(false);

        price.setPromptText("");
        price.setText(String.valueOf(this.flashSale.getPrice()));
        price.setEditable(false);

        date.setPromptText("");
        date.setText(String.valueOf(this.flashSale.getEndDate()));
        date.setEditable(false);

        ObservableList<Product> myOb1=FXCollections.observableArrayList(this.flashSale.getProduct());
        ID.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        Name.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        Quantity.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));
        Price.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        products.setItems(myOb1);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources)  {

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

