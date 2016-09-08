package com.example.dan.mommarket.model;

/**
 * Created by dan on 18.08.16.
 */
public class Offer {
    private int offerId;
    private float price;
    private boolean active;
    private int productId;
    private Shop shop;

    public Offer(int offerId
            , float price
            , int shopId
            , String shopName
            , int shopDeliveryPrice
            , String shopDeliveryTime
            , int shopReferenceCount
            , int shopRate) {
        this.offerId = offerId;
        this.price = price;
        this.shop = new Shop(shopId
                , shopName
                , shopDeliveryPrice
                , shopDeliveryTime
                , shopReferenceCount
                , shopRate);
    }
}
