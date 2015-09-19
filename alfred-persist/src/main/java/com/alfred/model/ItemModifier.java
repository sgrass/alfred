package com.alfred.model;

public class ItemModifier {
    private Integer id;

    private Integer restaurantId;
    
    private Integer itemCategoryId;

    private Integer itemId;

    private Integer modifierId;

    private Integer modifierCategoryId;

    private Integer type;

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

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getModifierId() {
        return modifierId;
    }

    public void setModifierId(Integer modifierId) {
        this.modifierId = modifierId;
    }

    public Integer getModifierCategoryId() {
        return modifierCategoryId;
    }

    public void setModifierCategoryId(Integer modifierCategoryId) {
        this.modifierCategoryId = modifierCategoryId;
    }

		public Integer getItemCategoryId() {
			return itemCategoryId;
		}

		public void setItemCategoryId(Integer itemCategoryId) {
			this.itemCategoryId = itemCategoryId;
		}

		public Integer getType() {
			return type;
		}

		public void setType(Integer type) {
			this.type = type;
		}
}