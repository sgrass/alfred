package com.alfred.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.PlacesMapper;
import com.alfred.model.Places;
import com.alfred.service.PlacesService;
import com.alfred.vo.PlaceTable;

@Transactional
@Service("placesService")
public class PlacesServiceImpl implements PlacesService{

	
	private static Log log = LogFactory.getLog(PlacesServiceImpl.class);
	
	@Autowired
	@Qualifier("placesMapper")
	private PlacesMapper placesMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		//删除实际上是把ISACTIVE 设置为-1
		Places p=new Places();
		p.setId(id);
		p.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		int res=1;
		int flag = placesMapper.updateById(p);
		if(flag>0){
			res=0;
		}

		return res;
	}
	@Override
	public int insert(Places places) {
		// TODO Auto-generated method stub
		return placesMapper.insert(places);
	}

	@Override
	public List<Places> selectByParam(Places places) {
		// TODO Auto-generated method stub
		return placesMapper.selectByParam(places);
	}

	@Override
	public int updateById(Places places) {
		// TODO Auto-generated method stub
		return placesMapper.updateById(places);
	}

	@Override
	public List<PlaceTable> selectByRevenueId(Integer revenueId) {
		// TODO Auto-generated method stub
		return placesMapper.selectByRevenueId(revenueId);
	}
	@Override
	public Places selectByPlace(Places places) {
		// TODO Auto-generated method stub
		List<Places> list;
		list = placesMapper.selectByPlace(places);
		
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}
	@Override
	public Places selectByPrimaryKey(Integer id) throws Exception {
		// TODO Auto-generated method stub
		return placesMapper.selectByPrimaryKey(id);
	}

}
