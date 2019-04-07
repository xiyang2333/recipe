package com.recipefinder.service;

import com.recipefinder.pojo.Item;
import com.recipefinder.pojo.Recipe;

import java.util.List;

/**
 * Created by xiyang on 2019/4/7
 */
public interface CoreService {
    public String recipeService(List<Item> fridge, List<Recipe> recipeList);

    public List<Item> csvDeal(String str) throws Exception;
}
