package com.alfred.dao;

import com.alfred.model.InventoryRawMaterial;
import com.alfred.vo.InventoryRawMaterialVO;

import java.util.HashMap;
import java.util.List;

public interface InventoryRawMaterialMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventoryRawMaterial inventoryRawMaterial);

    List<InventoryRawMaterial> selectByParam(InventoryRawMaterial inventoryRawMaterial);

    InventoryRawMaterial selectByPrimaryKey(Integer id);

    int updateById(InventoryRawMaterial inventoryRawMaterial);

    int updateAddQtyById(InventoryRawMaterial inventoryRawMaterial);
    
    int updateSubQtyById(InventoryRawMaterial inventoryRawMaterial);
    
    List<InventoryRawMaterialVO> selectByParamReoprt(HashMap<String,Object> map);
    
    int selectByParamReoprtCount (HashMap<String,Object> map);
    
    
}