package com.alfred.model;

import java.util.Date;

public class RevenueCenter {
    private Integer id;

    private Integer restaurantId;

    private Integer printId;

    private String revName;

    private Integer isActive;

    private Integer happyHourId;

    private Date happyStartTime;

    private Date happyEndTime;

    private Date createTime;

    private Date updateTime;

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

    public Integer getPrintId() {
        return printId;
    }

    public void setPrintId(Integer printId) {
        this.printId = printId;
    }

    public String getRevName() {
        return revName;
    }

    public void setRevName(String revName) {
        this.revName = revName == null ? null : revName.trim();
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getHappyHourId() {
        return happyHourId;
    }

    public void setHappyHourId(Integer happyHourId) {
        this.happyHourId = happyHourId;
    }

    public Date getHappyStartTime() {
        return happyStartTime;
    }

    public void setHappyStartTime(Date happyStartTime) {
        this.happyStartTime = happyStartTime;
    }

    public Date getHappyEndTime() {
        return happyEndTime;
    }

    public void setHappyEndTime(Date happyEndTime) {
        this.happyEndTime = happyEndTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}