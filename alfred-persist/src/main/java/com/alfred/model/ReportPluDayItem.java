package com.alfred.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReportPluDayItem {
    private Integer id;

    private Integer reportNo;

    private Integer restaurantId;

    private String restaurantName;

    private Integer revenueId;

    private String revenueName;

    private Date businessDate;

    private Integer itemMainCategoryId;

    private String itemMainCategoryName;

    private Integer itemCategoryId;

    private String itemCategoryName;

    private Integer itemDetailId;

    private String itemName;

    private BigDecimal itemPrice;

    private Integer itemCount;

    private BigDecimal itemAmount;

    private Integer itemVoidQty;

    private BigDecimal itemVoidPrice;

    private Integer billVoidQty;

    private BigDecimal billVoidPrice;

    private Integer itemHoldQty;

    private BigDecimal itemHoldPrice;

    private Integer itemFocQty;

    private BigDecimal itemFocPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReportNo() {
        return reportNo;
    }

    public void setReportNo(Integer reportNo) {
        this.reportNo = reportNo;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName == null ? null : restaurantName.trim();
    }

    public Integer getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(Integer revenueId) {
        this.revenueId = revenueId;
    }

    public String getRevenueName() {
        return revenueName;
    }

    public void setRevenueName(String revenueName) {
        this.revenueName = revenueName == null ? null : revenueName.trim();
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public Integer getItemMainCategoryId() {
        return itemMainCategoryId;
    }

    public void setItemMainCategoryId(Integer itemMainCategoryId) {
        this.itemMainCategoryId = itemMainCategoryId;
    }

    public String getItemMainCategoryName() {
        return itemMainCategoryName;
    }

    public void setItemMainCategoryName(String itemMainCategoryName) {
        this.itemMainCategoryName = itemMainCategoryName == null ? null : itemMainCategoryName.trim();
    }

    public Integer getItemCategoryId() {
        return itemCategoryId;
    }

    public void setItemCategoryId(Integer itemCategoryId) {
        this.itemCategoryId = itemCategoryId;
    }

    public String getItemCategoryName() {
        return itemCategoryName;
    }

    public void setItemCategoryName(String itemCategoryName) {
        this.itemCategoryName = itemCategoryName == null ? null : itemCategoryName.trim();
    }

    public Integer getItemDetailId() {
        return itemDetailId;
    }

    public void setItemDetailId(Integer itemDetailId) {
        this.itemDetailId = itemDetailId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public BigDecimal getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(BigDecimal itemAmount) {
        this.itemAmount = itemAmount;
    }

    public Integer getItemVoidQty() {
        return itemVoidQty;
    }

    public void setItemVoidQty(Integer itemVoidQty) {
        this.itemVoidQty = itemVoidQty;
    }

    public BigDecimal getItemVoidPrice() {
        return itemVoidPrice;
    }

    public void setItemVoidPrice(BigDecimal itemVoidPrice) {
        this.itemVoidPrice = itemVoidPrice;
    }

    public Integer getBillVoidQty() {
        return billVoidQty;
    }

    public void setBillVoidQty(Integer billVoidQty) {
        this.billVoidQty = billVoidQty;
    }

    public BigDecimal getBillVoidPrice() {
        return billVoidPrice;
    }

    public void setBillVoidPrice(BigDecimal billVoidPrice) {
        this.billVoidPrice = billVoidPrice;
    }

    public Integer getItemHoldQty() {
        return itemHoldQty;
    }

    public void setItemHoldQty(Integer itemHoldQty) {
        this.itemHoldQty = itemHoldQty;
    }

    public BigDecimal getItemHoldPrice() {
        return itemHoldPrice;
    }

    public void setItemHoldPrice(BigDecimal itemHoldPrice) {
        this.itemHoldPrice = itemHoldPrice;
    }

    public Integer getItemFocQty() {
        return itemFocQty;
    }

    public void setItemFocQty(Integer itemFocQty) {
        this.itemFocQty = itemFocQty;
    }

    public BigDecimal getItemFocPrice() {
        return itemFocPrice;
    }

    public void setItemFocPrice(BigDecimal itemFocPrice) {
        this.itemFocPrice = itemFocPrice;
    }
}