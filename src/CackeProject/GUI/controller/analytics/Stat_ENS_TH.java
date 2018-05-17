package CackeProject.GUI.controller.analytics;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import CackeProject.Utils.DataBase;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.stage.Stage;

/**
 *
 * @author sofye
 */
public class Stat_ENS_TH extends Application {

    public Stat_ENS_TH() {
    }

    @Override
    public void start(Stage stage) {
        ObservableList data = FXCollections.observableArrayList();
        ObservableList pieChartData = FXCollections.observableArrayList();
        final PieChart chart = new PieChart(pieChartData);
        try {

            Scene scene = new Scene(new Group());
            stage.setTitle("Pourcentage des ensiegnants par theme ");
            stage.setWidth(500);
            stage.setHeight(500);

            String req = "SELECT DISTINCT name FROM `user`";
            Connection cnx = DataBase.getInstance().getCnx();
            Statement st = cnx.createStatement();
            ResultSet res = st.executeQuery(req);
            //ObservableList<PieChart.Data> pieChartData= null;
            //PieChart pieChartData = new PieChart(); 
            int somme;
            while (res.next()) {

                String th = res.getString(1);
                System.out.println(th);
                ResultSet x = cnx.createStatement().executeQuery("select count(*) from user where name like '" + res.getString(1) + "'");
                    while(x.next()){
                // int somme=Integer.parseInt((int) x.getObject(1));
               somme = x.getInt(1);
                data.add(new PieChart.Data(res.getString(1), somme));
                    }
            }
            pieChartData.addAll(data);
            chart.setTitle("Pourcentage des ensiegnants par theme ");
            ((Group) scene.getRoot()).getChildren().add(chart);
            stage.setScene(scene);
            stage.show();

        } catch (SQLException ex) {
            Logger.getLogger(Stat_ENS_TH.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
