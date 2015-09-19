package com.alfred.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.ItemConstant;
import com.alfred.dao.ItemDetailMapper;
import com.alfred.dao.ItemModifierMapper;
import com.alfred.dao.ModifierMapper;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemModifier;
import com.alfred.service.ItemModifierService;
import com.alfred.vo.ItemModifierVO;

@Transactional
@Service("itemModifierService")
public class ItemModifierServiceImpl implements ItemModifierService {

	@Autowired
	@Qualifier("itemModifierMapper")
	private ItemModifierMapper itemModifierMapper = null;
	
	@Autowired
	@Qualifier("itemDetailMapper")
	private ItemDetailMapper itemDetailMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(Integer restaurantId, Integer subCateId, Integer itemId, Integer... modifierIds) {
		int resultRow = 0;
		ItemModifier itemModifier = new ItemModifier();
		itemModifier.setItemId(itemId);
		itemModifier.setRestaurantId(restaurantId);
		
		this.deleteByItemId(itemModifier.getRestaurantId(), itemModifier.getItemId());
		
		for (Integer modifierId : modifierIds) {
			itemModifier.setItemCategoryId(subCateId);
			itemModifier.setModifierCategoryId(modifierId);
			resultRow += itemModifierMapper.insert(itemModifier);
		}
		
		return resultRow;
	}
	
	@Override
	public boolean insertSubCateModifier(Integer restaurantId, Integer subCateId, Integer... modifierIds) {
		ItemModifier itemModifier = new ItemModifier();
		itemModifier.setRestaurantId(restaurantId);
		this.deleteBySubCateId(itemModifier.getRestaurantId(), subCateId);
		
		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setRestaurantId(restaurantId);
		itemDetail.setItemCategoryId(subCateId);
		itemDetail.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
		itemDetail.setItemType(ItemConstant.ITEM_TYPE_TEMPLATE);
		List<ItemDetail> itemDetailList = itemDetailMapper.selectByParam(itemDetail);
		
		for (Integer modifierId : modifierIds) {
			itemModifier.setItemCategoryId(subCateId);
			itemModifier.setModifierCategoryId(modifierId);
			itemModifier.setType(1);
			itemModifierMapper.insert(itemModifier);
		}
		
		for (ItemDetail item : itemDetailList) {
			ItemModifier im = new ItemModifier();
			im.setItemId(item.getId());
			im.setRestaurantId(restaurantId);
			for (Integer modifierId : modifierIds) {
				im.setItemCategoryId(subCateId);
				im.setModifierCategoryId(modifierId);
				itemModifierMapper.insert(im);
			}
		}
		return true;
	}

	@Override
	public List<ItemModifier> selectByParam(ItemModifier itemModifier) {
		return itemModifierMapper.selectByParam(itemModifier);
	}

	@Override
	public List<ItemModifier> selectItemModifier(Integer restaurantId) {
		ItemModifier itemModifier = new ItemModifier();
		itemModifier.setRestaurantId(restaurantId);
//		itemModifier.setItemMainCategoryId(itemMainCategoryId);
//		itemModifier.setItemCategoryId(itemCategoryId);
//		return itemModifierMapper.selectItemModifier(itemModifier);
		return this.selectByParam(itemModifier);
	}

	@Override
	public ItemModifier selectByPrimaryKey(Integer id) {
		return itemModifierMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(ItemModifier itemModifier) {
		return itemModifierMapper.updateById(itemModifier);
	}

	@Override
	public int deleteByItemId(Integer restaurantId, Integer itemId) {
		ItemModifier itemModifier = new ItemModifier();
		itemModifier.setRestaurantId(restaurantId);
		itemModifier.setItemId(itemId);
		return itemModifierMapper.deleteByItemId(itemModifier);
	}

	@Override
	public int deleteBySubCateId(Integer restaurantId, Integer subCateId) {
		ItemModifier itemModifier = new ItemModifier();
		itemModifier.setRestaurantId(restaurantId);
		itemModifier.setItemCategoryId(subCateId);
		return itemModifierMapper.deleteBySubCateId(itemModifier);
	}

}
