package CackeProject.GUI.controller.FlashSale;

import CackeProject.Entities.FlashSale;
import CackeProject.Interfaces.FlashSaleInterface;
import CackeProject.Services.Sales_Managment.CRUDFlashSale;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.PixelFormat.Type;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class ShowAdminController implements Initializable, FlashSaleInterface {

    @FXML
    private Button Ajouter;
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
    private TableColumn<Type,Boolean> Action;


    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        CRUDFlashSale crudFlashSale = new CRUDFlashSale();
        ArrayList<FlashSale> myList = (ArrayList<FlashSale>) crudFlashSale.showFlashSale();

        ObservableList<FlashSale> myOb=FXCollections.observableArrayList(myList);
      //  flashSaleTableView.setItems(myOb);

        ID.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        Description.setCellValueFactory(
                new PropertyValueFactory<>("Description"));
        Price.setCellValueFactory(
                new PropertyValueFactory<>("Price"));
        State.setCellValueFactory(
                new PropertyValueFactory<>("State"));

        Action.setSortable(false);
        Action.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Type, Boolean>, ObservableValue<Boolean>>() {

                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Type, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });
        Action.setCellFactory(
                new Callback<TableColumn<Type, Boolean>, TableCell<Type, Boolean>>() {

                    @Override
                    public TableCell<Type, Boolean> call(TableColumn<Type, Boolean> p) {
                        return new ButtonCell();
                    }

                });

        flashSaleTableView.setItems(myOb);

    }


private class ButtonCell extends TableCell<Type, Boolean> {

    final Button cellButton3 = new Button("Consulter");
    final Button cellButton2 = new Button("+");
    final Button cellButton = new Button("-");



    ButtonCell() {
        CRUDFlashSale p = new CRUDFlashSale();
        cellButton.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent t) {

                int id = flashSaleTableView.getSelectionModel().getSelectedItem().getId();
                p.deleteFlashSale(id);
                System.out.print(id);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/ShowAdmin.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
                    stage.setTitle("you are in your Login page");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.print("unknow err");
                }

            }
        });


        cellButton2.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                int id = flashSaleTableView.getSelectionModel().getSelectedItem().getId();
                System.out.print(id);
               try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/AddFlashSale.fxml"));
                    Parent root = loader.load();
                   Scene scene = new Scene(root);
                   Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
                   stage.setTitle("you are in your Login page");
                   stage.setScene(scene);
                   stage.show();
                } catch (IOException ex) {
                    System.err.print("unknow err");
                }
            }
        });


        cellButton3.setOnAction(new EventHandler<ActionEvent>() {


            @Override
            public void handle(ActionEvent t) {

                int id = flashSaleTableView.getSelectionModel().getSelectedItem().getId();
                p.deleteFlashSale(id);
                System.out.print(id);
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/EditFlashSale.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
                    stage.setTitle("you are in your Login page");
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException ex) {
                    System.err.print("unknow err");
                }
            }
        });

    }


    //Display button if the row is not empty
    @Override
    protected void updateItem(Boolean t, boolean empty) {
        super.updateItem(t, empty);
        if(!empty){
            HBox pane = new HBox(cellButton3, cellButton2,cellButton);
            setGraphic(pane);
        }
    }

}
    @FXML
    protected void handleSubmitButtonAction(ActionEvent event) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/AddFlashSale.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("AddFlashSale");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.print("unknow err");
        }
    }
}