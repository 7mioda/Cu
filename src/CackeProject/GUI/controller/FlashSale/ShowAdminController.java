package CackeProject.GUI.controller.FlashSale;

import CackeProject.Entities.FlashSale;
import CackeProject.GUI.Alert.AlertHelper;
import CackeProject.Interfaces.FlashSaleInterface;
import CackeProject.Services.CRUDProduct;
import CackeProject.Services.Sales_Managment.CRUDFlashSale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import org.quartz.SchedulerException;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


public class ShowController implements Initializable, FlashSaleInterface {

    @FXML
    private TableView<FlashSale> flashSaleTableView;
    @FXML
    private TableColumn<FlashSale, String> ID;
    @FXML
    private TableColumn<FlashSale, String> Description;
    @FXML
    private TableColumn<FlashSale, String> Price;
    @FXML
    private TableColumn<FlashSale, String> State;
    @FXML
    private Button submitButton;


    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        CRUDFlashSale crudFlashSale = new CRUDFlashSale();
        ArrayList<FlashSale> myList = (ArrayList<FlashSale>) crudFlashSale.showFlashSale();

        ObservableList<FlashSale> myOb=FXCollections.observableArrayList(myList);
        flashSaleTableView.setItems(myOb);

        ID.setCellValueFactory(
                new PropertyValueFactory<>("ID"));
        Description.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        Price.setCellValueFactory(
                new PropertyValueFactory<>("Price"));
        State.setCellValueFactory(
                new PropertyValueFactory<>("State"));

    }

    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {

            return;
        }
}

