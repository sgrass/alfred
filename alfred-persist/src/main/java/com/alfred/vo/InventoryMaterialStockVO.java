package com.alfred.vo;

import java.math.BigDecimal;
import java.util.Date;

public class InventoryMaterialStockVO {
	
	private Integer id;

    private Integer restaurantId;

    private Integer materialId;
    
    private String supplierName;
    
    private String materialName;

    private Integer supplierId;

    private Integer stockQty;

    private BigDecimal stockTotalPrice;

    private BigDecimal unitPrice;

    private Date createTime;

    private Date updateTime;
    
    
    

	public Integer getId() {
		return id;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public Integer getSupplierId() {
		return supplierId;
	}

	public Integer getStockQty() {
		return stockQty;
	}

	public BigDecimal getStockTotalPrice() {
		return stockTotalPrice;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}

	public void setStockQty(Integer stockQty) {
		this.stockQty = stockQty;
	}

	public void setStockTotalPrice(BigDecimal stockTotalPrice) {
		this.stockTotalPrice = stockTotalPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
