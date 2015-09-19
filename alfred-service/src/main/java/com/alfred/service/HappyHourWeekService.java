package com.alfred.service;
import java.util.HashMap;
import java.util.List;
import com.alfred.model.HappyHourWeek;

public interface HappyHourWeekService {
	
	  int deleteByPrimaryKey(Integer id) throws Exception;

	  int insert(HappyHourWeek happyHourWeek) throws Exception;

	  List<HappyHourWeek> selectByParam(HappyHourWeek happyHourWeek) throws Exception;

	  HappyHourWeek selectByPrimaryKey(Integer id) throws Exception;

	  int updateById(HappyHourWeek happyHourWeek) throws Exception;
	  
	  List<HappyHourWeek> selectWeekByRestaurantId(HashMap<String, Integer> map) throws Exception; 

}
