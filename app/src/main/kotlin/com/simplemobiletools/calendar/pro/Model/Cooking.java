package com.simplemobiletools.calendar.pro.Model;

public class Cooking {
    private int id;
    private String foodName;
    private String detail;
    private String picture;

    public Cooking() {
    }

    public Cooking(int id, String foodName, String detail, String picture) {
        this.id = id;
        this.foodName = foodName;
        this.detail = detail;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
