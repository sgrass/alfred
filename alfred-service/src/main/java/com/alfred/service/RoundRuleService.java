package com.alfred.service;

import java.util.List;

import com.alfred.model.RoundRule;

public interface RoundRuleService {
		
	  int deleteByPrimaryKey(Integer id) throws Exception;
	
	  int insert(RoundRule roundRule) throws Exception;
	  
	  List<RoundRule> selectByParam(RoundRule roundRule) throws Exception;
	
	  RoundRule selectByCountry(String country) throws Exception;
	
	  RoundRule selectByPrimaryKey(Integer id) throws Exception;
	
	  int updateById(RoundRule roundRule) throws Exception;
}
