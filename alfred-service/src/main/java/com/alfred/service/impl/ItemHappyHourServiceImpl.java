package com.alfred.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.ItemHappyHourMapper;
import com.alfred.model.ItemHappyHour;
import com.alfred.service.ItemHappyHourService;
@Transactional
@Service("itemHappyHourService")
public class ItemHappyHourServiceImpl implements ItemHappyHourService {
	@Autowired
	@Qualifier("itemHappyHourMapper")
	private ItemHappyHourMapper itemHappyHourMapper = null;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return itemHappyHourMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ItemHappyHour itemHappyHour) {
		return itemHappyHourMapper.insert(itemHappyHour);
	}

	@Override
	public List<ItemHappyHour> selectByParam(ItemHappyHour itemHappyHour) {
		return itemHappyHourMapper.selectByParam(itemHappyHour);
	}

	@Override
	public ItemHappyHour selectByPrimaryKey(Integer id) {
		return itemHappyHourMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(ItemHappyHour itemHappyHour) {
		return itemHappyHourMapper.updateById(itemHappyHour);
	}

	@Override
	public List<ItemHappyHour> selectByRestaurantId(Integer restaurantId) {
		HashMap<String, Integer> map=new HashMap<String, Integer>();
		map.put("restaurantId", restaurantId);
		return itemHappyHourMapper.selectByRestaurantId(map);
		
		
	}
	
}
