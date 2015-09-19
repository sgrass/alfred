package com.alfred.vo;

public class ItemDetailVO {
	private Integer id;

  private String itemName;

  private Integer itemMainCategoryId;

  private String itemMainCategoryName;

  private Integer itemCategoryId;
  
  private String itemCategoryName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemMainCategoryId() {
		return itemMainCategoryId;
	}

	public void setItemMainCategoryId(Integer itemMainCategoryId) {
		this.itemMainCategoryId = itemMainCategoryId;
	}

	public String getItemMainCategoryName() {
		return itemMainCategoryName;
	}

	public void setItemMainCategoryName(String itemMainCategoryName) {
		this.itemMainCategoryName = itemMainCategoryName;
	}

	public Integer getItemCategoryId() {
		return itemCategoryId;
	}

	public void setItemCategoryId(Integer itemCategoryId) {
		this.itemCategoryId = itemCategoryId;
	}

	public String getItemCategoryName() {
		return itemCategoryName;
	}

	public void setItemCategoryName(String itemCategoryName) {
		this.itemCategoryName = itemCategoryName;
	}

  
}
