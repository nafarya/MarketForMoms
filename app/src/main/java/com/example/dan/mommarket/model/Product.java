package com.example.dan.mommarket.model;

import com.example.dan.mommarket.database.OfferDataSource;
import com.example.dan.mommarket.database.ProductDataSource;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by dan on 18.08.16.
 */

public class Product {
    private String name;
    private int productId;
    private String description;
    private float price;
    private int categoryId;
    private String categoryName;
    private ProductCategory productCategory;
    private List<Feature> featuresList;
    private List<ItemReference> referenceList;
    private List<String> images;
    private String cardFeatureValue;
    private boolean bookmark = false;

    public Product(
            int productId,
            String name,
            float price,
            String description,
            int categoryId,
            String categoryName,
            List<String> imageUrl,
            String cardFeatureValue,
            int bookmark
    ) {
        this.name = name;
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.images = imageUrl;
        this.cardFeatureValue = cardFeatureValue;
        this.bookmark = bookmark == 1 ? true : false;
    }

    public Product(int productId, String productName, String productImageUrl) {
        this.name = productName;
        this.productId = productId;
        images = new LinkedList<>();
        images.add(productImageUrl);
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCardFeatureValue() {
        return cardFeatureValue;
    }

    public void setCardFeatureValue(String cardFeatureValue) {
        this.cardFeatureValue = cardFeatureValue;
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

    public List<Feature> getFeaturesList() {
        return featuresList;
    }

    public void setFeaturesList(List<Feature> featuresList) {
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

    public String getFirstImage() {
        String defaultPic = "https://cs-ellpic.yandex.net/cms_resources/navigation/pages/42624/7i5duj8hekbnbjuiqm48n1jck4_200x200@x1.png";
        if (images != null) {
            return images.get(0);
        } else {
            return defaultPic;
        }
    }

    public boolean isBookmark() {
        return bookmark;
    }

    public void setBookmark(boolean bookmark) {
        this.bookmark = bookmark;
        if (bookmark) {
            ProductDataSource.addProductToList(productId, 1);
        } else {
            ProductDataSource.deleteProductFromList(productId, 1);
        }
    }
}
