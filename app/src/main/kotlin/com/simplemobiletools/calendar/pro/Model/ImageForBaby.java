package com.simplemobiletools.calendar.pro.Model;

public class ImageForBaby {
    private int id;
    private String picture;

    public ImageForBaby(int id) {
        this.id = id;
    }

    public ImageForBaby(int id, String picture) {
        this.id = id;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
