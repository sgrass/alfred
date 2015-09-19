package com.alfred.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReportHourly {
    private Integer id;

    private Integer restaurantId;

    private Integer revenueId;

    private Date businessDate;

    private Integer hour;

    private Integer amountQty;

    private BigDecimal amountPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(Integer revenueId) {
        this.revenueId = revenueId;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getAmountQty() {
        return amountQty;
    }

    public void setAmountQty(Integer amountQty) {
        this.amountQty = amountQty;
    }

    public BigDecimal getAmountPrice() {
        return amountPrice;
    }

    public void setAmountPrice(BigDecimal amountPrice) {
        this.amountPrice = amountPrice;
    }
}