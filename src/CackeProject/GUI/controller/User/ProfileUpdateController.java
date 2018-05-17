package CackeProject.GUI.controller.User;


import CackeProject.Entities.User;
import CackeProject.Services.CRUDUser;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author AhmedMekni
 */
public class ProfileUpdateController implements Initializable {

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
    private TextField emailField;
    @FXML
    private AnchorPane edit1;
    @FXML
    private AnchorPane edit11;
    @FXML
    private Button Enregistrer;
    @FXML
    private Button Retour;
    @FXML
    private ImageView logOUT;
    @FXML
    private ImageView profilephoto;
    @FXML
    private Button uplBTNimage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
           User u = User.getInstance();
        if(u!=null)
        {
            nameField.setPromptText("");
            nameField.setText(u.getName());
            
            
            surnameField.setPromptText("");
            surnameField.setText(u.getSurname());
            
            
            adressField.setPromptText("");
            adressField.setText(u.getAdress());
            
            
            phoneField.setPromptText("");
            phoneField.setText(u.getPhoneNum());
            
            
            emailField.setPromptText("");
            emailField.setText(u.getEmail());
          
            
          
            
    }    
}
         @FXML
    private void UPLimage(ActionEvent event) throws IOException {
        //uploadImage(profilephoto);
        //***************************************************************************************
        User u = User.getInstance();
        FileChooser FC =new FileChooser();
	File selectedFile =FC.showOpenDialog(null);
	BufferedImage image = ImageIO.read(selectedFile);
        Image image1 = SwingFXUtils.toFXImage(image, null);
        profilephoto.setImage(image1);
//BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream) profilephoto.getInputMethodRequests()));
//                while (reader.readLine() != null){}
                    
                ;
                
      //**************************************************************************************************
      // Compose the request header
       
        

       
               

//    void uploadImage(ImageView img) {
//         JFileChooser fc = new JFileChooser();
//            fc.setDialogTitle("Please choose an image...");
//            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png", "bmp", "gif");
//            fc.addChoosableFileFilter(filter);
//
//            BufferedImage profilephoto = null;
//            // You should use the parent component instead of null
//            // but it was impossible to tell from the code snippet what that was.
//            if (JFileChooser.APPROVE_OPTION == fc.showOpenDialog(null)) {
//                File selectedFile = fc.getSelectedFile();
//                 Image image = SwingFXUtils.toFXImage(profilephoto, null);
//            img.setImage(image);
//                try {
//                    profilephoto = ImageIO.read(selectedFile);
//                } catch (IOException ex) {
//                }
//        
//    }}
    
//    private void UPLimage (ImageView img) {
//        User u = User.getInstance();
//                   JFileChooser fc = new JFileChooser();
//            fc.setDialogTitle("Please choose an image...");
//            FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG", "jpeg", "jpg", "png", "bmp", "gif");
//            fc.addChoosableFileFilter(filter);
//
//            BufferedImage origImage = null;
//            // You should use the parent component instead of null
//            // but it was impossible to tell from the code snippet what that was.
//            if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
//                File selectedFile = fc.getSelectedFile();
//                try {
//                    origImage = ImageIO.read(selectedFile);
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                }
          }
       
                
        
    
    @FXML
    private void Enregistrer(ActionEvent event) throws SQLException, IOException {
        
                     User original = User.getInstance();
//        if(!nameField.getText().equals(original.getName()) 
//                || !surnameField.getText().equals(original.getSurname()));
//       {

 //   public User(int id, String name, String surname, String username, String password, String adress, String phoneNum, String email)
        User user = new User();
        user.setId(original.getId());
        user.setName(nameField.getText());
        user.setSurname(surnameField.getText());
        user.setUsername(original.getUsername());
        user.setPassword(original.getPassword());
        user.setAdress(adressField.getText());
        user.setPhoneNum(phoneField.getText());
        user.setEmail(emailField.getText());
        user.setState(original.getState());
        user.setRole(original.getRole());
        CRUDUser us = new CRUDUser();
     
        us.updateUser(user,user.getId());
        User.setInstance(user);
       // FXMLLoader loaderin = new FXMLLoader(getClass().getResource("/CackeProject/GUI/fxml/users/ShowProfile"));
//        Node UPDAu = loaderin.load();
        //ProfileController pc = loaderin.getController();
      //  pc.setAdresse(user.getAdress());
      //  pc.setEmail(user.getEmail());
       // pc.setNom(user.getName());
       // pc.setSurname(user.getSurname());
       // pc.setPhoneNum(user.getPhoneNum());
       // pc.setImage(User.getImageProfil());
//       body.getChildren().add(UPDAu);
       // pc.setUser(User);
        
           
      //  Statement stm = DataBase.getInstance().getCnx().createStatement();
        
//             CRUDUser crudUser = new CRUDUser();
//
//                User original = User.getInstance();
//
//            original.setName(nameField.getText());
//            original.setEmail(emailField.getText());
//            original.setSurname(surnameField.getText());
//            original.setAdress(adressField.getText());
//            original.setPhoneNum(phoneField.getText());
//            crudUser.ModifierUser(original);
         System.out.println(" user has been updated ");
         
        
//            String user_login = original.getUsername();
//            
//
//            Statement stm = DataBase.getInstance().getCnx().createStatement();
//            System.out.println("Connexion base  OK!");
//            ResultSet result = stm.executeQuery("select * from user where username = \"" + user_login + "\" ");
                
                Parent subscibe_page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/ShowProfile.fxml"));
                System.out.println("ROOT !");

                Scene scene = new Scene(subscibe_page);
                System.out.println("NEW SCENE Profile Retour!");

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

                stage.setTitle("you are in your profile");
                stage.setScene(scene);
                stage.show();
        
        
    }
    
    
    @FXML
    private void Retour(ActionEvent event) throws IOException {
         System.out.println("you have not update");
                
                Parent subscibe_page = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/ShowProfile.fxml"));
                System.out.println("ROOT !");
                Scene scene = new Scene(subscibe_page);
                System.out.println("NEW SCENE Profile Retour!");
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setTitle("Profile");
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











//    public ImageView getImage() {
//        return ImageView;
//    }
//
//    public void setImage(String image) {
//    File file = new File ("C:/wamp64/www/final/web/public/uploads/brochures/User/"+image);
//        this.image.setImage(new Image(file.toURI().toString()));
//    }
//    
//    
//    public void Button(){
//    if(SessionUser.getImageProfil() == null){
//            ajout.setVisible(true);}
//            else{
//                ajout.setVisible(false);
//            }
//    }

    
    
    
}
