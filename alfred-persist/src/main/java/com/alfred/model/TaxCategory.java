package com.alfred.model;

public class TaxCategory {
    private Integer id;

    private Integer companyId;

    private Integer restaurantId;

    private Integer taxCategoryId;

    private String taxCategoryName;

    private Integer taxId;

    private Integer taxOn;

    private Integer taxOnId;

    private Integer index;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getTaxCategoryId() {
        return taxCategoryId;
    }

    public void setTaxCategoryId(Integer taxCategoryId) {
        this.taxCategoryId = taxCategoryId;
    }

    public String getTaxCategoryName() {
        return taxCategoryName;
    }

    public void setTaxCategoryName(String taxCategoryName) {
        this.taxCategoryName = taxCategoryName == null ? null : taxCategoryName.trim();
    }

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public Integer getTaxOn() {
        return taxOn;
    }

    public void setTaxOn(Integer taxOn) {
        this.taxOn = taxOn;
    }

    public Integer getTaxOnId() {
        return taxOnId;
    }

    public void setTaxOnId(Integer taxOnId) {
        this.taxOnId = taxOnId;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}