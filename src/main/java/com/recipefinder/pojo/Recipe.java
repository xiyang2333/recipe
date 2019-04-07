package com.recipefinder.pojo;

import java.util.List;

/**
 * Created by xiyang on 2019/4/7
 */
public class Recipe {
    private String name;
    private List<Ingredients> ingredients;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredients> ingredients) {
        this.ingredients = ingredients;
    }
}
