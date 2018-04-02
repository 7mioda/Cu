package CackeProject.Entities;

public class Confectioner extends User{

    private String Taxregnum;
    private static final String role = "Confectioner";


    public Confectioner() {

    }

    public String getTaxregnum() {
        return Taxregnum;
    }

    public void setTaxregnum(String taxregnum) {
        Taxregnum = taxregnum;
    }

    public static String getRole() {
        return role;
    }
}
