package com.alfred.model;

import java.math.BigDecimal;
import java.util.Date;

public class RoundAmount {
    private Integer id;

    private Integer orderId;

    private Integer billNo;

    private BigDecimal roundBeforePrice;

    private BigDecimal roundAlfterPrice;

    private BigDecimal roundBalancePrice;

    private Integer restId;

    private Integer revenueId;

    private Integer tableId;

    private Date businessDate;

    private Date createTime;

    private Date updateTime;

    private Date sysCreateTime;

    private Date sysUpdateTime;

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

    public Integer getBillNo() {
        return billNo;
    }

    public void setBillNo(Integer billNo) {
        this.billNo = billNo;
    }

    public BigDecimal getRoundBeforePrice() {
        return roundBeforePrice;
    }

    public void setRoundBeforePrice(BigDecimal roundBeforePrice) {
        this.roundBeforePrice = roundBeforePrice;
    }

    public BigDecimal getRoundAlfterPrice() {
        return roundAlfterPrice;
    }

    public void setRoundAlfterPrice(BigDecimal roundAlfterPrice) {
        this.roundAlfterPrice = roundAlfterPrice;
    }

    public BigDecimal getRoundBalancePrice() {
        return roundBalancePrice;
    }

    public void setRoundBalancePrice(BigDecimal roundBalancePrice) {
        this.roundBalancePrice = roundBalancePrice;
    }

    public Integer getRestId() {
        return restId;
    }

    public void setRestId(Integer restId) {
        this.restId = restId;
    }

    public Integer getRevenueId() {
        return revenueId;
    }

    public void setRevenueId(Integer revenueId) {
        this.revenueId = revenueId;
    }

    public Integer getTableId() {
        return tableId;
    }

    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }

    public Date getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(Date businessDate) {
        this.businessDate = businessDate;
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
}