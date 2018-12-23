package com.simplemobiletools.calendar.pro.Model;

public class CookingTip {
    private int id;
    private int cookingId;
    private String tip;

    public CookingTip() {
    }

    public CookingTip(int id, int cookingId, String tip) {
        this.id = id;
        this.cookingId = cookingId;
        this.tip = tip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCookingId() {
        return cookingId;
    }

    public void setCookingId(int cookingId) {
        this.cookingId = cookingId;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }
}
