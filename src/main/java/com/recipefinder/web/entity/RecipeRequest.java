package com.recipefinder.web.entity;

/**
 * Created by xiyang on 2019/4/7
 */
public class RecipeRequest {
    private String fridge;
    private String recipes;

    public String getFridge() {
        return fridge;
    }

    public void setFridge(String fridge) {
        this.fridge = fridge;
    }

    public String getRecipes() {
        return recipes;
    }

    public void setRecipes(String recipes) {
        this.recipes = recipes;
    }
}
