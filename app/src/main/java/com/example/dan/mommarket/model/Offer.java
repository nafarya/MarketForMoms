package com.example.dan.mommarket.model;

import com.example.dan.mommarket.database.OfferDataSource;

/**
 * Created by dan on 18.08.16.
 */
public class Offer {
    private int offerId;
    private float price;
    private boolean active;
    private int productId;
    private Shop shop;
    private int count;

    public Offer(int id, int shopId, float price) {
        this.offerId = id;
        this.price = price;
        this.shop = new Shop(shopId);
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

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

    public void addToList(int listId) {

        OfferDataSource.addOfferToList(offerId, listId, price);
    }

    public void deleteFromList(int listId) {
        OfferDataSource.deleteOfferFromList(offerId, listId, false);
    }

    public void addToCart() {
        this.addToList(0);
    }

    public void deleteFromCart() {
        this.deleteFromList(0);
    }
}
