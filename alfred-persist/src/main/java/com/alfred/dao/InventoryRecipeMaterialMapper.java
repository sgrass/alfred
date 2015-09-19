package com.alfred.dao;

import com.alfred.model.InventoryRecipeMaterial;
import java.util.List;

public interface InventoryRecipeMaterialMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByItemId(InventoryRecipeMaterial inventoryRecipeMaterial);
    
    int deleteByModifierId(InventoryRecipeMaterial inventoryRecipeMaterial);

    int insert(InventoryRecipeMaterial inventoryRecipeMaterial);

    List<InventoryRecipeMaterial> selectByParam(InventoryRecipeMaterial inventoryRecipeMaterial);

    InventoryRecipeMaterial selectByPrimaryKey(Integer id);

    int updateById(InventoryRecipeMaterial inventoryRecipeMaterial);
    
    int queryUseredMaterial(InventoryRecipeMaterial inventoryRecipeMaterial);
}