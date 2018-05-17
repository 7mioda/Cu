package CackeProject.GUI.controller.Offre;

import CackeProject.Entities.FlashSale;
import CackeProject.Entities.Offre;
import CackeProject.Entities.User;
import CackeProject.Interfaces.FlashSaleInterface;
import CackeProject.Services.Sales_Managment.CRUDOffer;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class ShowAdminController implements Initializable, FlashSaleInterface {

    @FXML
    private Button Ajouter;
    @FXML
    private TableView<Offre> offerTableView;
    @FXML
    private TableColumn<FlashSale, String> ID;
    @FXML
    private TableColumn<FlashSale, String> Price;
    @FXML
    private TableColumn<FlashSale, String> Old_Price;
    @FXML
    private TableColumn<Type,Boolean> Action;


    @Override
    public void initialize(URL location, ResourceBundle resources)  {
        CRUDOffer crudOffer = new CRUDOffer();
        ArrayList<Offre> myList = (ArrayList<Offre>) crudOffer.showOfferByUser(User.getInstance().getId());

        ObservableList<Offre> myOb = FXCollections.observableArrayList(myList);

        ID.setCellValueFactory(
                new PropertyValueFactory<>("id"));
        Price.setCellValueFactory(
                new PropertyValueFactory<>("price"));
        Old_Price.setCellValueFactory(
                new PropertyValueFactory<>("old_price"));


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

        offerTableView.setItems(myOb);

    }


    private class ButtonCell extends TableCell<Type, Boolean> {

        final Button cellButton3 = new Button("Consulter");
        final Button cellButton2 = new Button("+");
        final Button cellButton = new Button("-");



        ButtonCell() {
            CRUDOffer p = new CRUDOffer();

            cellButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent t) {

                    int id = offerTableView.getSelectionModel().getSelectedItem().getId();
                    p.deleteOffer(id);
                    System.out.print(id);
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/Offre/ShowAdmin.fxml"));
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

                    int id = offerTableView.getSelectionModel().getSelectedItem().getId();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/Offre/EditOffre.fxml"));
                        Parent root = loader.load();
                        EditOfferController controller = loader.getController();
                        controller.initVariable(id);
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
                        stage.setTitle("Edition");
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

                    int id = offerTableView.getSelectionModel().getSelectedItem().getId();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/Offre/ShowOffre.fxml"));
                        Parent root = loader.load();
                        ShowOfferController controller = loader.getController();
                        controller.initVariable(id);
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) ((Node) t.getSource()).getScene().getWindow();
                        stage.setTitle("you are in your Login page");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        System.err.print(ex.getMessage());
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/Offre/AddOffre.fxml"));
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

    @FXML
    protected void handleBackFlashsaleAction(ActionEvent event) {
        try {
            Parent page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/FlashSale/ShowAdmin.fxml"));
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("FlashSale");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.err.print("unknow err");
        }
    }
}