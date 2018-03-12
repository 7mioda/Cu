package CackeProject.Entities;


import CackeProject.Services.CRUDUser;

public class User{
    private String name;
    private String surname;
    public static CRUDUser CRUD = null;

    /**
     *
     * @param name name of user
     * @param surname surname of user
     */
    public User(String name, String surname) {
        this.name = name;
        this.surname = surname;
        if(User.CRUD==null){
            User.CRUD = new CRUDUser();
        }
    }

    /**
     *
     * @return The name of user
     */

    public String getName() {
        return name;
    }

    /**
     * Set the name of user
     * @param name name of user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The surname of user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set the surname of user
     * @param surname surname of user
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
