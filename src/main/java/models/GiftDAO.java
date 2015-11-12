package models;

/**
 * Created by Alec Klein on 11/8/2015.
 */
public class GiftDAO {

    String asin;
    long friendid;
    String title;
    int state;

    public GiftDAO(String asin, long friendid, String title, int state) {
        this.asin = asin;
        this.friendid = friendid;
        this.title = title;
        this.state = state;
    }

    public GiftDAO(String asin, long friendid, String title) {
        this.asin = asin;
        this.friendid = friendid;
        this.title = title;
    }

    public String getAsin() {
        return asin;
    }

    public void setAsin(String asin) {
        this.asin = asin;
    }

    public long getFriendid() {
        return friendid;
    }

    public void setFriendid(long friendid) {
        this.friendid = friendid;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String title) {
        this.title = title;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
