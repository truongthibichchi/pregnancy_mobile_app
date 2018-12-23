package com.simplemobiletools.calendar.pro.Model;

public class Sport {
    private int id;
    private String sportName;
    private String detail;
    private String picture;

    public Sport() {
    }

    public Sport(int id, String sportName, String detail, String picture) {
        this.id = id;
        this.sportName = sportName;
        this.detail = detail;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
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
