package org.a3tn.pregnancy_mobile.Model;

import java.util.List;

public class CookingDetail {
    private int id;
    private String foodName;
    private String description;
    private List<CookingIngredient> ingredients;
    private List<CookingStep> steps;
    private List<CookingTip> tips;

    public CookingDetail() {
    }

    public CookingDetail(int id, String foodName, String description, List<CookingIngredient> ingredients, List<CookingStep> steps, List<CookingTip> tips) {
        this.id = id;
        this.foodName = foodName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
