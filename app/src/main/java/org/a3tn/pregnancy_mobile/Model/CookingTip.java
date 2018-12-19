package org.a3tn.pregnancy_mobile.Model;

public class CookingTip {
    private int id;
    private int cookingId;
    private int tip;

    public CookingTip() {
    }

    public CookingTip(int id, int cookingId, int tip) {
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

    public int getTip() {
        return tip;
    }

    public void setTip(int tip) {
        this.tip = tip;
    }
}
