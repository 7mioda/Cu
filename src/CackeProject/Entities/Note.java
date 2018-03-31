package CackeProject.Entities;


/**
 * User's product note and rank
 */
public class Note {
    private int Rank;
    private String Post;



    /**
     *
     * @param rank
     * @param post
     */
    public Note(int rank, String post) {
        Rank = rank;
        Post = post;
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
