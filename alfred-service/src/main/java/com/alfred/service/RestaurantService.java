package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import com.alfred.model.Restaurant;
import com.alfred.vo.RestaurantUser;

public interface RestaurantService {

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insert(Restaurant restaurant) throws Exception;

	List<Restaurant> selectByParam(Restaurant restaurant) throws Exception;

	List<Restaurant> selectByCompany(Integer companyId) throws Exception;
	
	Restaurant selectByPrimaryKey(Integer id) throws Exception;
	
	int updateById(Restaurant restaurant) throws Exception;
	
    int updateImgById(Restaurant restaurant)throws Exception;;

	List<Restaurant> selectByStatus(Restaurant restaurant) throws Exception;

	Restaurant selectByRestaurant(Restaurant restaurant) throws Exception;

	List<RestaurantUser> selectByResM(RestaurantUser restaurantUser) throws Exception;
	
    List<Restaurant> selectRestList(HashMap<String, Object> map) throws Exception;


}
