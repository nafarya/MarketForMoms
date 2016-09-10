package com.example.dan.mommarket.model;

/**
 * Created by GEORGY on 28.08.2016.
 */

public class Cart {
    private int id;
    private String name;
    private int sum;
    private int shopsCount;

    public Cart(
            int id,
            String name,
            int shopsCount,
            int sum) {
        this.name = name;
        this.id = id;
        this.sum = sum;
        this.shopsCount = shopsCount;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getShopsCount() {
        return shopsCount;
    }

    public void setShopsCount(int shopsCount) {
        this.shopsCount = shopsCount;
    }

}
