package com.alfred.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.HappyHourMapper;
import com.alfred.dao.HappyHourWeekMapper;
import com.alfred.model.HappyHour;
import com.alfred.model.HappyHourWeek;
import com.alfred.service.HappyHourService;
import com.alfred.vo.HappyHourVO;

@Transactional
@Service("happyHourService")
public class HappyHourServiceImpl implements HappyHourService {

	@Autowired
	@Qualifier("happyHourMapper")
	private HappyHourMapper happyHourMapper = null;
	
	@Autowired
	@Qualifier("happyHourWeekMapper")
	private HappyHourWeekMapper happyHourWeekMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(HappyHour happyHour) {
		happyHour.setCreateTime(new Date());
		happyHour.setUpdateTime(new Date());
		return happyHourMapper.insert(happyHour);
	}

	@Override
	public int insert(HappyHour happyHour, List<HappyHourWeek> hwList) {
		this.insert(happyHour);
		Integer happyHourId = happyHour.getId();
		int row = 1;
		for (HappyHourWeek hw : hwList) {
			hw.setHappyHourId(happyHourId);
			row+=happyHourWeekMapper.insert(hw);
		}
		return row;
	}
	
	@Override
	public List<HappyHour> selectByParam(HappyHour happyHour) {
		return happyHourMapper.selectByParam(happyHour);
	}

	@Override
	public HappyHour selectByPrimaryKey(Integer id) {
		return happyHourMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(HappyHour happyHour) {
		happyHour.setUpdateTime(new Date());
		return happyHourMapper.updateById(happyHour);
	}

	@Override
	public List<HappyHourVO> selectHappyHourAll(Integer restaurantId) {
		return happyHourMapper.selectHappyHourAll(restaurantId);
	}

	@Override
	public int updateById(HappyHour happyHour, List<HappyHourWeek> hwList) {
		int row = 0;
		HappyHour param = new HappyHour();
		param.setHappyHourName(happyHour.getHappyHourName());
		param.setIsActive(happyHour.getIsActive());
		param.setId(happyHour.getId());
		row = this.updateById(happyHour);

		row-=happyHourWeekMapper.deleteByHappyHourId(happyHour.getId());
		
		
		
		for (HappyHourWeek hw : hwList) {
			hw.setHappyHourId(happyHour.getId());
			row+=happyHourWeekMapper.insert(hw);
		}
		return row;
	}

}
