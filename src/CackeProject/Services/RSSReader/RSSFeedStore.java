package CackeProject.Services.RSSReader;



public interface RSSFeedStore {
    void clear();
    void add(RSSItem currentItem);
}