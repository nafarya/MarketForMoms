package com.example.dan.mommarket.model;

/**
 * Created by dan on 18.08.16.
 */
public class ProductCategory {
    private String name;
    private int id;
    private int parentCategoryId;

    public ProductCategory(int id) {
        this.id = id;
    }
    public ProductCategory(int id,String name) {
        this.id = id;
        this.name=name;
    }
    public ProductCategory(int id,String name,int parentCategoryId) {
        this.id = id;
        this.name=name;
        this.parentCategoryId=parentCategoryId;
    }
/**/
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

}
