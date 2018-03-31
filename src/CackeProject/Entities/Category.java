package CackeProject.Entities;


/**
 * Present Categories of products
 */
public class Category {
    private int id;
    private String Designation;
    private String Unit;

    public Category() {
    }
    public Category(String designation, String unit) {
        Designation = designation;
        Unit = unit;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String unit) {
        Unit = unit;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }
}
