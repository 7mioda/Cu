package CackeProject.GUI.controller.User;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import CackeProject.Entities.User;
import CackeProject.Entities.hystorique;
import CackeProject.GUI.Alert.AlertHelper;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author AhmedMekni
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button btnLogin;
    @FXML
    private Button subscribeBTN;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        try {
            User u = new User();
            String user_login = usernameField.getText();
            String user_password = passwordField.getText();
            Statement stm = DataBase.getInstance().getCnx().createStatement();
            ResultSet result = stm.executeQuery("select * from User where username = \"" + user_login + "\" ");
            System.out.println("Connexion user");
            while (result.next()) {
                u.setId(result.getInt(1));
                System.out.println("verif user id");
                u.setUsername(result.getString(9));
                System.out.println("verif username");
                u.setPassword(result.getString(10));
                System.out.println("verif Password");
                u.setName(result.getString(2));
                u.setSurname(result.getString(3));
                u.setAdress(result.getString(4));
                u.setPhoneNum(result.getString(5));
                u.setEmail(result.getString(6));
                u.setRole(result.getString(8));
                u.setState(result.getInt(11));
                
            }
            result.close();
             if ((user_login.equals("admin")) && (user_password.equals("admin"))) {
                  Parent subscibe_page = FXMLLoader.load(getClass().getResource("DashbPrincipale.fxml"));
                System.out.println("ROOT !");

                Scene scene = new Scene(subscibe_page);
                System.out.println("NEW SCENE admin!");

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setTitle("admin session");
                stage.setScene(scene);
                stage.show();
                Window owner = btnLogin.getScene().getWindow();
                AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "session",
                "admin " + usernameField.getText());
              
               
                 
                }
             
             else if ((user_login.equals(u.getUsername())) && (user_password.equals(u.getPassword())) ) {
                 CRUDhystorique Crudhist = new CRUDhystorique();
                 hystorique x = Crudhist.getHistoryById(u.getId());
                 
                 if ((user_login.equals(u.getUsername())) && (user_password.equals(u.getPassword()))/*&&(x.getBancount()<9)*/)
                   {
                 if ((u.getState()!=1)){
                     System.out.println("Bienvenue!");
                User.setInstance(u);
                Parent subscibe_page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/ShowProfile.fxml"));
                System.out.println("ROOT !");

                Scene scene = new Scene(subscibe_page);
                System.out.println("NEW SCENE Profile!");

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setTitle("you are in profile");
                stage.setScene(scene);
                stage.show();
                Window owner2 = btnLogin.getScene().getWindow();
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner2, "nice to see you!",
                "Welcome in your profile " + usernameField.getText());
                     
                 } else {
                     
                 JOptionPane.showMessageDialog(null, "ce compte n'existe pas peut etre vous etes bloqué");
                
                }
                           
                    }
                    else {
                        //alert("vous ete encore banné pour: "+(maintenant.getTime()-releasedate.getTime())+" periode");
                            System.out.println("alert");
                            
                    }

                //label2.setText("Bienvenue : " + u.getUsername());
            } else {
                System.out.println("Failed to find user!!!!!!!!!!!!");
                
                //label2.setText("Login or password incorrect");
            }
        } catch (SQLException ex) {
            System.err.print("Echec!");
        }

    }

    @FXML
    private void GoToregister(ActionEvent event) throws IOException {
         JOptionPane.showMessageDialog(null, "vous devez remlir vos données pour s'inscrire");
        System.out.println("SUBSCRIBE !");

        Parent subscibe_page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/Registration.fxml"));
        System.out.println("ROOT !");
        // Scene scene = new Scene(root,700,650);
        Scene scene = new Scene(subscibe_page);
        System.out.println("NEW SCENE !");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Inscription");
        stage.setScene(scene);
        stage.show();

    }

}
    
    


