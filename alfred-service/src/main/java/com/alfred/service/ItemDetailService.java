package com.alfred.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alfred.model.ItemDetail;
import com.alfred.pagination.Pagination;
import com.alfred.vo.ItemDetailVO;
import com.alfred.vo.MainMuenTree;

public interface ItemDetailService {
	
	  int deleteByPrimaryKey(Integer id) throws Exception;
	
	  int insert(ItemDetail itemDetail) throws Exception;
	
	  List<ItemDetail> selectByParam(ItemDetail itemDetail) throws Exception;
	  
	  int selectCountByParam(ItemDetail itemDetail) throws Exception;
	  
	  public List<ItemDetail> selectPageByParam(ItemDetail itemDetail, Pagination page) throws Exception;
	
	  ItemDetail selectByPrimaryKey(Integer id) throws Exception;
	
	  int updateById(ItemDetail itemDetail) throws Exception;
	  
	  List<MainMuenTree> selectItemDetailTree(HashMap<String, Object> map) throws Exception;
	
	  List<MainMuenTree> selectItemUpdateDetailTree(HashMap<String, Object> map) throws Exception;
	
	  List<ItemDetailVO> selectItemName(Integer restaurantId) throws Exception;
	  
	  List<ItemDetail> selectByRevenueId(Integer restaurantId, Integer revenueId, Integer itemType, Integer isActive) throws Exception;
	  
	  int  insertRevenueCenterMenu(String[] itemDetailIds, int revenueId,String[] removeItemDetailId,int restaurantId) throws Exception;
	  
	  ItemDetail queryRevenueId(int id,int itemTemplateId, int revenueId) throws Exception;
	  
	  int insertExcelItems(Map<String, Map<String, Map<String, Object>>> mainMap,int userId,int restaurantId) throws Exception;
	  
	  int deleteCascadById(Integer restaurantId ,Integer itemId, Integer userId) throws Exception;
  

  
}