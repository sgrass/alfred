package com.alfred.vo;

import java.util.List;

import com.alfred.model.Modifier;


public class ModifierVO {
    private Integer id;

    private Integer restaurantId;

    private Integer type;

    private String categoryName;

    private Integer isActive;
    
    private List<Modifier> modifierList; 

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName == null ? null : categoryName.trim();
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

		public List<Modifier> getModifierList() {
			return modifierList;
		}

		public void setModifierList(List<Modifier> modifierList) {
			this.modifierList = modifierList;
		}
}