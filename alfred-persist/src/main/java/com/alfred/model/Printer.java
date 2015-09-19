package com.alfred.model;

import java.util.Date;

public class Printer {
    private Integer id;

    private String printerGroupName;

    private String printerName;

    private String printerLocation;

    private String printerType;

    private String qPrint;

    private Integer isCashdrawer;

    private Integer companyId;

    private Integer restaurantId;

    private Integer type;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrinterGroupName() {
        return printerGroupName;
    }

    public void setPrinterGroupName(String printerGroupName) {
        this.printerGroupName = printerGroupName == null ? null : printerGroupName.trim();
    }

    public String getPrinterName() {
        return printerName;
    }

    public void setPrinterName(String printerName) {
        this.printerName = printerName == null ? null : printerName.trim();
    }

    public String getPrinterLocation() {
        return printerLocation;
    }

    public void setPrinterLocation(String printerLocation) {
        this.printerLocation = printerLocation == null ? null : printerLocation.trim();
    }

    public String getPrinterType() {
        return printerType;
    }

    public void setPrinterType(String printerType) {
        this.printerType = printerType == null ? null : printerType.trim();
    }

    public String getqPrint() {
        return qPrint;
    }

    public void setqPrint(String qPrint) {
        this.qPrint = qPrint == null ? null : qPrint.trim();
    }

    public Integer getIsCashdrawer() {
        return isCashdrawer;
    }

    public void setIsCashdrawer(Integer isCashdrawer) {
        this.isCashdrawer = isCashdrawer;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
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
}