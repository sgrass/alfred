package com.alfred.model;

import java.math.BigDecimal;

public class ItemHappyHour {
    private Integer id;

    private Integer happyHourId;

    private Integer itemMainCategoryId;

    private Integer itemCategoryId;

    private Integer itemId;

    private Integer type;

    private BigDecimal discountPrice;

    private BigDecimal discountRate;

    private Integer freeNum;
    
    private Integer freeItemId;
    
    private String itemMainCategoryName;
    
    private String itemCategoryName;
    
    private String itemName;
    
    private String freeItemName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHappyHourId() {
        return happyHourId;
    }

    public void setHappyHourId(Integer happyHourId) {
        this.happyHourId = happyHourId;
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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    public Integer getFreeNum() {
        return freeNum;
    }

    public void setFreeNum(Integer freeNum) {
        this.freeNum = freeNum;
    }

		public String getItemCategoryName() {
			return itemCategoryName;
		}

		public void setItemCategoryName(String itemCategoryName) {
			this.itemCategoryName = itemCategoryName;
		}

		public String getItemName() {
			return itemName;
		}

		public void setItemName(String itemName) {
			this.itemName = itemName;
		}

		public String getItemMainCategoryName() {
			return itemMainCategoryName;
		}

		public void setItemMainCategoryName(String itemMainCategoryName) {
			this.itemMainCategoryName = itemMainCategoryName;
		}

		public Integer getFreeItemId() {
			return freeItemId;
		}

		public void setFreeItemId(Integer freeItemId) {
			this.freeItemId = freeItemId;
		}

		public String getFreeItemName() {
			return freeItemName;
		}

		public void setFreeItemName(String freeItemName) {
			this.freeItemName = freeItemName;
		}
}