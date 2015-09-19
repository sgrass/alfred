package com.alfred.vo;

import java.util.Date;
import java.util.List;

import com.alfred.model.UserRestaurant;
public class UserRestaurantVO {
	
	private Integer id;

	private Integer empId;

	private Integer type;

	private Integer status;

	private String accountName;

	private String userName;

	private String password;

	private String firstName;

	private String lastName;

	private String nickName;

	private Integer companyId;

	private Date createTime;

	private Date updateTime;
	
	private List<UserRestaurant> userRestaurantList;

	public Integer getId() {
		return id;
	}

	public Integer getEmpId() {
		return empId;
	}

	public Integer getType() {
		return type;
	}

	public Integer getStatus() {
		return status;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public List<UserRestaurant> getUserRestaurantList() {
		return userRestaurantList;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setUserRestaurantList(List<UserRestaurant> userRestaurantList) {
		this.userRestaurantList = userRestaurantList;
	}

     
	

}
