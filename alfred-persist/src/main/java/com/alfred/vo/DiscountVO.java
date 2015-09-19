package com.alfred.vo;

import java.math.BigDecimal;
import java.util.Date;

public class DiscountVO {
	

    private String revenueName;

    private Date businessDate;

    private Integer billNumber;

    private String tableName;

    private BigDecimal actuallAmount;//打折前

    private BigDecimal discount;//打折后

    private BigDecimal grandTotal;//实收金额

	public String getRevenueName() {
		return revenueName;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public Integer getBillNumber() {
		return billNumber;
	}

	public String getTableName() {
		return tableName;
	}

	public BigDecimal getActuallAmount() {
		return actuallAmount;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public BigDecimal getGrandTotal() {
		return grandTotal;
	}

	public void setRevenueName(String revenueName) {
		this.revenueName = revenueName;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setBillNumber(Integer billNumber) {
		this.billNumber = billNumber;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public void setActuallAmount(BigDecimal actuallAmount) {
		this.actuallAmount = actuallAmount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setGrandTotal(BigDecimal grandTotal) {
		this.grandTotal = grandTotal;
	}
    
    

    
}