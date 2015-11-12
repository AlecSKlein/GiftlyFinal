package models;

/**
 * Created by Alec Klein on 11/7/2015.
 */
public class InterestDAO {

    String interestName;
    long friendid;
    int state;

    public InterestDAO(String interestName, long friendid, int state) {
        this.interestName = interestName;
        this.friendid = friendid;
        this.state = state;
    }

    public InterestDAO(String interestName, long friendid) {
        this.interestName = interestName;
        this.friendid = friendid;
    }

    public String getInterestName() {
        return interestName;
    }

    public void setInterestName(String interestName) {
        this.interestName = interestName;
    }

    public long getFriendid() {
        return friendid;
    }

    public void setFriendid(long friendid) {
        this.friendid = friendid;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
