package com.example.dan.mommarket.model;

import com.example.dan.mommarket.database.CartDataSource;

/**
 * Created by GEORGY on 28.08.2016.
 */

public class Cart {
    private int id;
    private String name;
    private int sum;
    private int shopsCount;
    private String city;
    private String street;
    private String appartment;
    private String comment;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private boolean cash;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
        this.update();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
        this.update();
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
        this.update();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
        this.update();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        this.update();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        this.update();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.update();
    }

    public boolean isCash() {
        return cash;
    }

    public void setCash(boolean cash) {
        this.cash = cash;
        this.update();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        this.update();
    }

    public Cart(int id
            , String name
            , int shopsCount
            , int sum
            , String city
            , String street
            , String appartment
            , String comment
            , String firstName
            , String lastName
            , String phone
            , String email
            , int cash) {
        this.id = id;
        this.name = name;
        this.shopsCount = shopsCount;
        this.sum = sum;
        this.city = city;
        this.street = street;
        this.appartment = appartment;
        this.comment = comment;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.cash = cash == 1 ? true : false;
    }

    public void update() {
        CartDataSource.updateCart(this);
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
