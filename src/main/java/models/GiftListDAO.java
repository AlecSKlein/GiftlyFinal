package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alec Klein on 11/8/2015.
 */
public class GiftListDAO {

    private List<GiftDAO> giftList = new ArrayList<GiftDAO>();

    public GiftListDAO(List<GiftDAO> giftList) {
        this.giftList = giftList;
    }

    public List<GiftDAO> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<GiftDAO> giftList) {
        this.giftList = giftList;
    }

}
