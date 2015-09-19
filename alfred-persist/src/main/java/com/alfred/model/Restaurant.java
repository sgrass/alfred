package com.alfred.model;

import java.io.Serializable;
import java.util.Date;

import com.alfred.util.ConfigHelper;

public class Restaurant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4346872773423985248L;
	
	
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

	    private String website;

	    private String addressPrint;

	    private String logoUrl;
	    
	    private Integer qrPayment;

	    private String restaurantPrint;

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

		public String getWebsite() {
			return website;
		}

		public String getAddressPrint() {
			return addressPrint;
		}

		public String getLogoUrl() {
			return logoUrl;
		}

		public String getLogoUrl2() {
			return logoUrl;
		}

		public Integer getQrPayment() {
			return qrPayment;
		}

		public String getRestaurantPrint() {
			return restaurantPrint;
		}

		public void setId(Integer id) {
			this.id = id;
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

		public void setWebsite(String website) {
			this.website = website;
		}

		public void setAddressPrint(String addressPrint) {
			this.addressPrint = addressPrint;
		}

		public void setLogoUrl(String logoUrl) {
			
			  this.logoUrl = logoUrl == null ? null : ConfigHelper.getString("img.logo.path") + logoUrl.trim();
		}

		public void setLogoUrl2(String logoUrl) {
			this.logoUrl = logoUrl == null ? null : logoUrl.trim();
		}

		public void setQrPayment(Integer qrPayment) {
			this.qrPayment = qrPayment;
		}

		public void setRestaurantPrint(String restaurantPrint) {
			this.restaurantPrint = restaurantPrint;
		}
	    
	    
	    
	    
    

}