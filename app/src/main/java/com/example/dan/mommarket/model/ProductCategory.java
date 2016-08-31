package com.example.dan.mommarket.model;

/**
 * Created by dan on 18.08.16.
 */
public class ProductCategory {
    private String name;
    private int id;
    private int parentCategoryId;
    private String imageURL;
    private String description;

    public ProductCategory(int id) {
        this.id = id;
    }
    public ProductCategory(int id,String name) {
        this.id = id;
        this.name=name;
    }
    public ProductCategory(int id,
                           String name,
                           String description,
                           int parentCategoryId,
                           String imageURL) {
        this.id = id;
        this.name=name;
        this.description=description;
        this.parentCategoryId=parentCategoryId;
        this.imageURL=imageURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
