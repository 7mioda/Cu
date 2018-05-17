package CackeProject.GUI.controller.User;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import CackeProject.Entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
public class ProfileController implements Initializable {

    @FXML
    private AnchorPane sidebar;
    @FXML
    private AnchorPane show;
    @FXML
    private TextField nameField;
    @FXML
    private TextField surnameField;
    @FXML
    private TextField adressField;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField loginField;
    @FXML
    private TextField emailField;
    @FXML
    private AnchorPane edit1;
    @FXML
    private AnchorPane edit11;
    @FXML
    private Button btnmodif;
    @FXML
    private ImageView logOut;
    @FXML
    private ImageView profilephoto;
    @FXML
    private Button btnPatisserie;
    @FXML
    private Label Label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        btnPatisserie.setVisible(false);
        User u = User.getInstance();
        
        if(u!=null)
        {   
            
            nameField.setPromptText("");
            nameField.setText(u.getName());
            nameField.setEditable(false);
            
            surnameField.setPromptText("");
            surnameField.setText(u.getSurname());
            surnameField.setEditable(false);
            
            adressField.setPromptText("");
            adressField.setText(u.getAdress());
            adressField.setEditable(false);
            
            phoneField.setPromptText("");
            phoneField.setText(u.getPhoneNum());
            phoneField.setEditable(false);
            
            emailField.setPromptText("");
            emailField.setText(u.getEmail());
            emailField.setEditable(false);
            
            loginField.setPromptText("");
            loginField.setText(u.getUsername());
            loginField.setEditable(false);
            
            Label.setText("bienvenue "+u.getRole());
         /*FileChooser FC =new FileChooser();
	File selectedFile =FC.showOpenDialog(null);
	BufferedImage image = ImageIO.read(imageFile);*/
        
    
            
        }
    }    

    @FXML
    private void Modifierprofil(ActionEvent event) throws IOException {
        
               Parent subscibe_page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/UpdateProfile.fxml"));
               Scene scene = new Scene(subscibe_page);
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setScene(scene);
                stage.show();
                System.out.println("ready to update");
                
    }

    @FXML
    private void Logout(MouseEvent event) throws IOException {
          System.out.println("you have been Logged out");
                
                Parent subscibe_page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/Login.fxml"));
                System.out.println("ROOT login !");

                Scene scene = new Scene(subscibe_page);
                System.out.println("NEW SCENE login Retour!");

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setTitle("you are in your Login page");
                stage.setScene(scene);
                stage.show();
    }

       public TextField getEmail() {
        return emailField;
    }

    public void setEmail(String Email) {
        this.emailField.setText(Email);
    }

    public TextField getPhoneNum() {
        return phoneField;
    }

    public void setPhoneNum(String PhoneNum) {
        this.phoneField.setText(PhoneNum);
    }

    public TextField getAdresse() {
        return adressField;
    }

    public void setAdresse(String Adresse) {
        this.adressField.setText(Adresse);
    }

    public TextField getSurname() {
        return surnameField;
    }

    public void setSurname(String Surname) {
        this.surnameField.setText(Surname);
    }

    public TextField getName() {
        return nameField;
    }

    public void setNom(String Nom) {
        this.nameField.setText(Nom);
    }

    @FXML
    private void goPatisserie(ActionEvent event) {
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
    
}
