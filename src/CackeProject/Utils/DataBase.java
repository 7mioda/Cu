package CackeProject.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connexion to Mysql DataBase
 */
public class DataBase {
    private String url;
    private String login;
    private String password;
    private Connection cnx;
    private static DataBase instance = null;

    /**
     *
     */
    public DataBase() {
        this.url = "jdbc:mysql://localhost:3306/CapCake";
        this.login = "root";
        this.password = "123456789";
        try {
            this.cnx = DriverManager.getConnection(this.url, this.login,this.password);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     *
     * @param url
     * @param login
     * @param password
     */
    public DataBase(String url ,String login , String password) {
        this.url = url;
        this.login = login;
        this.password = password;
        try {
            this.cnx = DriverManager.getConnection(this.url, this.login, this.password);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    /**
     *
     * @return
     */
    public static DataBase getInstance(){
        if(instance==null){
            instance = new DataBase();
        }
        return instance;
    }

    /**
     *
     * @return
     */
    public Connection getCnx(){
        return this.cnx;
    }
}
