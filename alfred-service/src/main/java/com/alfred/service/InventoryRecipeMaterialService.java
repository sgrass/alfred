package com.alfred.service;

import java.util.List;

import com.alfred.model.InventoryRecipeMaterial;


public interface InventoryRecipeMaterialService {
	
  int deleteByPrimaryKey(Integer id) throws Exception;
	
  int deleteByItemId(Integer itemId, Integer restaurantId) throws Exception;
	
  int deleteByModifierId(Integer modifierId, Integer restaurantId) throws Exception;

  int insert(InventoryRecipeMaterial inventoryRecipeMaterial) throws Exception;
  
  public int insertRmList(List<InventoryRecipeMaterial> rmList, Integer itemId, Integer restaurantId) throws Exception;
  
  public int insertMmList(List<InventoryRecipeMaterial> mmList, Integer modifierId, Integer restaurantId) throws Exception;

  List<InventoryRecipeMaterial> selectByParam(InventoryRecipeMaterial inventoryRecipeMaterial) throws Exception;

  InventoryRecipeMaterial selectByPrimaryKey(Integer id) throws Exception;

  int updateById(InventoryRecipeMaterial inventoryRecipeMaterial) throws Exception;
  
  int queryUseredMaterial(Integer modifierId, Integer restaurantId)throws Exception;
}
