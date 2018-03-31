package CackeProject.Entities;

public class Confectioner extends User{

    private String Taxregnum;
    public static final String role = "Confectioner";


    public Confectioner() {

    }

    public Confectioner(String name, String surname, String adress, String phoneNum, String email, String taxregnum) {
        super(name, surname, adress, phoneNum, email);
        Taxregnum = taxregnum;
    }

    public String getTaxregnum() {
        return Taxregnum;
    }

    public void setTaxregnum(String taxregnum) {
        Taxregnum = taxregnum;
    }
}
