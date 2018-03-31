package CackeProject.Entities;

public class User{
    private String name;
    private String surname;
    private String Adress;
    private String PhoneNum;
    private String Email;
    /**
     * Default Construct
     */
    public User() {

    }

    public User(String name, String surname, String adress, String phoneNum, String email) {
        this.name = name;
        this.surname = surname;
        Adress = adress;
        PhoneNum = phoneNum;
        Email = email;
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
