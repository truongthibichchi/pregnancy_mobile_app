package com.simplemobiletools.calendar.pro.Model;

public class Sport {
    private int id;
    private String sportName;
    private String benefit;
    private String step;
    private String note;
    private String picture;

    public Sport() {
    }

    public Sport(int id, String sportName, String benefit, String step, String note, String picture) {
        this.id = id;
        this.sportName = sportName;
        this.benefit = benefit;
        this.step = step;
        this.note = note;
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

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

