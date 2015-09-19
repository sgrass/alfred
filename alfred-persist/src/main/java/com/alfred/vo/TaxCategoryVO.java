package com.alfred.vo;

import java.util.List;

import com.alfred.model.TaxCategory;

public class TaxCategoryVO {

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
    
    private List<TaxCategory>  taxCategoryList;

	public Integer getId() {
		return id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public Integer getTaxCategoryId() {
		return taxCategoryId;
	}

	public String getTaxCategoryName() {
		return taxCategoryName;
	}

	public Integer getTaxId() {
		return taxId;
	}

	public Integer getTaxOn() {
		return taxOn;
	}

	public Integer getTaxOnId() {
		return taxOnId;
	}

	public Integer getIndex() {
		return index;
	}

	public Integer getStatus() {
		return status;
	}

	public List<TaxCategory> getTaxCategoryList() {
		return taxCategoryList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setTaxCategoryId(Integer taxCategoryId) {
		this.taxCategoryId = taxCategoryId;
	}

	public void setTaxCategoryName(String taxCategoryName) {
		this.taxCategoryName = taxCategoryName;
	}

	public void setTaxId(Integer taxId) {
		this.taxId = taxId;
	}

	public void setTaxOn(Integer taxOn) {
		this.taxOn = taxOn;
	}

	public void setTaxOnId(Integer taxOnId) {
		this.taxOnId = taxOnId;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setTaxCategoryList(List<TaxCategory> taxCategoryList) {
		this.taxCategoryList = taxCategoryList;
	}

}
