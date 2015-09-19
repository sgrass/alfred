package com.alfred.dao;

import com.alfred.model.InventorySupplier;
import java.util.List;

public interface InventorySupplierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(InventorySupplier inventorySupplier);

    List<InventorySupplier> selectByParam(InventorySupplier inventorySupplier);

    InventorySupplier selectByPrimaryKey(Integer id);

    int updateById(InventorySupplier inventorySupplier);
}