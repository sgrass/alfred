package com.alfred.vo;

import java.util.List;


import com.alfred.model.Tables;

public class PlaceTable {
	
	private Integer id;

    private String placeName;

    private String placeDescription;

    private Integer restaurantId;

    private Integer revenueId;

    private Integer isActive;
    
    private List<Tables> tablesList;

	public Integer getId() {
		return id;
	}

	public String getPlaceName() {
		return placeName;
	}

	public String getPlaceDescription() {
		return placeDescription;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public Integer getRevenueId() {
		return revenueId;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public List<Tables> getTablesList() {
		return tablesList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public void setPlaceDescription(String placeDescription) {
		this.placeDescription = placeDescription;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setRevenueId(Integer revenueId) {
		this.revenueId = revenueId;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public void setTablesList(List<Tables> tablesList) {
		this.tablesList = tablesList;
	}
    
 
    
}
