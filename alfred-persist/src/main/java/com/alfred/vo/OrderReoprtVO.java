package com.alfred.vo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderReoprtVO {

	private Integer id;

	private Integer orderId;

	private Integer persons;

	private BigDecimal total;

	private Date businessDate;

	private Date createTime;

	private String empName;

	private String itemName;

	private String revName;

	private String tableName;

	private Integer userId;

	private Integer itemId;

	private Integer itemNum;

	private Integer orderDetailStatus;

	private String reason;

	private Integer printStatus;

	private BigDecimal itemPrice;

	private BigDecimal taxPrice;

	private BigDecimal discountPrice;

	private BigDecimal discountRate;

	private BigDecimal realPrice;

	private Integer splitType;

	private Integer orderSplitId;

	private Date updateTime;

	private Date sysCreateTime;

	private Date sysUpdateTime;

	private Integer discountType;

	private BigDecimal modifierPrice;

	public Integer getUserId() {
		return userId;
	}

	public Integer getItemId() {
		return itemId;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public Integer getOrderDetailStatus() {
		return orderDetailStatus;
	}

	public String getReason() {
		return reason;
	}

	public Integer getPrintStatus() {
		return printStatus;
	}

	public BigDecimal getItemPrice() {
		return itemPrice;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public BigDecimal getRealPrice() {
		return realPrice;
	}

	public Integer getSplitType() {
		return splitType;
	}

	public Integer getOrderSplitId() {
		return orderSplitId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public Date getSysCreateTime() {
		return sysCreateTime;
	}

	public Date getSysUpdateTime() {
		return sysUpdateTime;
	}

	public Integer getDiscountType() {
		return discountType;
	}

	public BigDecimal getModifierPrice() {
		return modifierPrice;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public void setOrderDetailStatus(Integer orderDetailStatus) {
		this.orderDetailStatus = orderDetailStatus;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setPrintStatus(Integer printStatus) {
		this.printStatus = printStatus;
	}

	public void setItemPrice(BigDecimal itemPrice) {
		this.itemPrice = itemPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public void setRealPrice(BigDecimal realPrice) {
		this.realPrice = realPrice;
	}

	public void setSplitType(Integer splitType) {
		this.splitType = splitType;
	}

	public void setOrderSplitId(Integer orderSplitId) {
		this.orderSplitId = orderSplitId;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setSysCreateTime(Date sysCreateTime) {
		this.sysCreateTime = sysCreateTime;
	}

	public void setSysUpdateTime(Date sysUpdateTime) {
		this.sysUpdateTime = sysUpdateTime;
	}

	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}

	public void setModifierPrice(BigDecimal modifierPrice) {
		this.modifierPrice = modifierPrice;
	}

	public Integer getId() {
		return id;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public Integer getPersons() {
		return persons;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public String getItemName() {
		return itemName;
	}

	public String getRevName() {
		return revName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public void setPersons(Integer persons) {
		this.persons = persons;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setRevName(String revName) {
		this.revName = revName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

}
