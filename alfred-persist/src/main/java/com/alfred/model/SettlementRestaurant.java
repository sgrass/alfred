package com.alfred.model;

public class SettlementRestaurant {
    private Integer id;

    private Integer restaurantId;

    private Integer mediaId;

    private Integer adjustmentsId;

    private Integer type;

    private String remarks;

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

    public Integer getMediaId() {
        return mediaId;
    }

    public void setMediaId(Integer mediaId) {
        this.mediaId = mediaId;
    }

    public Integer getAdjustmentsId() {
        return adjustmentsId;
    }

    public void setAdjustmentsId(Integer adjustmentsId) {
        this.adjustmentsId = adjustmentsId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}