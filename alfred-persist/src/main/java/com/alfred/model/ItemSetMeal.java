package com.alfred.model;

public class ItemSetMeal {
    private Integer id;

    private Integer restaurantId;

    private Integer setMealId;

    private Integer itemId;

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

    public Integer getSetMealId() {
        return setMealId;
    }

    public void setSetMealId(Integer setMealId) {
        this.setMealId = setMealId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }
}