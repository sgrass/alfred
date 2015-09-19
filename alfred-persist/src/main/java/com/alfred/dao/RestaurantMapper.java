package com.alfred.dao;

import com.alfred.model.Restaurant;
import com.alfred.vo.RestaurantUser;

import java.util.HashMap;
import java.util.List;

public interface RestaurantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Restaurant restaurant);

    List<Restaurant> selectByParam(Restaurant restaurant);
    
    List<Restaurant> selectByStatus(Restaurant restaurant);
    
    List<Restaurant> selectRestList(HashMap<String, Object> map);
    
    List<Restaurant> selectByRestaurant(Restaurant restaurant);
    
    Restaurant selectByPrimaryKey(Integer id);

    int updateById(Restaurant restaurant);
    
    List<RestaurantUser> selectByResM(RestaurantUser restaurantUser);
    
    int updateImgById(Restaurant restaurant);
    
}

