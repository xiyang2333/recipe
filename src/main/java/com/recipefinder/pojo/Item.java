package com.recipefinder.pojo;

import java.util.Date;

/**
 * Created by xiyang on 2019/4/7
 */
public class Item {

    private String item;
    private Integer amount;
    private UnitEnum Unit;
    private Date useBy;
    private Integer restAmount;

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
        return Unit;
    }

    public void setUnit(UnitEnum unit) {
        Unit = unit;
    }

    public Date getUseBy() {
        return useBy;
    }

    public void setUseBy(Date useBy) {
        this.useBy = useBy;
    }

    public Integer getRestAmount() {
        return restAmount;
    }

    public void setRestAmount(Integer restAmount) {
        this.restAmount = restAmount;
    }
}
