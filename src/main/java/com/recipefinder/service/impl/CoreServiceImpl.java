package com.recipefinder.service.impl;

import com.recipefinder.pojo.Ingredients;
import com.recipefinder.pojo.Item;
import com.recipefinder.pojo.Recipe;
import com.recipefinder.pojo.UnitEnum;
import com.recipefinder.service.CoreService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.*;

/**
 * Created by xiyang on 2019/4/7
 */
@Service
public class CoreServiceImpl implements CoreService {
    private static Logger logger = Logger.getLogger(CoreServiceImpl.class);
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public String recipeService(List<Item> fridge, List<Recipe> recipeList) {
        //init result
        String result = "Order Takeout";
        try {
            Date minTime = null;
            for( Recipe recipe : recipeList){
                Date recipeMinTime = null;
                //set rest amount
                for(Item item : fridge) {
                    item.setRestAmount(item.getAmount());
                }
                //start search
                boolean hasAll = true;
                boolean lessThanMinTime = false;
                for(Ingredients ingredients : recipe.getIngredients()){
                    boolean hasMatch = false;
                    for(Item item : fridge){
                        if(item.getItem().equals(ingredients.getItem()) && item.getUnit().equals(ingredients.getUnit())
                                && item.getRestAmount() >= ingredients.getAmount()){
                            hasMatch = true;
                            item.setRestAmount(item.getRestAmount() - ingredients.getAmount());
                            if(recipeMinTime == null || recipeMinTime.compareTo(item.getUseBy()) > 0){
                                recipeMinTime = item.getUseBy();
                            }
                        }
                    }
                    //do not contain and break to next recipe
                    if(!hasMatch){
                        hasAll = false;
                        break;
                    }
                }
                //has all and early use-by
                if(hasAll && (minTime == null || (recipeMinTime != null && minTime.compareTo(recipeMinTime) > 0))){
                    result = recipe.getName();
                    minTime = recipeMinTime;
                }
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        return result;
    }

    public List<Item> csvDeal(String str) throws Exception {
        List<Item> fridge = new ArrayList<>();
        String csvStr = str;
        String pattern = "\\d{1,2}/\\d{1,2}/\\d\\d\\d\\d";
        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(csvStr);
        //find every one
        while(m.find()){
            String e = m.group();
            String dealStr = csvStr.substring(0, csvStr.indexOf(e) + e.length());
            //split by , to get every value
            String[] dealStrs = dealStr.split(",");
            if(dealStrs.length != 4){
                Exception ex = new Exception("Wrong CSV");
                throw ex;
            } else {
                Date useBy = sdf.parse(dealStrs[3].trim());

                // if over past its use-by do not put into list
                if (useBy.compareTo(new Date()) >= 0) {
                    Item item = new Item();
                    item.setItem(dealStrs[0].trim());
                    item.setAmount(Integer.parseInt(dealStrs[1].trim()));
                    item.setUnit(UnitEnum.valueOf(dealStrs[2].trim()));
                    item.setUseBy(useBy);
                    fridge.add(item);
                }
            }
            //substring to start next round
            csvStr = csvStr.substring(csvStr.indexOf(e) + e.length()).trim();
        }
        return fridge;
    }
}
