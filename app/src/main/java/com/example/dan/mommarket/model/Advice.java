package com.example.dan.mommarket.model;

/**
 * Created by GEORGY on 28.08.2016.
 */

public class Advice {
    private int id;
    private String name;
    private String shortDescription;
    private String description;
    private String imageURL;
    private String image0;
    private String authorName;
    private String authorText;
    private String authorImage;
    private String text0;
    private String header1;
    private String text1;
    private String image1;
    private String header2;
    private String text2;
    private ProductCategory Category1;
    private ProductCategory Category2;
    private ProductCategory Category3;

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
        this.imageURL = imageUrl;
    }

    public Advice(int id,
                  String name,
                  String shortDescription,
                  String description,
                  String imageURL,
                  String image0,
                  String authorName,
                  String authorText,
                  String authorImage,
                  String text0,
                  String header1,
                  String text1,
                  String image1,
                  String header2,
                  String text2,
                  int category1Id,
                  String category1Name,
                  int category2Id,
                  String category2Name,
                  int category3Id,
                  String category3Name) {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.description = description;
        this.imageURL = imageURL;
        this.image0 = image0;
        this.authorName = authorName;
        this.authorText = authorText;
        this.authorImage = authorImage;
        this.text0 = text0;
        this.header1 = header1;
        this.text1 = text1;
        this.image1 = image1;
        this.header2 = header2;
        this.text2 = text2;
        Category1 = new ProductCategory(category1Id,category1Name);
        Category2 = new ProductCategory(category2Id,category2Name);
        Category3 = new ProductCategory(category3Id,category3Name);
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

    public String getImage0() {
        return image0;
    }

    public void setImage0(String image0) {
        this.image0 = image0;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorText() {
        return authorText;
    }

    public void setAuthorText(String authorText) {
        this.authorText = authorText;
    }

    public String getAuthorImage() {
        return authorImage;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public String getText0() {
        return text0;
    }

    public void setText0(String text0) {
        this.text0 = text0;
    }

    public String getHeader1() {
        return header1;
    }

    public void setHeader1(String header1) {
        this.header1 = header1;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getHeader2() {
        return header2;
    }

    public void setHeader2(String header2) {
        this.header2 = header2;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public ProductCategory getCategory1() {
        return Category1;
    }

    public void setCategory1(ProductCategory category1) {
        Category1 = category1;
    }

    public ProductCategory getCategory2() {
        return Category2;
    }

    public void setCategory2(ProductCategory category2) {
        Category2 = category2;
    }

    public ProductCategory getCategory3() {
        return Category3;
    }

    public void setCategory3(ProductCategory category3) {
        Category3 = category3;
    }
}
