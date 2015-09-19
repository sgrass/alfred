package com.alfred.model;

import java.math.BigDecimal;
import java.util.Date;

import com.alfred.util.ConfigHelper;

public class ItemDetail {
    private Integer id;

    private Integer restaurantId;
    
    private Integer itemTemplateId;

    private Integer revenueId;

    private String itemName;

    private String itemDesc;

    private String itemCode;

    private String imgUrl;
    
    private String imgUrl2;

    private BigDecimal price;

    private Integer itemType;

    private Integer printerId;

    private Integer isModifier;

    private Integer itemMainCategoryId;

    private Integer itemCategoryId;

    private Integer isActive;

    private Integer taxCategoryId;

    private Integer isPack;

    private Integer isTakeout;

    private Integer happyHoursId;

    private Integer userId;

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

    public Integer getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(Integer revenueId) {
        this.revenueId = revenueId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode == null ? null : itemCode.trim();
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl == null ? null : ConfigHelper.getString("img.item.path") + imgUrl.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getPrinterId() {
        return printerId;
    }

    public void setPrinterId(Integer printerId) {
        this.printerId = printerId;
    }

    public Integer getIsModifier() {
        return isModifier;
    }

    public void setIsModifier(Integer isModifier) {
        this.isModifier = isModifier;
    }

    public Integer getItemMainCategoryId() {
        return itemMainCategoryId;
    }

    public void setItemMainCategoryId(Integer itemMainCategoryId) {
        this.itemMainCategoryId = itemMainCategoryId;
    }

    public Integer getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Integer itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getTaxCategoryId() {
        return taxCategoryId;
    }

    public void setTaxCategoryId(Integer taxCategoryId) {
        this.taxCategoryId = taxCategoryId;
    }

    public Integer getIsPack() {
        return isPack;
    }

    public void setIsPack(Integer isPack) {
        this.isPack = isPack;
    }

    public Integer getIsTakeout() {
        return isTakeout;
    }

    public void setIsTakeout(Integer isTakeout) {
        this.isTakeout = isTakeout;
    }

    public Integer getHappyHoursId() {
        return happyHoursId;
    }

    public void setHappyHoursId(Integer happyHoursId) {
        this.happyHoursId = happyHoursId;
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

		public String getImgUrl2() {
			return imgUrl;
		}

		public void setImgUrl2(String imgUrl) {
			this.imgUrl = imgUrl == null ? null : imgUrl.trim();;
		}

		public Integer getItemTemplateId() {
			return itemTemplateId;
		}

		public void setItemTemplateId(Integer itemTemplateId) {
			this.itemTemplateId = itemTemplateId;
		}

}