package CackeProject.GUI.controller.User;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import CackeProject.Entities.User;
import CackeProject.Entities.hystorique;
import CackeProject.GUI.Alert.AlertHelper;
import CackeProject.Services.CRUDUser;
import CackeProject.Services.CRUDhystorique;
import CackeProject.Utils.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AhmedMekni
 */
public class RegistrationController implements Initializable {

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
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Button CanselBTN;
    @FXML
    private Button ValiderUser;

    @FXML
    private AnchorPane edit;
    @FXML
    private ComboBox<String> roleCB;
    @FXML
    private TextField TaxField;

    boolean validate(String val, String rule) {
        Pattern p = Pattern.compile(rule);
        return p.matcher(val).matches();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        roleCB.getItems().removeAll(roleCB.getItems());
        roleCB.getItems().addAll( "Client", "Patissier");
        TaxField.setVisible(false);
    }
    @FXML
    private void comboAction(ActionEvent event) {

    if(roleCB.getValue().equals("Patissier"))
    {
        TaxField.setVisible(true);
    }
    else
    {
        TaxField.setVisible(false);
    }

}
    
    @FXML
    private void ReternToLogin(ActionEvent event) throws IOException {
        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage newstage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/users/Login.fxml"));
        System.out.println("ROOT !");
        Parent root = loader.load();
        Scene scene = new Scene(root);
        System.out.println("NEW SCENE !");

        oldstage.close();
        newstage.setScene(scene);
        newstage.show();

    }

    @FXML
    private void Add_User(ActionEvent event) throws IOException {

        boolean allDataFilledCorrectly = true;

        //  do {nameField.getText();} while (nameField.getText().isEmpty());
        if (nameField.getText().isEmpty()) {

            nameField.setStyle("-fx-border-color: red;");
            nameField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null, "Veuillez saisir votre nom", "Message d'erreur", JOptionPane.ERROR_MESSAGE);

            allDataFilledCorrectly = false;
            System.out.println("SUBSCRIBE !");

        } else if (surnameField.getText().isEmpty()) {

            surnameField.setStyle("-fx-border-color: red;");
            surnameField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null, "Veuillez saisir votre prenom", "Message d'erreur", JOptionPane.ERROR_MESSAGE);

            allDataFilledCorrectly = false;

        } else if (emailField.getText().isEmpty()) {

            emailField.setStyle("-fx-border-color: red;");//-fx-background-color:
            emailField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null, "Veuillez saisir votre adresse e-mail", "Message d'erreur", JOptionPane.ERROR_MESSAGE);

            allDataFilledCorrectly = false;

        } else if (loginField.getText().isEmpty()) {

            loginField.setStyle("-fx-border-color: red;");
            loginField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null, "Veuillez saisir votre login", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
            allDataFilledCorrectly = false;

        } else if (passwordField.getText().isEmpty()) {

            passwordField.setStyle("-fx-border-color: red;");
            passwordField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null, "Veuillez saisir votre password", "Message d'erreur", JOptionPane.ERROR_MESSAGE);

            allDataFilledCorrectly = false;

        } else if (confirmPasswordField.getText().isEmpty()) {

            confirmPasswordField.setStyle("-fx-border-color: red;");
            confirmPasswordField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null,
                    "Veuillez confirmer votre password", "Message d'erreur", JOptionPane.ERROR_MESSAGE);

            allDataFilledCorrectly = false;

        } else if (!confirmPasswordField.getText().equals(passwordField.getText())) {

            passwordField.setStyle("-fx-border-color: red;");
            confirmPasswordField.setStyle("-fx-border-color: red;");
            confirmPasswordField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null, "Vos password ne sont pas identiques", "Message d'erreur", JOptionPane.ERROR_MESSAGE);

            allDataFilledCorrectly = false;
        }   else if (!validate(loginField.getText(),"^[a-zA-Z][a-zA-Z0-9._]*")) {

            confirmPasswordField.setStyle("-fx-border-color: red;");
            confirmPasswordField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null,
                    "Veuillez saisir un login valide", "Message d'erreur", JOptionPane.ERROR_MESSAGE);

            allDataFilledCorrectly = false;

        } else if (!validate(emailField.getText(),"^[a-zA-Z][a-zA-Z0-9._]*@[a-zA-Z0-9._]+[.][a-zA-Z]+$")) {

            passwordField.setStyle("-fx-border-color: red;");
            confirmPasswordField.setStyle("-fx-border-color: red;");
            confirmPasswordField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null, "Veuillez saisir un email valide", "Message d'erreur", JOptionPane.ERROR_MESSAGE);

            allDataFilledCorrectly = false;
        }else if (roleCB.getItems().get(roleCB.getSelectionModel().getSelectedIndex()).equals("Patissier") && TaxField.getText().isEmpty())
        {
            TaxField.setStyle("-fx-border-color: red;");
            TaxField.setStyle("-fx-background-color: red;");
            JOptionPane.showMessageDialog(null,
                    "Un Patissier doit obligatoirement saisir son Identité Fiscale ","Message d'erreur", JOptionPane.ERROR_MESSAGE);
        }
