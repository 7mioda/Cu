import CackeProject.Entities.User;


public class MyMain {
    public static void main(String[] args) {
        User.CRUD.showUser().stream().map(c->c.getName()+" "+c.getSurname()).forEach(System.out::println);
    }
}
