package CackeProject.GUI.controller.product;

import static java.lang.Integer.parseInt;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import CackeProject.Entities.Category;
import CackeProject.Entities.Product;
import CackeProject.Services.CRUDCategory;
import CackeProject.Services.CRUDProduct;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author AhmedMekni
 */
public class AddProductController implements Initializable {

    @FXML
    private AnchorPane sidebar;
    @FXML
    private ImageView logOut;
    @FXML
    private AnchorPane show;
    private TextField nameField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private TextField priceField;
    @FXML
    private ComboBox categoryBox;
    @FXML
    private TextField quantityField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelbtn;
    @FXML
    private AnchorPane edit1;
    @FXML
    private AnchorPane edit11;
    private CRUDCategory crudCategory = new CRUDCategory();
    @FXML
    private TextField Productf;
    @FXML
    private Button addCateg;
    @FXML
    private TextField designation;
   
    @FXML
    private Button ajoutertabcateg;
    @FXML
    private ComboBox<String> unité;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> categoryList = FXCollections.observableArrayList(crudCategory.showCategory().stream().map(e->e.getDesignation()).collect(Collectors.toList()));
        categoryBox.setItems(categoryList);
          unité.getItems().removeAll(unité.getItems());
        unité.getItems().addAll("Kg", "Par pièce","Par morceau");
        ajoutertabcateg.setVisible(false);
        designation.setVisible(false);
        unité.setVisible(false);
        
    }    
 

    @FXML
    private void Logout(MouseEvent event) {
    }

    @FXML
    private void handleSave(ActionEvent event) {
       
        Category category = crudCategory.showCategory(categoryBox.getSelectionModel().getSelectedIndex() + 1);
        Product product = new Product();
       // Product p = product.getInstance();
        CRUDProduct crudProduct = new CRUDProduct();
        product.setCategory(category);
        product.setPrice((parseInt(priceField.getText())));
        product.setQuantity(parseInt(quantityField.getText()));

        product.setName(Productf.getText());
        crudProduct.addProduct(product);
        
    
    }

    @FXML
    private void handleCancel(ActionEvent event) {
         Productf.setText("");
        priceField.setText("");
        quantityField.setText("");
        descriptionArea.setText("");
        categoryBox.valueProperty().setValue(null);
    }

    @FXML
    private void addCateg(ActionEvent event) {
        ajoutertabcateg.setVisible(true);
       
        designation.setVisible(true);
        unité.setVisible(true);
    }

    @FXML
    private void addCategCrud(ActionEvent event) {
         ajoutertabcateg.setVisible(false);
        designation.setVisible(false);
        unité.setVisible(false);
        Category category =  new Category();
        CRUDCategory crudCat = new CRUDCategory();
         category.setDesignation(designation.getText());
         category.setUnit(unité.getItems().get(unité.getSelectionModel().getSelectedIndex()));
         crudCat.addCategory(category);
         //Category.setInstance(category);
        
        
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
