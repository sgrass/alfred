package com.alfred.vo;

import java.util.Date;
import java.util.List;
import com.alfred.model.PrinterGroup;

public class PrinterGroupVO {
	
	private Integer id;
	
    private String printerGroupName;
    
    private String printerName;
    
    private String printerType;
    
    private Integer companyId;

    private Integer restaurantId;

    private Integer type;

    private Date createTime;

    private Date updateTime;
    
    private List<PrinterGroup> printerGroupList;
    
	public List<PrinterGroup> getPrinterGroupList() {
		return printerGroupList;
	}

	public void setPrinterGroupList(List<PrinterGroup> printerGroupList) {
		this.printerGroupList = printerGroupList;
	}

	public Integer getId() {
		return id;
	}

	public String getPrinterGroupName() {
		return printerGroupName;
	}

	public String getPrinterName() {
		return printerName;
	}

	public String getPrinterType() {
		return printerType;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public Integer getType() {
		return type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setPrinterGroupName(String printerGroupName) {
		this.printerGroupName = printerGroupName;
	}

	public void setPrinterName(String printerName) {
		this.printerName = printerName;
	}

	public void setPrinterType(String printerType) {
		this.printerType = printerType;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}


	

}
