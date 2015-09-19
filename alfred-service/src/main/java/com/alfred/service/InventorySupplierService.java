package com.alfred.service;

import java.util.List;

import com.alfred.model.InventorySupplier;


public interface InventorySupplierService {
	
	int deleteByPrimaryKey(Integer id) throws Exception;

  int insert(InventorySupplier inventorySupplier) throws Exception;

  List<InventorySupplier> selectByParam(InventorySupplier inventorySupplier) throws Exception;

  InventorySupplier selectByPrimaryKey(Integer id) throws Exception;

  int updateById(InventorySupplier inventorySupplier) throws Exception;
}
