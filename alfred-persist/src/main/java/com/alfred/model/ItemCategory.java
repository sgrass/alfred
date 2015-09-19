package com.alfred.model;

import java.util.Date;

public class ItemCategory {
    private Integer id;

    private String itemCategoryName;

    private Integer superCategoryId;

    private String color;

    private Integer itemMainCategoryId;

    private Integer restaurantId;

    private Integer isActive;

    private Integer indexId;

    private Integer printerId;

    private Integer userId;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName == null ? null : itemCategoryName.trim();
    }

    public Integer getSuperCategoryId() {
        return superCategoryId;
    }

    public void setSuperCategoryId(Integer superCategoryId) {
        this.superCategoryId = superCategoryId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public Integer getItemMainCategoryId() {
        return itemMainCategoryId;
    }

    public void setItemMainCategoryId(Integer itemMainCategoryId) {
        this.itemMainCategoryId = itemMainCategoryId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIndexId() {
        return indexId;
    }

    public void setIndexId(Integer indexId) {
        this.indexId = indexId;
    }

    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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