package models;

/**
 * Created by Alec Klein on 11/8/2015.
 */
public class GiftDAO {

    String asin;
    long friendid;
    String description;
    int state;

    public GiftDAO(String asin, long friendid, String description, int state) {
        this.asin = asin;
        this.friendid = friendid;
        this.description = description;
        this.state = state;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
