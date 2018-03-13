package CackeProject.Entities;


public class Customer extends User{
    private String Adress;
    private String PhoneNum;
    private String Email;

    public Customer(String adress, String phoneNum, String email) {
        Adress = adress;
        PhoneNum = phoneNum;
        Email = email;
    }

    public Customer(String name, String surname, String adress, String phoneNum, String email) {
        super(name, surname);
        Adress = adress;
        PhoneNum = phoneNum;
        Email = email;
    }

    /**
     *
     * @return
     */
    public String getAdress() {
        return Adress;
    }

    /**
     *
     * @param adress
     */
    public void setAdress(String adress) {
        Adress = adress;
    }

    /**
     *
     * @return
     */
    public String getPhoneNum() {
        return PhoneNum;
    }

    /**
     *
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        PhoneNum = phoneNum;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return Email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        Email = email;
    }
}
