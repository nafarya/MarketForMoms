package com.example.dan.mommarket.model;

import java.util.List;

/**
 * Created by dan on 18.08.16.
 */

public class Product {
    private String name;
    private int productId;
    private String description;
    private float price;
    private ProductCategory productCategory;
    private List<Features> featuresList;
    private List<ItemReference> referenceList;

    public Product(
            int productId,
            String name,
            float price,
            String description) {
        this.name = name;
        this.productId = productId;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Features> getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(List<Features> featuresList) {
        this.featuresList = featuresList;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }

    public List<ItemReference> getReferenceList() {
        return referenceList;
    }

    public void setReferenceList(List<ItemReference> referenceList) {
        this.referenceList = referenceList;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
