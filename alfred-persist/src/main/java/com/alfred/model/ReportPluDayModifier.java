package com.alfred.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReportPluDayModifier {
    private Integer id;

    private Integer reportNo;

    private Integer restaurantId;

    private String restaurantName;

    private Integer revenueId;

    private String revenueName;

    private Date businessDate;

    private Integer modifierCategoryId;

    private String modifierCategoryName;

    private Integer modifierId;

    private String modifierName;

    private BigDecimal modifierPrice;

    private Integer modifierCount;

    private BigDecimal billVoidPrice;

    private Integer billVoidCount;

    private BigDecimal voidModifierPrice;

    private Integer voidModifierCount;

    private BigDecimal bohModifierPrice;

    private Integer bohModifierCount;

    private BigDecimal focModifierPrice;

    private Integer focModifierCount;

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

    public Integer getModifierCategoryId() {
        return modifierCategoryId;
    }

    public void setModifierCategoryId(Integer modifierCategoryId) {
        this.modifierCategoryId = modifierCategoryId;
    }

    public String getModifierCategoryName() {
        return modifierCategoryName;
    }

    public void setModifierCategoryName(String modifierCategoryName) {
        this.modifierCategoryName = modifierCategoryName == null ? null : modifierCategoryName.trim();
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public String getModifierName() {
        return modifierName;
    }

    public void setModifierName(String modifierName) {
        this.modifierName = modifierName == null ? null : modifierName.trim();
    }

    public BigDecimal getModifierPrice() {
        return modifierPrice;
    }

    public void setModifierPrice(BigDecimal modifierPrice) {
        this.modifierPrice = modifierPrice;
    }

    public Integer getModifierCount() {
        return modifierCount;
    }

    public void setModifierCount(Integer modifierCount) {
        this.modifierCount = modifierCount;
    }

    public BigDecimal getBillVoidPrice() {
        return billVoidPrice;
    }

    public void setBillVoidPrice(BigDecimal billVoidPrice) {
        this.billVoidPrice = billVoidPrice;
    }

    public Integer getBillVoidCount() {
        return billVoidCount;
    }

    public void setBillVoidCount(Integer billVoidCount) {
        this.billVoidCount = billVoidCount;
    }

    public BigDecimal getVoidModifierPrice() {
        return voidModifierPrice;
    }

    public void setVoidModifierPrice(BigDecimal voidModifierPrice) {
        this.voidModifierPrice = voidModifierPrice;
    }

    public Integer getVoidModifierCount() {
        return voidModifierCount;
    }

    public void setVoidModifierCount(Integer voidModifierCount) {
        this.voidModifierCount = voidModifierCount;
    }

    public BigDecimal getBohModifierPrice() {
        return bohModifierPrice;
    }

    public void setBohModifierPrice(BigDecimal bohModifierPrice) {
        this.bohModifierPrice = bohModifierPrice;
    }

    public Integer getBohModifierCount() {
        return bohModifierCount;
    }

    public void setBohModifierCount(Integer bohModifierCount) {
        this.bohModifierCount = bohModifierCount;
    }

    public BigDecimal getFocModifierPrice() {
        return focModifierPrice;
    }

    public void setFocModifierPrice(BigDecimal focModifierPrice) {
        this.focModifierPrice = focModifierPrice;
    }

    public Integer getFocModifierCount() {
        return focModifierCount;
    }

    public void setFocModifierCount(Integer focModifierCount) {
        this.focModifierCount = focModifierCount;
    }
}