package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alec Klein on 11/8/2015.
 */
public class InterestListDAO {

    public List<InterestDAO> getGiftList() {
        return interestList;
    }

    public void setGiftList(List<InterestDAO> giftList) {
        this.interestList = interestList;
    }

    private List<InterestDAO> interestList = new ArrayList<InterestDAO>();
}
