package com.example.dan.mommarket.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dan on 18.08.16.
 */

public class Shop {
    private int id;
    private String name;
    private int deliveryPrice;
    private String deliveryTime;
    private Float deliveryTimeFloat;
    private String deliveryPlace;
    private int referenceCount;
    private float rate;
    private int productCartCount;
    private int productCartSum;

    public String getDeliveryPlace() {
        return deliveryPlace;
    }

    public void setDeliveryPlace(String deliveryPlace) {
        this.deliveryPlace = deliveryPlace;
    }

    private int productCartId;
    private List<ItemReference> itemReferenceList = new LinkedList<>();

    public Shop(Shop shop) {
    }

    public Shop(int id

            , String name
            , int deliveryPrice
            , String deliveryTime
            , int referenceCount
            , int rate) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.deliveryTime = deliveryTime;
        this.referenceCount = referenceCount;
        this.rate = rate;
    }

    public Shop(int id
            , String name
            , int deliveryPrice
            , String deliveryTime
            , Float deliveryTimeFloat
            , int referenceCount
            , int rate
            , String deliveryPlace
            , int productCartId
            , int productCartCount
            , int productCartSum) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.deliveryTime = deliveryTime;
        this.referenceCount = referenceCount;
        this.deliveryTimeFloat = deliveryTimeFloat;
        this.rate = rate;
        this.productCartId = productCartId;
        this.productCartCount = productCartCount;
        this.productCartSum = productCartSum;
        this.deliveryPlace = deliveryPlace;

    }

    public Shop(int id
            , String name
            , int deliveryPrice
            , String deliveryTime
            , int referenceCount
            , int rate
            , int productCartId
            , int productCartCount
            , int productCartSum) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.deliveryTime = deliveryTime;
        this.referenceCount = referenceCount;
        this.rate = rate;
        this.productCartId = productCartId;
        this.productCartCount = productCartCount;
        this.productCartSum = productCartSum;
    }

    public Shop(int id) {
        this.id = id;
    }

    public int getReferenceCount() {
        return referenceCount;
    }

    public void setReferenceCount(int referenceCount) {
        this.referenceCount = referenceCount;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getProductCartCount() {
        return productCartCount;
    }

    public void setProductCartCount(int productCartCount) {
        this.productCartCount = productCartCount;
    }

    public int getProductCartSum() {
        return productCartSum;
    }

    public void setProductCartSum(int productCartSum) {
        this.productCartSum = productCartSum;
    }

    public int getProductCartId() {
        return productCartId;
    }

    public void setProductCartId(int productCartId) {
        this.productCartId = productCartId;
    }

    public Float getDeliveryTimeFloat() {
        return deliveryTimeFloat;
    }

    public void setDeliveryTimeFloat(Float deliveryTimeFloat) {
        this.deliveryTimeFloat = deliveryTimeFloat;
    }

}
