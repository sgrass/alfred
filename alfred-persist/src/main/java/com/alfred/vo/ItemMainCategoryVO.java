package com.alfred.vo;

import java.util.Date;
import java.util.List;

import com.alfred.model.ItemCategory;
import com.alfred.model.ItemMainCategory;

public class ItemMainCategoryVO {

	private Integer id;

  private String mainCategoryName;

  private String color;

  private Integer restaurantId;

  private Integer isActive;

  private Integer indexId;

  private Integer userId;
  
  private Integer printerId;

  private Date createTime;

  private Date updateTime;
  
  private List<ItemCategory> itemCategoryList;

  public Integer getId() {
      return id;
  }

  public void setId(Integer id) {
      this.id = id;
  }

  public String getMainCategoryName() {
      return mainCategoryName;
  }

  public void setMainCategoryName(String mainCategoryName) {
      this.mainCategoryName = mainCategoryName == null ? null : mainCategoryName.trim();
  }

  public String getColor() {
      return color;
  }

  public void setColor(String color) {
      this.color = color == null ? null : color.trim();
  }

  public Integer getRestaurantId() {
      return restaurantId;
  }

  public void setRestaurantId(Integer restaurantId) {
      this.restaurantId = restaurantId;
  }

  public Integer getIsActive() {
      return isActive;
  }

  public void setIsActive(Integer isActive) {
      this.isActive = isActive;
  }

  public Integer getIndexId() {
      return indexId;
  }

  public void setIndexId(Integer indexId) {
      this.indexId = indexId;
  }

  public Integer getUserId() {
      return userId;
  }

  public void setUserId(Integer userId) {
      this.userId = userId;
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

	public List<ItemCategory> getItemCategoryList() {
		return itemCategoryList;
	}

	public void setItemCategoryList(List<ItemCategory> itemCategoryList) {
		this.itemCategoryList = itemCategoryList;
	}

	public Integer getPrinterId() {
		return printerId;
	}

	public void setPrinterId(Integer printerId) {
		this.printerId = printerId;
	}

}
