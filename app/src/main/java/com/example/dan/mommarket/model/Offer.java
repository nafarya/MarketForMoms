package com.example.dan.mommarket.model;

/**
 * Created by dan on 18.08.16.
 */
public class Offer {
    private float price;
    private boolean active;
    private int productId;

    public Offer(float price, int productId){
        this.price = price;
        this.productId = productId;
    }
}
