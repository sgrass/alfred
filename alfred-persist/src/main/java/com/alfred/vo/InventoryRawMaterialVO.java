package com.alfred.vo;

import java.math.BigDecimal;
import java.util.Date;

public class InventoryRawMaterialVO {
    private Integer id;

    private Integer restaurantId;

    private String rawMaterialName;

    private String unitOfMeasurement;

    private BigDecimal quantityMin;

    private BigDecimal quantityCurrent;
    
    private BigDecimal quantityUsable;
    
    private Integer status;

    private Integer version;

    private Date createTime;

    private Date updateTime;
    

    public BigDecimal getQuantityUsable() {
		return quantityUsable;
	}

	public void setQuantityUsable(BigDecimal quantityUsable) {
		this.quantityUsable = quantityUsable;
	}

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

    public String getRawMaterialName() {
        return rawMaterialName;
    }

    public void setRawMaterialName(String rawMaterialName) {
        this.rawMaterialName = rawMaterialName == null ? null : rawMaterialName.trim();
    }

    public String getUnitOfMeasurement() {
        return unitOfMeasurement;
    }

    public void setUnitOfMeasurement(String unitOfMeasurement) {
        this.unitOfMeasurement = unitOfMeasurement == null ? null : unitOfMeasurement.trim();
    }

    public BigDecimal getQuantityMin() {
        return quantityMin;
    }

    public void setQuantityMin(BigDecimal quantityMin) {
        this.quantityMin = quantityMin;
    }

    public BigDecimal getQuantityCurrent() {
        return quantityCurrent;
    }

    public void setQuantityCurrent(BigDecimal quantityCurrent) {
        this.quantityCurrent = quantityCurrent;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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
}