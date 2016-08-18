package com.example.dan.mommarket.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dan on 18.08.16.
 */

public class Shop {
    private String name;
    private float rate;
    private List<ItemReference> itemReferenceList = new LinkedList<>();

    public void addToShopReferencesList(ItemReference itemReference) {
        itemReferenceList.add(itemReference);
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
