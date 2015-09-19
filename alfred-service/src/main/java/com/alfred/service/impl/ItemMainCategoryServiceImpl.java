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
import com.alfred.dao.ItemMainCategoryMapper;
import com.alfred.model.ItemCategory;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemMainCategory;
import com.alfred.service.ItemMainCategoryService;
import com.alfred.vo.ItemMainCategoryVO;

@Transactional
@Service("itemMainCategoryService")
public class ItemMainCategoryServiceImpl implements ItemMainCategoryService {

	@Autowired
	@Qualifier("itemMainCategoryMapper")
	private ItemMainCategoryMapper itemMainCategoryMapper = null;
	
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
	public int insert(ItemMainCategory itemMainCategory) {
		itemMainCategory.setCreateTime(new Date());
		itemMainCategory.setUpdateTime(new Date());
		return itemMainCategoryMapper.insert(itemMainCategory);
	}

	@Override
	public List<ItemMainCategory> selectByParam(ItemMainCategory itemMainCategory) {
		return itemMainCategoryMapper.selectByParam(itemMainCategory);
	}

	@Override
	public ItemMainCategory selectByPrimaryKey(Integer id) {
		return itemMainCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(ItemMainCategory itemMainCategory) {
		itemMainCategory.setUpdateTime(new Date());
		return itemMainCategoryMapper.updateById(itemMainCategory);
	}

	@Override
	public List<ItemMainCategoryVO> selectByRestaurant(Integer restaurantId) {
		return itemMainCategoryMapper.selectByRestaurant(restaurantId);
	}

	@Override
	public int insertCascad(ItemMainCategory itemMainCategory) {
		int resultRow = 0;
		resultRow = this.insert(itemMainCategory);
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setItemMainCategoryId(itemMainCategory.getId());
		itemCategory.setItemCategoryName(itemMainCategory.getMainCategoryName());
		itemCategory.setUserId(itemMainCategory.getUserId());
		itemCategory.setPrinterId(itemMainCategory.getPrinterId());
		itemCategory.setRestaurantId(itemMainCategory.getRestaurantId());
		itemCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
		resultRow += itemCategoryMapper.insert(itemCategory);
		
		return resultRow;
	}

	@Override
	public int deleteCascadById(Integer restaurantId ,Integer mainCateId, Integer userId) throws Exception {
		int resultRow = 0;
		ItemMainCategory itemMainCategory = new ItemMainCategory();
		itemMainCategory.setId(mainCateId);
		itemMainCategory.setRestaurantId(restaurantId);
		itemMainCategory.setUserId(userId);
		itemMainCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		resultRow += this.updateById(itemMainCategory);
		
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setRestaurantId(restaurantId);
		itemCategory.setItemMainCategoryId(mainCateId);
		itemCategory.setUserId(userId);
		itemCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		itemCategory.setUpdateTime(new Date());
		resultRow += itemCategoryMapper.updateByMainCateId(itemCategory);
		
		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setRestaurantId(restaurantId);
		itemDetail.setItemMainCategoryId(mainCateId);
		itemDetail.setUserId(userId);
		itemDetail.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		itemDetail.setUpdateTime(new Date());
		resultRow += itemDetailMapper.updateByMainCateId(itemDetail);
		
		return resultRow;
	}

	@Override
	public int updateCascadById(ItemMainCategory itemMainCategory) throws Exception {
		int resultRow = 0;
		this.updateById(itemMainCategory);
		
		ItemCategory itemCategory = new ItemCategory();
		itemCategory.setRestaurantId(itemMainCategory.getRestaurantId());
		itemCategory.setItemMainCategoryId(itemMainCategory.getId());
		itemCategory.setUserId(itemMainCategory.getUserId());
		itemCategory.setPrinterId(itemMainCategory.getPrinterId());
		itemCategory.setUpdateTime(new Date());
		resultRow += itemCategoryMapper.updateByMainCateId(itemCategory);
		
		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setRestaurantId(itemMainCategory.getRestaurantId());
		itemDetail.setItemMainCategoryId(itemMainCategory.getId());
		itemDetail.setUserId(itemMainCategory.getPrinterId());
		itemDetail.setPrinterId(itemMainCategory.getPrinterId());
		itemDetail.setUpdateTime(new Date());
		resultRow += itemDetailMapper.updateByMainCateId(itemDetail);
		
		return resultRow;
	}

}
