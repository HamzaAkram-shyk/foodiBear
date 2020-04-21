package com.example.foodibear.Model;

public class Item {

    private String name,Image,itemId;


    public Item(String name, String image, String itemId) {
        this.name = name;
        Image = image;
        this.itemId = itemId;
    }
    public Item() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
}
