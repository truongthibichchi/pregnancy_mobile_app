package org.a3tn.pregnancy_mobile.Model;

public class CookingStep {
    private int id;
    private int cookingId;
    private String detail;

    public CookingStep() {
    }

    public CookingStep(int id, int cookingId, String detail) {
        this.id = id;
        this.cookingId = cookingId;
        this.detail = detail;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
