package com.example.dan.mommarket.model;

/**
 * Created by dan on 18.08.16.
 */
public class ProductCategory {
    private String name;
    private int id;
    private int parentCategory;

    public ProductCategory(int id) {
        this.id = id;
    }
    public ProductCategory(int id,String name) {
        this.id = id;
        this.name=name;
    }
}
