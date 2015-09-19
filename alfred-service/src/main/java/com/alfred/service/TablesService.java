package com.alfred.service;

import java.util.List;

import com.alfred.model.Tables;

public interface TablesService {
	
	
	int deleteByPrimaryKey(Integer id) throws Exception;

	int insert(Tables tables) throws Exception;

	List<Tables> selectByParam(Tables tables) throws Exception;
	
	int updateById(Tables tables) throws Exception;
	
    Tables selectByTable(Tables tables) throws Exception;

    Tables selectByPrimaryKey(Integer id) throws Exception;


}
