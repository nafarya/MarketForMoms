package com.example.dan.mommarket.model;

import android.util.Log;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by dan on 18.08.16.
 */

public class Child {
    private String name;
    private int age;
    private float weight;
    private String sex;
    private float height;
    private String lastUpdate;

    public Child(String sex, int age, float height, float weight) {
        this.sex = sex;
        this.age = age;
        this.height = height;
        this.weight = weight;
        lastUpdate = DateFormat.getDateInstance().format(new Date());
    }
}
