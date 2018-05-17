package CackeProject.Utils.RSSReader;



public interface RSSFeedStore {
    void clear();
    void add(RSSItem currentItem);
}