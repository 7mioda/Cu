import CackeProject.Services.SMSApi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;




/*public class MyMain extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/Registration.fxml"));
        stage.setTitle("CupCacke");
        stage.setScene(new Scene(root,1000,650));
        stage.show();
    }
}*/
public class MyMain {

    public static void main(String[] args) {
        SMSApi.sendSms("+21699213630","rakez%20sdf%20sdf");
    }
}