package com.alfred.service;

import java.util.List;

import com.alfred.model.ItemHappyHour;

public interface ItemHappyHourService {
	
	
	  int deleteByPrimaryKey(Integer id) throws Exception;

	  int insert(ItemHappyHour itemHappyHour) throws Exception;

	  List<ItemHappyHour> selectByParam(ItemHappyHour itemHappyHour) throws Exception;

	  ItemHappyHour selectByPrimaryKey(Integer id) throws Exception;

	  int updateById(ItemHappyHour itemHappyHour) throws Exception;
	  	  
	  List<ItemHappyHour> selectByRestaurantId(Integer restaurantId) throws Exception;

}
