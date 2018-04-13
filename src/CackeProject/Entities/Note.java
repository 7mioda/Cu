package CackeProject.Entities;

/**
 * User's product note and rank
 */
public class Note {
    private Product product;
    private User user;
    private int Rank;
    private String Post;




    public Note(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getRank() {
        return Rank;
    }


    public void setRank(int rank) {
        Rank = rank;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }
}