////////////////////////////////////////////////////////////////////////////////////////////////
        Connection cnx = DataBase.getInstance().getCnx();

        try {
            User u = new User();
            String user_login = loginField.getText();

            Statement stm = cnx.createStatement();
            ResultSet result = stm.executeQuery("select * from User where username = \"" + user_login + "\" or email = \"" + emailField.getText() + "\" ");

            while (result.next()) {
                u.setUsername(result.getString(9));
                u.setEmail(result.getString(6));
                System.out.println(" verification BD !");
            }

            if (loginField.getText().equals(u.getUsername())) {

                JOptionPane.showMessageDialog(null, "Ce Login existe déjà!", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
                System.out.println(" login existant");

            } else if (emailField.getText().equals(u.getEmail())) {

                JOptionPane.showMessageDialog(null, "Cet e-mail est déjà utilisé!", "Message d'erreur", JOptionPane.ERROR_MESSAGE);
                System.out.println(" Email existant");

            } else {
                CRUDUser crudUser = new CRUDUser();
                CRUDhystorique crudHystory = new CRUDhystorique();
                hystorique hist = new hystorique();
                User user = new User();
                user.setName(nameField.getText());
                user.setEmail(emailField.getText());
                user.setPassword(passwordField.getText());
                user.setSurname(surnameField.getText());
                user.setAdress(adressField.getText());
                user.setPhoneNum(phoneField.getText());
                user.setUsername(loginField.getText());
                user.setState(0);
                user.setRole(roleCB.getItems().get(roleCB.getSelectionModel().getSelectedIndex()));
                user.setTaxeregnum(
                                user.getRole().equals("Patissier")? 
                                TaxField.getText()
                                :"");
                crudUser.addUser(user);
                
                //"select * from user where username = \"" + user_login + "\" or email = \"" + emailField.getText() + "\" "
               //SELECT `id` FROM `user` where username =  && email = "
               ResultSet result1 = stm.executeQuery("SELECT `id` FROM `User` WHERE `username` = '"+user.getUsername()+"' OR `email` = '"+user.getEmail()+"'");
                while(result1.next())
                {
                    hist.setUserid(result1.getInt(1));
                }
                crudHystory.addHystorique(hist);
                 //hist.setUserid();
                
               
                System.out.println(" user has been added ");

            }
        } catch (SQLException ex) {
            System.err.print("Echec d'ajout utilisateur à la base!");
        }
        Stage oldstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage newstage = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/users/Login.fxml"));
        System.out.println("ROOT !");

        Parent root = loader.load();
        Scene scene = new Scene(root);
        System.out.println("NEW SCENE !");

        oldstage.close();
        newstage.setScene(scene);
        newstage.show();
        Window owner = ValiderUser.getScene().getWindow();
        AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Registration Successful!",
                "Welcome " + nameField.getText());

    }

}
