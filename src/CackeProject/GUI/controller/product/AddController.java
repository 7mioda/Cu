package CackeProject.GUI.controller.product;


import CackeProject.Entities.Category;
import CackeProject.Entities.Product;
import CackeProject.Interfaces.ProductInterface;
import CackeProject.Services.CRUDCategory;
import CackeProject.Services.CRUDProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

public class AddController implements Initializable,ProductInterface {

    @FXML
    private TextField nameField, priceField, quantityField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private ComboBox categoryBox, supplierBox;
    @FXML
    private Button saveButton;

    private  CRUDCategory crudCategory = new CRUDCategory();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> categoryList = FXCollections.observableArrayList(crudCategory.showCategory().stream().map(e->e.getDesignation()).collect(Collectors.toList()));
        categoryBox.setItems(categoryList);

    }


    private boolean validateInput() {

        String errorMessage = "";

        if (nameField.getText() == null || nameField.getText().length() == 0) {
            errorMessage += "nom n'est pas valide!\n";
        }

        if (priceField.getText() == null || priceField.getText().length() == 0) {
            errorMessage += "le prix n'est pas valide!\n";
        }

        if (quantityField.getText() == null || quantityField.getText().length() == 0) {
            errorMessage += "la quantité n'est pas valide!\n";
        }

        if (descriptionArea.getText() == null || descriptionArea.getText().length() == 0) {
            errorMessage += "No email description!\n";
        }

        if (categoryBox.getSelectionModel().isEmpty()) {
            errorMessage += "Please select the category!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }

    @FXML
    public void handleCancel(ActionEvent event) {
        nameField.setText("");
        priceField.setText("");
        quantityField.setText("");
        descriptionArea.setText("");
        categoryBox.valueProperty().setValue(null);
    }

    @FXML
    public void handleSave(ActionEvent event) {
        Category category = crudCategory.showCategory(categoryBox.getSelectionModel().getSelectedIndex() + 1);
        Product product = new Product();
        CRUDProduct crudProduct = new CRUDProduct();
        product.setCategory(category);
        product.setPrice((parseInt(priceField.getText())));
        product.setQuantity(parseInt(quantityField.getText()));
        product.setName(nameField.getText());
        crudProduct.addProduct(product);
    }

    @FXML
    public void closeAction(ActionEvent event) {((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
