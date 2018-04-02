package CackeProject.Entities;


public class Customer extends User{

    private static final String role = "customer";

    public Customer() {

    }
    public static String getRole() {
        return role;
    }
}
