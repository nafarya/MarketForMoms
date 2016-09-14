package com.example.dan.mommarket.model;

import com.example.dan.mommarket.database.OfferItemDataSource;

/**
 * Created by dan on 18.08.16.
 */
public class OfferItem {
    private int id;
    private int count;
    private Offer offer;
    private Product product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public OfferItem(int id
            , int count
            , int offerId
            , int offerShopId
            , float offerPrice
            , int productId
            , String productName
            , String ProductImageUrl) {
        this.id = id;
        this.count = count;
        this.offer = new Offer(offerId, offerShopId, offerPrice);
        this.product = new Product(productId, productName, ProductImageUrl);
    }

    public void deleteFromList() {
        OfferItemDataSource.deleteOfferItem(id);
    }

    public void updateFromList() {
        OfferItemDataSource.updateOfferItem(id, offer.getPrice(), count);
    }

    public void updateCount() {
        OfferItemDataSource.updateOfferItem(id, offer.getPrice(), count);
    }

    public void deleteOfferItem() {
        OfferItemDataSource.deleteOfferItem(id);
    }
}
