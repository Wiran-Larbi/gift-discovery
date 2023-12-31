package com.dotcipher.gift_discovery.helpers.HomeAdapter;

public class LovedGiftHelper {

    byte[] image;
    String title, description;

    public LovedGiftHelper() {}

    public LovedGiftHelper(byte[] image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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
}
