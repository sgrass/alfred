package com.alfred.model;

import java.util.Date;

public class HappyHourWeek {
    private Integer id;

    private Integer happyHourId;

    private String week;

    private Date startTime;

    private Date endTime;

    private Integer isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHappyHourId() {
        return happyHourId;
    }

    public void setHappyHourId(Integer happyHourId) {
        this.happyHourId = happyHourId;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week == null ? null : week.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }
}