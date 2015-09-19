package com.alfred.service;
import java.util.List;
import com.alfred.model.HappyHour;
import com.alfred.model.HappyHourWeek;
import com.alfred.vo.HappyHourVO;
public interface HappyHourService {
	
	  int deleteByPrimaryKey(Integer id) throws Exception;
	
	  int insert(HappyHour happyHour) throws Exception;
	
	  List<HappyHour> selectByParam(HappyHour happyHour) throws Exception;
	
	  HappyHour selectByPrimaryKey(Integer id) throws Exception;
	
	  int updateById(HappyHour happyHour) throws Exception;
	  
	  List<HappyHourVO> selectHappyHourAll(Integer restaurantId) throws Exception;
	  
	  int insert(HappyHour happyHour, List<HappyHourWeek> hwList) throws Exception;
	  
	  int updateById(HappyHour happyHour, List<HappyHourWeek> hwList) throws Exception;
}
