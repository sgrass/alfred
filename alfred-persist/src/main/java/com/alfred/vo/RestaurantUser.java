package com.alfred.vo;

import java.util.Date;
import java.util.List;

import com.alfred.model.User;
import com.alfred.util.ConfigHelper;
import com.alfred.util.Skip32Util;

public class RestaurantUser {
	private Integer id;
	   
    private Integer companyId;
    
    private String restaurantName;

    private Integer type;

    private Integer status;

    private String description;

    private String email;

    private String address1;

    private String address2;

    private String telNo;

    private String country;

    private String state;

    private String city;

    private String postalCode;

    private Date createTime;

    private Date updateTime;
    
    private String restaurantKey;
    
    private String website;
    
    private List<User> usersList;

	public Integer getId() {
		return id;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public Integer getType() {
		return type;
	}

	public Integer getStatus() {
		return status;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress1() {
		return address1;
	}

	public String getAddress2() {
		return address2;
	}

	public String getTelNo() {
		return telNo;
	}

	public String getCountry() {
		return country;
	}

	public String getState() {
		return state;
	}

	public String getCity() {
		return city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public List<User> getUsersList() {
		return usersList;
	}

	public void setId(Integer id) {
		this.id = id;
		this.restaurantKey = Skip32Util.skip32encrypt(id, ConfigHelper.getString("skip32.key"));
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUsersList(List<User> usersList) {
		this.usersList = usersList;
	}

	/**
	 * @return the restaurantKey
	 */
	public String getRestaurantKey() {
		return restaurantKey;
	}

	/**
	 * @param restaurantKey the restaurantKey to set
	 */
	public void setRestaurantKey(String restaurantKey) {
		this.restaurantKey = restaurantKey;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}
    
    
   
    
}
