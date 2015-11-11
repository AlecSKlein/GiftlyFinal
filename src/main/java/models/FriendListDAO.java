package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alec Klein on 11/8/2015.
 */
public class FriendListDAO {

    private List<FriendDAO> friendList = new ArrayList<FriendDAO>();

    public FriendListDAO(List<FriendDAO> friendList) {
        this.friendList = friendList;
    }

    public List<FriendDAO> getFriendList() {
        return friendList;
    }

    public void setFriendList(List<FriendDAO> friendList) {
        this.friendList = friendList;
    }
}
