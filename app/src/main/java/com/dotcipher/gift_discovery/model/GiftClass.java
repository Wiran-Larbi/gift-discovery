package com.dotcipher.gift_discovery.model;

import android.graphics.Bitmap;

public class GiftClass {
    private String title;
    private String description;
    private String category;
    private String occasion;
    private Bitmap image;


    public GiftClass(String title, String description, String category,String occasion, Bitmap image) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.image = image;
    }

    public String getOccasion() {
        return occasion;
    }

    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }

    @Override
    public String toString() {
        return "GiftClass{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", occasion='" + occasion + '\'' +
                ", image=" + image +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
