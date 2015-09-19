package com.alfred.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetail {
    private Integer id;

    private Integer orderId;

    private Integer orderOriginId;

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

    private Date createTime;

    private Date updateTime;

    private Date sysCreateTime;

    private Date sysUpdateTime;

    private Integer discountType;

    private BigDecimal modifierPrice;
    
    private Integer fromOrderDetailId;
    
    private Integer isFree;
    
    private String specialInstractions;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public Integer getOrderId() {
			return orderId;
		}

		public void setOrderId(Integer orderId) {
			this.orderId = orderId;
		}

		public Integer getOrderOriginId() {
			return orderOriginId;
		}

		public void setOrderOriginId(Integer orderOriginId) {
			this.orderOriginId = orderOriginId;
		}

		public Integer getUserId() {
			return userId;
		}

		public void setUserId(Integer userId) {
			this.userId = userId;
		}

		public Integer getItemId() {
			return itemId;
		}

		public void setItemId(Integer itemId) {
			this.itemId = itemId;
		}

		public Integer getItemNum() {
			return itemNum;
		}

		public void setItemNum(Integer itemNum) {
			this.itemNum = itemNum;
		}

		public Integer getOrderDetailStatus() {
			return orderDetailStatus;
		}

		public void setOrderDetailStatus(Integer orderDetailStatus) {
			this.orderDetailStatus = orderDetailStatus;
		}

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public Integer getPrintStatus() {
			return printStatus;
		}

		public void setPrintStatus(Integer printStatus) {
			this.printStatus = printStatus;
		}

		public BigDecimal getItemPrice() {
			return itemPrice;
		}

		public void setItemPrice(BigDecimal itemPrice) {
			this.itemPrice = itemPrice;
		}

		public BigDecimal getTaxPrice() {
			return taxPrice;
		}

		public void setTaxPrice(BigDecimal taxPrice) {
			this.taxPrice = taxPrice;
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

		public BigDecimal getRealPrice() {
			return realPrice;
		}

		public void setRealPrice(BigDecimal realPrice) {
			this.realPrice = realPrice;
		}

		public Integer getSplitType() {
			return splitType;
		}

		public void setSplitType(Integer splitType) {
			this.splitType = splitType;
		}

		public Integer getOrderSplitId() {
			return orderSplitId;
		}

		public void setOrderSplitId(Integer orderSplitId) {
			this.orderSplitId = orderSplitId;
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

		public Date getSysCreateTime() {
			return sysCreateTime;
		}

		public void setSysCreateTime(Date sysCreateTime) {
			this.sysCreateTime = sysCreateTime;
		}

		public Date getSysUpdateTime() {
			return sysUpdateTime;
		}

		public void setSysUpdateTime(Date sysUpdateTime) {
			this.sysUpdateTime = sysUpdateTime;
		}

		public Integer getDiscountType() {
			return discountType;
		}

		public void setDiscountType(Integer discountType) {
			this.discountType = discountType;
		}

		public BigDecimal getModifierPrice() {
			return modifierPrice;
		}

		public void setModifierPrice(BigDecimal modifierPrice) {
			this.modifierPrice = modifierPrice;
		}

		public Integer getFromOrderDetailId() {
			return fromOrderDetailId;
		}

		public void setFromOrderDetailId(Integer fromOrderDetailId) {
			this.fromOrderDetailId = fromOrderDetailId;
		}

		public Integer getIsFree() {
			return isFree;
		}

		public void setIsFree(Integer isFree) {
			this.isFree = isFree;
		}

		public String getSpecialInstractions() {
			return specialInstractions;
		}

		public void setSpecialInstractions(String specialInstractions) {
			this.specialInstractions = specialInstractions;
		}

}