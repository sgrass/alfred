package com.alfred.model;

import java.math.BigDecimal;
import java.util.Date;

public class ReportDayTax {
    private Integer id;

    private Integer daySalesId;

    private Integer restaurantId;

    private String restaurantName;

    private Integer revenueId;

    private String revenueName;

    private Date businessDate;

    private Integer taxId;

    private String taxName;

    private BigDecimal taxPercentage;

    private Integer taxQty;

    private BigDecimal taxAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDaySalesId() {
        return daySalesId;
    }

    public void setDaySalesId(Integer daySalesId) {
        this.daySalesId = daySalesId;
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

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
    }

    public Integer getTaxId() {
        return taxId;
    }

    public void setTaxId(Integer taxId) {
        this.taxId = taxId;
    }

    public String getTaxName() {
        return taxName;
    }

    public void setTaxName(String taxName) {
        this.taxName = taxName == null ? null : taxName.trim();
    }

    public BigDecimal getTaxPercentage() {
        return taxPercentage;
    }

    public void setTaxPercentage(BigDecimal taxPercentage) {
        this.taxPercentage = taxPercentage;
    }

    public Integer getTaxQty() {
        return taxQty;
    }

    public void setTaxQty(Integer taxQty) {
        this.taxQty = taxQty;
    }

    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(BigDecimal taxAmount) {
        this.taxAmount = taxAmount;
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
			this.revenueName = revenueName;
		}
}