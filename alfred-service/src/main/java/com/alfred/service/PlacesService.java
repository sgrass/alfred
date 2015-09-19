package com.alfred.service;

import java.util.List;

import com.alfred.model.Places;
import com.alfred.vo.PlaceTable;
public interface PlacesService {
	
	
		int deleteByPrimaryKey(Integer id) throws Exception;
	
		int insert(Places places) throws Exception;
	
		List<Places> selectByParam(Places places) throws Exception;
		
		int updateById(Places places) throws Exception;
		
		List<PlaceTable> selectByRevenueId(Integer revenueId) throws Exception;
		
	    Places selectByPlace(Places places) throws Exception;
	    
	    Places selectByPrimaryKey(Integer id) throws Exception;

}
