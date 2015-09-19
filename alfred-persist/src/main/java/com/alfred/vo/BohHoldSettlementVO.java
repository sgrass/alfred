package com.alfred.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.alfred.util.DateUtil;

public class BohHoldSettlementVO {

  private Integer id;

  private Integer restaurantId;

  private Integer revenueId;

  private Integer paymentId;

  private Integer paymentSettId;

  private Integer orderId;

  private Integer billNo;

  private String nameOfPerson;

  private String phone;

  private String remarks;

  private Integer authorizedUserId;
  
  private String authorizedUserName;

  private BigDecimal amount;

  private Integer status;

  private Integer paymentType;

  private Date paidDate;

  private Integer daysDueInt;
  
  private Date daysDue;

  private Date sysCreateTime;

  private Date sysUpdateTime;

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

	public Integer getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}

	public Integer getPaymentSettId() {
		return paymentSettId;
	}

	public void setPaymentSettId(Integer paymentSettId) {
		this.paymentSettId = paymentSettId;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getBillNo() {
		return billNo;
	}

	public void setBillNo(Integer billNo) {
		this.billNo = billNo;
	}

	public String getNameOfPerson() {
		return nameOfPerson;
	}

	public void setNameOfPerson(String nameOfPerson) {
		this.nameOfPerson = nameOfPerson;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getAuthorizedUserId() {
		return authorizedUserId;
	}

	public void setAuthorizedUserId(Integer authorizedUserId) {
		this.authorizedUserId = authorizedUserId;
	}

	public String getAuthorizedUserName() {
		return authorizedUserName;
	}

	public void setAuthorizedUserName(String authorizedUserName) {
		this.authorizedUserName = authorizedUserName;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Date getDaysDue() {
		return daysDue;
	}

	public void setDaysDue(Date daysDue) {
		this.daysDue = daysDue;
		if (daysDue != null) {
			this.daysDueInt = DateUtil.getDaysBetween(daysDue, new Date());
		}
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

	public Integer getDaysDueInt() {
		return daysDueInt;
	}

	public void setDaysDueInt(Integer daysDueInt) {
		this.daysDueInt = daysDueInt;
	}
}
