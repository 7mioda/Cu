
import org.apache.log4j.BasicConfigurator;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;







public class MyMain extends Application {

    private double xOffset = 0;
    private double yOffset = 0;
    private static class Delta {
        double x, y;
    }

    final Delta dragDelta = new Delta();
    final BooleanProperty inDrag = new SimpleBooleanProperty(false);

    @Override
    public void start(final Stage stage) throws Exception {
        BasicConfigurator.configure();
        Parent root = FXMLLoader.load(getClass().getResource("/CackeProject/GUI/fxml/users/Login.fxml"));
        stage.initStyle(StageStyle.TRANSPARENT);
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                dragDelta.x = stage.getX() - event.getScreenX();
                dragDelta.y = stage.getY() - event.getScreenY();
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                stage.setX(mouseEvent.getSceneX()- dragDelta.x);
                stage.setY(mouseEvent.getSceneY()- dragDelta.y);
                stage.getWidth();
                stage.getHeight();
                stage.getX();
                stage.getY();
                inDrag.set(true);
            }
        });

        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setTitle("CupCacke");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}







