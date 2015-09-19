package com.alfred.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.HappyHourWeekMapper;
import com.alfred.model.HappyHourWeek;
import com.alfred.service.HappyHourWeekService;
@Transactional
@Service("happyHourWeekService")
public class HappyHourWeekServiceImpl implements HappyHourWeekService {

	@Autowired
	@Qualifier("happyHourWeekMapper")
	private HappyHourWeekMapper happyHourWeekMapper = null;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(HappyHourWeek happyHourWeek) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HappyHourWeek> selectByParam(HappyHourWeek happyHourWeek) {
		// TODO Auto-generated method stub
		return happyHourWeekMapper.selectByParam(happyHourWeek);
	}

	@Override
	public HappyHourWeek selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(HappyHourWeek happyHourWeek) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<HappyHourWeek> selectWeekByRestaurantId(
			HashMap<String, Integer> map) {
		// TODO Auto-generated method stub
		return happyHourWeekMapper.selectWeekByRestaurantId(map);
	}

}
