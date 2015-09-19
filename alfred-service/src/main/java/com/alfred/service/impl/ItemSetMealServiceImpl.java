package com.alfred.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.ItemDetailMapper;
import com.alfred.dao.ItemSetMealMapper;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemSetMeal;
import com.alfred.pagination.Pagination;
import com.alfred.service.ItemSetMealService;
import com.alfred.util.MapUtil;
import com.alfred.vo.ItemSetMealVO;
@Transactional
@Service("itemSetMealService")
public class ItemSetMealServiceImpl implements ItemSetMealService {
	@Autowired
	@Qualifier("itemSetMealMapper")
	private ItemSetMealMapper itemSetMealMapper = null;
	
	
	@Autowired
	@Qualifier("itemDetailMapper")
	private ItemDetailMapper itemDetailMapper = null;

	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ItemDetail itemDetail, String[] itemSetMeal)
			throws Exception {
		itemDetail.setCreateTime(new Date());
		itemDetail.setUpdateTime(new Date());
		itemDetailMapper.insert(itemDetail);
		if(itemSetMeal!=null&&itemSetMeal.length>0){
			for(String s:itemSetMeal){
				ItemSetMeal setMeal=new ItemSetMeal();
				setMeal.setItemId(Integer.parseInt(s));	
				setMeal.setSetMealId(itemDetail.getId());
				setMeal.setRestaurantId(itemDetail.getRestaurantId());
				itemSetMealMapper.insert(setMeal);
			}
		}
		return 0;
	}

	@Override
	public List<ItemSetMeal> selectByParam(ItemSetMeal itemSetMeal)
			throws Exception {
		return itemSetMealMapper.selectByParam(itemSetMeal);
	}

	@Override
	public ItemSetMeal selectByPrimaryKey(Integer id) throws Exception {
		return itemSetMealMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(ItemDetail itemDetail, String[] itemSetMeal)
			throws Exception {
		 itemDetail.setUpdateTime(new Date());
		 itemDetailMapper.updateById(itemDetail);
		 //删除关系表
		 itemSetMealMapper.deleteByPrimaryKey(itemDetail.getId());
		 if(itemSetMeal!=null&&itemSetMeal.length>0){
				for(String s:itemSetMeal){
					ItemSetMeal setMeal=new ItemSetMeal();
					setMeal.setItemId(Integer.parseInt(s));	
					setMeal.setSetMealId(itemDetail.getId());
					System.out.println("itemDetail.getRestaurantId()=="+itemDetail.getRestaurantId());
					setMeal.setRestaurantId(itemDetail.getRestaurantId());
					itemSetMealMapper.insert(setMeal);
				}
			}
		 
		 
		 
		return 0;
	}

	@Override
	public int deleteCascadById(Integer restaurantId, Integer itemId,
			Integer userId) throws Exception {
		int resultRow = 0;
		ItemDetail templateItem = new ItemDetail();
		templateItem.setId(itemId);
		templateItem.setUserId(userId);
		templateItem.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		resultRow += this.updateById(templateItem,null);
		
		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setItemTemplateId(itemId);
		itemDetail.setRestaurantId(restaurantId);
		itemDetail.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		itemDetail.setUserId(userId);
		itemDetail.setUpdateTime(new Date());
		resultRow += itemDetailMapper.updateByItemTemplateId(itemDetail);
		
		itemSetMealMapper.deleteBySetMeal(itemId);

		return resultRow;
	}

	@Override
	public List<ItemSetMealVO> selectSetMealPageByParam(ItemDetail itemDetail, Pagination page) throws Exception {
		Map<String, Object> map = null;;
		map = MapUtil.toMap(itemDetail);
		map.put("startRow", page.getStartRow() > 0 ? page.getStartRow() - 1 : 0);
		map.put("perPageSize", page.getItemsPerPage());
		return itemDetailMapper.selectSetMealPageByParam(map);
	}
	
	
	
}
