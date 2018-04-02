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
    /**
     *
     * @param rank
     * @param post
     */

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

    /**
     *
     * @return
     */
    public int getRank() {
        return Rank;
    }

    /**
     *
     * @param rank
     */
    public void setRank(int rank) {
        Rank = rank;
    }

    /**
     *
     * @return
     */
    public String getPost() {
        return Post;
    }

    /**
     *
     * @param post
     */
    public void setPost(String post) {
        Post = post;
    }
}
