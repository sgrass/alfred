package com.alfred.dao;

import com.alfred.model.ItemHappyHour;

import java.util.HashMap;
import java.util.List;

public interface ItemHappyHourMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemHappyHour itemHappyHour);

    List<ItemHappyHour> selectByParam(ItemHappyHour itemHappyHour);
    
    

    ItemHappyHour selectByPrimaryKey(Integer id);

    int updateById(ItemHappyHour itemHappyHour);
    
 
    List<ItemHappyHour> selectByRestaurantId(HashMap<String, Integer> map);
}