package com.recipefinder.web.entity;

/**
 * Created by xiyang on 2019/4/7
 */
public class RecipeResponse {
    private ResponseHeader header;

    private String result;

    public ResponseHeader getHeader() {
        return header;
    }

    public void setHeader(ResponseHeader header) {
        this.header = header;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
