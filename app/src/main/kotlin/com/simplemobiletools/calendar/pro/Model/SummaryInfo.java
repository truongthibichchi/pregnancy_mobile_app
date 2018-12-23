package com.simplemobiletools.calendar.pro.Model;

public class SummaryInfo {
    private int id;
    private String weeks;
    private String picture;

    public SummaryInfo() {
    }

    public SummaryInfo(int id, String weeks, String picture) {
        this.id = id;
        this.weeks = weeks;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
