package com.recipefinder.pojo;

/**
 * Created by xiyang on 2019/4/7
 */
public class Ingredients {
    private String item;
    private Integer amount;
    private UnitEnum unit;

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public UnitEnum getUnit() {
        return unit;
    }

    public void setUnit(UnitEnum unit) {
        this.unit = unit;
    }
}
