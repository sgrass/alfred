package com.alfred.model;

import java.util.Date;

public class HappyHour {
    private Integer id;

    private Integer restaurantId;
    
    private String happyHourName;

    private Integer isActive;

    private Date createTime;

    private Date updateTime;

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
        this.happyHourName = happyHourName == null ? null : happyHourName.trim();
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

		public Integer getRestaurantId() {
			return restaurantId;
		}

		public void setRestaurantId(Integer restaurantId) {
			this.restaurantId = restaurantId;
		}
}