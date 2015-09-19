package com.alfred.service;

import java.util.List;


import java.util.Map;

import com.alfred.model.UserRestaurant;
public interface UserRestaurantService {
	
	int deleteByPrimaryKey(Integer id) throws Exception;

	int insert(UserRestaurant userRestaurant) throws Exception;

	List<UserRestaurant> selectByParam(UserRestaurant userRestaurant) throws Exception;
	
	List<UserRestaurant> selectByRestaurantId(Integer restaurantId, Integer companyId, Integer type) throws Exception;

	UserRestaurant selectByPrimaryKey(Integer id) throws Exception;

	int updateById(UserRestaurant userRestaurant) throws Exception;
	
	int updateManPower(String[] userIds, int revenueId,int restaurantId) throws Exception;
	
	

}

