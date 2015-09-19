package com.alfred.vo;

import java.util.List;

import com.alfred.model.ItemModifier;

public class ItemModifierVO {
	private Integer id;

  private Integer restaurantId;

  private Integer revenueId;
  
  private Integer itemCategoryId;
  
  private String itemName;

  private String itemDesc;

  private List<ItemModifier> itemModifierList;

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

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public List<ItemModifier> getItemModifierList() {
		return itemModifierList;
	}

	public void setItemModifierList(List<ItemModifier> itemModifierList) {
		this.itemModifierList = itemModifierList;
	}

	public Integer getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(Integer itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}

}
