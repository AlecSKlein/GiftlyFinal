package models;

/**
 * Created by Alec Klein on 11/7/2015.
 */
public class FriendDAO {

    long friendid;
    long userid;
    String name;
    String dob;
    int state;

    public FriendDAO(long friendid, long userid, String name, String dob, int state) {
        this.friendid = friendid;
        this.userid = userid;
        this.name = name;
        this.dob = dob;
        this.state = state;
    }

    public long getFriendid() {
        return friendid;
    }

    public void setFriendid(long friendid) {
        this.friendid = friendid;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
