package CackeProject.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connexion to Mysql DataBase
 */
public class MyConnection {
    private Connection cnx;
    private static MyConnection instance;

    public MyConnection() {
        String url = "jdbc:mysql://localhost:3306/bd esprit";
        String login = "root";
        String pwd = "123456789";
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    public MyConnection getInstance(){
        if(instance==null){
            instance = new MyConnection();
        }
        return instance;
    }
    public Connection getCnx(){
        return Cnx;
    }
}
