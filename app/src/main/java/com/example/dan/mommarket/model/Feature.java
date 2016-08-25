package com.example.dan.mommarket.model;

/**
 * Created by dan on 18.08.16.
 */
public class Feature {
    private int categoryId;
    private int id;
    private String name;
    private String value;

    public Feature(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public Feature(int id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }
    public Feature(int id, String name, String value, int categoryId) {
        this.id = id;
        this.name = name;
        this.value = value;
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
