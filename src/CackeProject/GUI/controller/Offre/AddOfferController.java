package CackeProject.GUI.controller.Offre;

import CackeProject.Entities.FlashSale;
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
import javafx.stage.Stage;
import javafx.stage.Window;
import org.quartz.SchedulerException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class AddFlashSaleController implements Initializable, FlashSaleInterface {

    @FXML
    private TextField price;

    @FXML
    private ComboBox products;

    @FXML
    private DatePicker endDate;

    @FXML
    private TextArea description;

    @FXML
    private ChoiceBox<String> productList;

    @FXML
    private Button submitButton;


    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        CRUDProduct crudProduct = new CRUDProduct();
        ObservableList<String> productsList = FXCollections.observableArrayList(crudProduct.showProduct().stream().map(e->e.getName()).collect(Collectors.toList()));
        productList.setItems(productsList);
        products.setItems(productsList);
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
                    "Veuillez choisir une date limite pour l'offre");
            return;
        }

        CRUDProduct crudProduct = new CRUDProduct();
        CRUDFlashSale crudFlashSale = new CRUDFlashSale();
        FlashSale flashSale = new FlashSale();
        flashSale.setDescription(description.getText());
        flashSale.setProduct(crudProduct.showProduct());
        flashSale.setState("ON");
        flashSale.setPrice(Double.parseDouble(price.getText()));
        flashSale.setEndDate(java.sql.Date.valueOf(endDate.getValue()));
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Successful!",
                "L'offre est bien ajouté");
        try {
            crudFlashSale.addFlashSale(flashSale);
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
}

