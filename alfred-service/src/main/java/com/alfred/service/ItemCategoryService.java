package com.alfred.service;

import com.alfred.model.ItemCategory;

import java.util.List;

public interface ItemCategoryService {
	
	  int deleteByPrimaryKey(Integer id) throws Exception;
	  
	  int deleteCascadById(Integer restaurantId ,Integer subCateId, Integer userId) throws Exception ;
	
	  int insert(ItemCategory itemCategory) throws Exception;
	
	  List<ItemCategory> selectByParam(ItemCategory itemCategory) throws Exception;
	
	  ItemCategory selectByPrimaryKey(Integer id) throws Exception;
	
	  int updateById(ItemCategory itemCategory) throws Exception;
	  
	  int updateCascadById(ItemCategory itemCategory) throws Exception;
	  
	  List<ItemCategory> selectByMainCategory(Integer mainCategoryId) throws Exception;
}