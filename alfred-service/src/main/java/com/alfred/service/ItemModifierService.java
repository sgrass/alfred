package com.alfred.service;
import java.util.List;
import com.alfred.model.ItemModifier;
public interface ItemModifierService {
	
	  int deleteByPrimaryKey(Integer id) throws Exception;
		
	  int deleteByItemId(Integer restaurantId, Integer itemId) throws Exception;
		
	  int deleteBySubCateId(Integer restaurantId, Integer subCateId) throws Exception;
	
	  int insert(Integer restaurantId, Integer subCateId, Integer itemId, Integer... modifierIds) throws Exception;
	  
	  boolean insertSubCateModifier(Integer restaurantId, Integer subCateId, Integer... modifierIds) throws Exception;
	
	  List<ItemModifier> selectByParam(ItemModifier itemModifier) throws Exception;
	  
	  List<ItemModifier> selectItemModifier(Integer restaurantId) throws Exception;
	
	  ItemModifier selectByPrimaryKey(Integer id) throws Exception;
	
	  int updateById(ItemModifier itemModifier) throws Exception;

}
