package com.alfred.service;

import com.alfred.model.ItemMainCategory;
import com.alfred.vo.ItemMainCategoryVO;

import java.util.List;

public interface ItemMainCategoryService {
	
	  int deleteByPrimaryKey(Integer id) throws Exception;
	
	  int insert(ItemMainCategory itemMainCategory) throws Exception;
	  
	  int insertCascad(ItemMainCategory itemMainCategory) throws Exception;
	
	  List<ItemMainCategory> selectByParam(ItemMainCategory itemMainCategory) throws Exception;
	
	  ItemMainCategory selectByPrimaryKey(Integer id) throws Exception;
	
	  int updateById(ItemMainCategory itemMainCategory) throws Exception;

	  int updateCascadById(ItemMainCategory itemMainCategory) throws Exception;
	  
	  int deleteCascadById(Integer restaurantId ,Integer mainCateId, Integer userId) throws Exception;
	  
	  List<ItemMainCategoryVO> selectByRestaurant(Integer restaurantId) throws Exception;
}