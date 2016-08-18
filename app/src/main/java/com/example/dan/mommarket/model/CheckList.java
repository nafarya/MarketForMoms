package com.example.dan.mommarket.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dan on 18.08.16.
 */

public class CheckList {
    private String name;
    private String type;
    private String startDate;
    private List<Offer> offerList;

    public CheckList(String name, String type) {
        this.name = name;
        this.type = type;
        offerList = new LinkedList<>();
    }

    public void addToOfferList(Offer offer) {
        offerList.add(offer);
    }
}
