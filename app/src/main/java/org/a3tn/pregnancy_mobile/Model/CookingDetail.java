package org.a3tn.pregnancy_mobile.Model;

import java.util.List;

public class CookingDetail {
    private int id;
    private String foodName;
    private String detail;
    private String picture;
    private List<CookingIngredient> ingredients;
    private List<CookingStep> steps;
    private List<CookingTip> tips;

    public CookingDetail() {
    }

    public CookingDetail(int id, String foodName, String detail) {
        this.id = id;
        this.foodName = foodName;
        this.detail = detail;
    }

    public CookingDetail(int id, String foodName, String detail, String picture) {
        this.id = id;
        this.foodName = foodName;
        this.detail = detail;
        this.picture = picture;
    }

    public CookingDetail(int id, String foodName, String detail, List<CookingIngredient> ingredients, List<CookingStep> steps, List<CookingTip> tips) {
        this.id = id;
        this.foodName = foodName;
        this.detail = detail;
        this.ingredients = ingredients;
        this.steps = steps;
        this.tips = tips;
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

    public List<CookingIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<CookingIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<CookingStep> getSteps() {
        return steps;
    }

    public void setSteps(List<CookingStep> steps) {
        this.steps = steps;
    }

    public List<CookingTip> getTips() {
        return tips;
    }

    public void setTips(List<CookingTip> tips) {
        this.tips = tips;
    }
}
