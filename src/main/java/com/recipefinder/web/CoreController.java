package com.recipefinder.web;

import com.alibaba.fastjson.JSON;
import com.recipefinder.pojo.Item;
import com.recipefinder.pojo.Recipe;
import com.recipefinder.service.CoreService;
import com.recipefinder.web.entity.RecipeRequest;
import com.recipefinder.web.entity.RecipeResponse;
import com.recipefinder.web.entity.ResponseHeader;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.recipefinder.web.entity.ResponseCode.COMMON_ERROR;
import static com.recipefinder.web.entity.ResponseCode.SUCCESS;
import static com.recipefinder.web.entity.ResponseCode.WRONG_INPUT;

/**
 * Created by xiyang on 2019/4/7
 */
@RestController
@RequestMapping("/recipe")
public class CoreController {
    private static Logger logger = Logger.getLogger(CoreController.class);

    @Autowired
    private CoreService coreService;

    @RequestMapping(value = "/recipe", method = RequestMethod.POST)
    public RecipeResponse getItemList(@RequestBody RecipeRequest request) {
        RecipeResponse response = new RecipeResponse();
        try {
            List<Item> fridge = null;
            List<Recipe> recipes = null;
            try {
                //try to get fridge by csv
                fridge = coreService.csvDeal(request.getFridge());
                recipes = JSON.parseArray(request.getRecipes(), Recipe.class);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
                //return error csv
                response.setResult("Wrong CSV or Wrong Recipes");
                response.setHeader(responseHeaderSet(false, ex.getMessage(), WRONG_INPUT));
            }

            if (fridge != null && recipes != null) {
                String result = coreService.recipeService(fridge, recipes);
                response.setResult(result);
                response.setHeader(responseHeaderSet(true, "", SUCCESS));
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage());
            //return error
            response.setResult(ex.getMessage());
            response.setHeader(responseHeaderSet(false, ex.getMessage(), COMMON_ERROR));
        }
        return response;
    }

    private ResponseHeader responseHeaderSet(Boolean result, String desc, String code) {
        ResponseHeader responseHeader = new ResponseHeader();
        responseHeader.setStatus(result);
        responseHeader.setMessage(desc);
        responseHeader.setCode(code);
        return responseHeader;
    }
}
