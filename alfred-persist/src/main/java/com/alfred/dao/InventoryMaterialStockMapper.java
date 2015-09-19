package com.alfred.dao;

import java.util.HashMap;
import java.util.List;

import com.alfred.model.InventoryMaterialStock;
import com.alfred.vo.InventoryMaterialStockVO;

public interface InventoryMaterialStockMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryMaterialStock inventoryMaterialStock);

    List<InventoryMaterialStock> selectByParam(InventoryMaterialStock inventoryMaterialStock);
    
    List<InventoryMaterialStockVO> selectStock(InventoryMaterialStock inventoryMaterialStock);

    InventoryMaterialStock selectByPrimaryKey(Integer id);

    int updateById(InventoryMaterialStock inventoryMaterialStock);
    
    List<InventoryMaterialStock> selectByParamReoprt(HashMap<String,Object> map);
    
    int selectByParamReoprtCount(HashMap<String,Object> map);
    
    
}