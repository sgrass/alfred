package com.alfred.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.ItemCategoryMapper;
import com.alfred.dao.ItemDetailMapper;
import com.alfred.model.ItemCategory;
import com.alfred.model.ItemDetail;
import com.alfred.service.ItemCategoryService;

@Transactional
@Service("itemCategoryService")
public class ItemCategoryServiceImpl implements ItemCategoryService {

	@Autowired
	@Qualifier("itemCategoryMapper")
	private ItemCategoryMapper itemCategoryMapper = null;
	
	@Autowired
	@Qualifier("itemDetailMapper")
	private ItemDetailMapper itemDetailMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(ItemCategory itemCategory) {
		itemCategory.setCreateTime(new Date());
		itemCategory.setUpdateTime(new Date());
		return itemCategoryMapper.insert(itemCategory);
	}

	@Override
	public List<ItemCategory> selectByParam(ItemCategory itemCategory) {
		return itemCategoryMapper.selectByParam(itemCategory);
	}

	@Override
	public ItemCategory selectByPrimaryKey(Integer id) {
		return itemCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(ItemCategory itemCategory) {
		itemCategory.setUpdateTime(new Date());
		return itemCategoryMapper.updateById(itemCategory);
	}

	@Override
	public List<ItemCategory> selectByMainCategory(Integer mainCategoryId) {
		ItemCategory param = new ItemCategory();
//		param.setRestaurantId(restaurantId);
		param.setItemMainCategoryId(mainCategoryId);
		param.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
		
		List<ItemCategory> list = this.selectByParam(param);
		
		if (list != null && list.size() > 0) {
			return list;
		}
		
		return null;
	}

	@Override
	public int deleteCascadById(Integer restaurantId, Integer subCateId, Integer userId) throws Exception {
		int resultRow = 0;
		
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setId(subCateId);
		itemCategory.setRestaurantId(restaurantId);
		itemCategory.setUserId(userId);
		itemCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		resultRow += this.updateById(itemCategory);
		
		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setRestaurantId(restaurantId);
		itemDetail.setItemCategoryId(subCateId);
		itemDetail.setUserId(userId);
		itemDetail.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		itemDetail.setUpdateTime(new Date());
		resultRow += itemDetailMapper.updateBySubCateId(itemDetail);
		
		return resultRow;
	}

	@Override
	public int updateCascadById(ItemCategory itemCategory) throws Exception {
		int resultRow = 0;
		resultRow += this.updateById(itemCategory);
		
		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setRestaurantId(itemCategory.getRestaurantId());
		itemDetail.setItemCategoryId(itemCategory.getId());
		itemDetail.setUserId(itemCategory.getUserId());
		itemDetail.setPrinterId(itemCategory.getPrinterId());
		itemDetail.setUpdateTime(new Date());
		resultRow += itemDetailMapper.updateBySubCateId(itemDetail);
		
		return resultRow;
	}

}
