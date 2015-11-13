package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alec Klein on 11/8/2015.
 */
public class InterestListDAO {

    public List<InterestDAO> getInterestList() {
        return interestList;
    }

    public void setGiftList(List<InterestDAO> giftList) {
        this.interestList = interestList;
    }

    public InterestListDAO(List<InterestDAO> interestList) {
        this.interestList = interestList;
    }

    private List<InterestDAO> interestList = new ArrayList<InterestDAO>();
}
