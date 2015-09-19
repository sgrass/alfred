package com.alfred.model;

public class UserRestaurant {
    private Integer id;

    private Integer userId;

    private Integer restaurantId;

    private Integer revenueId;

    private Integer kitchenId;

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public Integer getRevenueId() {
		return revenueId;
	}

	public Integer getKitchenId() {
		return kitchenId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setRevenueId(Integer revenueId) {
		this.revenueId = revenueId;
	}

	public void setKitchenId(Integer kitchenId) {
		this.kitchenId = kitchenId;
	}


}