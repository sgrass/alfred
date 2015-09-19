package com.alfred.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.InventorySupplierMapper;
import com.alfred.model.InventorySupplier;
import com.alfred.service.InventorySupplierService;

@Transactional
@Service("inventorySupplierService")
public class InventorySupplierServiceImpl implements InventorySupplierService {

	@Autowired
	@Qualifier("inventorySupplierMapper")
	private InventorySupplierMapper inventorySupplierMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {
		return 0;
	}

	@Override
	public int insert(InventorySupplier inventorySupplier) throws Exception {
		inventorySupplier.setCreateTime(new Date());
		inventorySupplier.setUpdateTime(new Date());
		return inventorySupplierMapper.insert(inventorySupplier);
	}

	@Override
	public List<InventorySupplier> selectByParam(InventorySupplier inventorySupplier) throws Exception {
		return inventorySupplierMapper.selectByParam(inventorySupplier);
	}

	@Override
	public InventorySupplier selectByPrimaryKey(Integer id) throws Exception {
		return inventorySupplierMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(InventorySupplier inventorySupplier) throws Exception {
		inventorySupplier.setUpdateTime(new Date());
		return inventorySupplierMapper.updateById(inventorySupplier);
	}

}
