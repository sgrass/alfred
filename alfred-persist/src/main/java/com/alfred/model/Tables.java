package com.alfred.model;

public class Tables {
    private Integer id;

    private Integer restaurantId;

    private Integer revenueId;

    private Integer placesId;

    private String tableName;

    private Integer tablePacks;

    private Integer isActive;
    
    private Integer status;

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

    public Integer getPlacesId() {
        return placesId;
    }

    public void setPlacesId(Integer placesId) {
        this.placesId = placesId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName == null ? null : tableName.trim();
    }

    public Integer getTablePacks() {
        return tablePacks;
    }

    public void setTablePacks(Integer tablePacks) {
        this.tablePacks = tablePacks;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}
}