package CackeProject.Entities;

import CackeProject.Services.Secuirty;

import java.security.NoSuchAlgorithmException;


public class User{
    private int id;
    private String name;
    private String surname;
    private String Username;
    private String password;
    private String Adress;
    private String PhoneNum;
    private String Email;
    /**
     * Default Construct
     */
    public User() {

    }


    public User(int id, String name, String surname, String username, String password, String adress, String phoneNum, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        Username = username;
        this.password = password;
        Adress = adress;
        PhoneNum = phoneNum;
        Email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
    public void setPassword(String password) {
        try {
            this.password= Secuirty.getSecurePassword(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getPhoneNum() {
        return PhoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
