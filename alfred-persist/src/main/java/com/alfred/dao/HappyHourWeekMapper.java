package com.alfred.dao;

import com.alfred.model.HappyHourWeek;

import java.util.HashMap;
import java.util.List;

public interface HappyHourWeekMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByHappyHourId(Integer happyHourId);

    int insert(HappyHourWeek happyHourWeek);

    List<HappyHourWeek> selectByParam(HappyHourWeek happyHourWeek);

    HappyHourWeek selectByPrimaryKey(Integer id);

    int updateById(HappyHourWeek happyHourWeek);
    
    
    List<HappyHourWeek> selectWeekByRestaurantId(HashMap<String, Integer> map); 
    
}