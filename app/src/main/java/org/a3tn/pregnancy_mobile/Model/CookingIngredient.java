package org.a3tn.pregnancy_mobile.Model;

public class CookingIngredient {
    private int id;
    private String ingredient;
    private String mesurement;

    public CookingIngredient() {
    }

    public CookingIngredient(int id, String ingredient, String mesurement) {
        this.id = id;
        this.ingredient = ingredient;
        this.mesurement = mesurement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getMesurement() {
        return mesurement;
    }

    public void setMesurement(String mesurement) {
        this.mesurement = mesurement;
    }
}
