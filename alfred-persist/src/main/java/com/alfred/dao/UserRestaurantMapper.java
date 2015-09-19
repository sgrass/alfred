package com.alfred.dao;

import com.alfred.model.UserRestaurant;

import java.util.List;
import java.util.Map;

public interface UserRestaurantMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(UserRestaurant userrestaurant);

    List<UserRestaurant> selectByParam(UserRestaurant userrestaurant);

    List<UserRestaurant> selectByRestaurantId(Map<String, Object> map);
    
    UserRestaurant selectByPrimaryKey(Integer id);

    int updateById(UserRestaurant userrestaurant);
    
    int delByRevenueId(UserRestaurant userrestaurant);
    
    int deleteByUserId(Integer id);

    
    
}