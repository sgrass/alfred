package com.alfred.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.InventoryRecipeMaterialMapper;
import com.alfred.model.InventoryRecipeMaterial;
import com.alfred.service.InventoryRecipeMaterialService;

@Transactional
@Service("inventoryRecipeMaterialService")
public class InventoryRecipeMaterialServiceImpl implements InventoryRecipeMaterialService {

	@Autowired
	@Qualifier("inventoryRecipeMaterialMapper")
	private InventoryRecipeMaterialMapper inventoryRecipeMaterialMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {
		return inventoryRecipeMaterialMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(InventoryRecipeMaterial inventoryRecipeMaterial) throws Exception {
		return inventoryRecipeMaterialMapper.insert(inventoryRecipeMaterial);
	}
	
	@Override
	public int insertRmList(List<InventoryRecipeMaterial> rmList, Integer itemId, Integer restaurantId) throws Exception {
		int rowResult = 0;
		rowResult += this.deleteByItemId(itemId, restaurantId);
		for (InventoryRecipeMaterial inventoryRecipeMaterial : rmList) {
			rowResult += inventoryRecipeMaterialMapper.insert(inventoryRecipeMaterial);
		}
		return rowResult;
	}
	
	@Override
	public int insertMmList(List<InventoryRecipeMaterial> mmList, Integer modifierId, Integer restaurantId) throws Exception {
		int rowResult = 0;
		rowResult += this.deleteByModifierId(modifierId, restaurantId);
		for (InventoryRecipeMaterial inventoryRecipeMaterial : mmList) {
			rowResult += inventoryRecipeMaterialMapper.insert(inventoryRecipeMaterial);
		}
		return rowResult;
	}

	@Override
	public List<InventoryRecipeMaterial> selectByParam(InventoryRecipeMaterial inventoryRecipeMaterial) throws Exception {
		return inventoryRecipeMaterialMapper.selectByParam(inventoryRecipeMaterial);
	}

	@Override
	public InventoryRecipeMaterial selectByPrimaryKey(Integer id) throws Exception {
		return inventoryRecipeMaterialMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(InventoryRecipeMaterial inventoryRecipeMaterial) throws Exception {
		return inventoryRecipeMaterialMapper.updateById(inventoryRecipeMaterial);
	}

	@Override
	public int deleteByItemId(Integer itemId, Integer restaurantId) throws Exception {
		InventoryRecipeMaterial param = new InventoryRecipeMaterial();
		param.setItemId(itemId);
		param.setRestaurantId(restaurantId);
		return inventoryRecipeMaterialMapper.deleteByItemId(param);
	}
	
	@Override
	public int deleteByModifierId(Integer modifierId, Integer restaurantId) throws Exception {
		InventoryRecipeMaterial param = new InventoryRecipeMaterial();
		param.setModifierId(modifierId);
		param.setRestaurantId(restaurantId);
		return inventoryRecipeMaterialMapper.deleteByModifierId(param);
	}

	@Override
	public int queryUseredMaterial(
			Integer modifierId, Integer restaurantId) throws Exception {
		// TODO Auto-generated method stub
		InventoryRecipeMaterial param = new InventoryRecipeMaterial();
		param.setModifierId(modifierId);
		param.setRestaurantId(restaurantId);
		param.setStatus(1);
		return inventoryRecipeMaterialMapper.queryUseredMaterial(param);
	}

}
