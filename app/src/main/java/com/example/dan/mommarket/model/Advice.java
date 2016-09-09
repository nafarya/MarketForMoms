package com.example.dan.mommarket.model;

/**
 * Created by GEORGY on 28.08.2016.
 */

public class Advice {
    private int id ;
    private String name ;
    private String shortDescription ;
    private String description;
    private String imageURL;
    public Advice(
            int id,
            String name,
            String shortDescription,
            String description,
            String imageUrl
    ) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.shortDescription = shortDescription;
        this.imageURL=imageUrl;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
