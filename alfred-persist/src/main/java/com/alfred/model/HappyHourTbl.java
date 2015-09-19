package com.alfred.model;

public class HappyHourTbl {
    private Integer id;

    private String happyHoursName;

    private Integer isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHappyHoursName() {
        return happyHoursName;
    }

    public void setHappyHoursName(String happyHoursName) {
        this.happyHoursName = happyHoursName == null ? null : happyHoursName.trim();
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}