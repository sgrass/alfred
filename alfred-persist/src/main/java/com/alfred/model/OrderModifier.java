package com.alfred.model;

import java.math.BigDecimal;
import java.util.Date;

public class OrderModifier {
    private Integer id;

    private Integer orderId;

    private Integer orderDetailId;

    private Integer orderOriginId;

    private Integer userId;

    private Integer itemId;

    private Integer modifierId;

    private Integer modifierNum;

    private Integer status;

    private BigDecimal modifierPrice;

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

    public Integer getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(Integer orderDetailId) {
        this.orderDetailId = orderDetailId;
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

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Integer getModifierNum() {
        return modifierNum;
    }

    public void setModifierNum(Integer modifierNum) {
        this.modifierNum = modifierNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getModifierPrice() {
        return modifierPrice;
    }

    public void setModifierPrice(BigDecimal modifierPrice) {
        this.modifierPrice = modifierPrice;
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