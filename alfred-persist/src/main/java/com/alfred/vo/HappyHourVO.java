package com.alfred.vo;

import java.util.Date;
import java.util.List;

import com.alfred.model.HappyHourWeek;
import com.alfred.model.ItemHappyHour;

public class HappyHourVO {
	
	private Integer id;

  private String happyHourName;

  private Integer isActive;

  private Date createTime;

  private Date updateTime;
  
  private List<ItemHappyHour> itemHappyList;
  
  private List<HappyHourWeek> happyWeekList;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHappyHourName() {
		return happyHourName;
	}

	public void setHappyHourName(String happyHourName) {
		this.happyHourName = happyHourName;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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

	public List<ItemHappyHour> getItemHappyList() {
		return itemHappyList;
	}

	public void setItemHappyList(List<ItemHappyHour> itemHappyList) {
		this.itemHappyList = itemHappyList;
	}

	public List<HappyHourWeek> getHappyWeekList() {
		return happyWeekList;
	}

	public void setHappyWeekList(List<HappyHourWeek> happyWeekList) {
		this.happyWeekList = happyWeekList;
	}
  
}
