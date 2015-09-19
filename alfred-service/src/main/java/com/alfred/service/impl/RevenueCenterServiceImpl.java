package com.alfred.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.ItemDetailMapper;
import com.alfred.dao.RevenueCenterMapper;
import com.alfred.model.ItemDetail;
import com.alfred.model.RevenueCenter;
import com.alfred.service.RevenueCenterService;
import com.alfred.vo.RevenueCenterAndPrinter;
@Transactional
@Service("revenueCenterService")
public class RevenueCenterServiceImpl implements RevenueCenterService {
	
	
	private static Log log = LogFactory.getLog(RevenueCenterServiceImpl.class);
	@Autowired
	@Qualifier("revenueCenterMapper")
	private RevenueCenterMapper revenueCenterMapper = null;

	@Autowired
	@Qualifier("itemDetailMapper")
	private ItemDetailMapper itemDetailMapper = null;
	
	
	@Override
	public int deleteByPrimaryKey(Integer id,Integer restaurantId) {
		// TODO Auto-generated method stub
		ItemDetail itemDetail=new ItemDetail();
		itemDetail.setPrinterId(id);
		itemDetail.setRestaurantId(restaurantId);
		itemDetailMapper.updateById(itemDetail);
		
		return revenueCenterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RevenueCenter revenueCenter) {
		int newId=0;
		// TODO Auto-generated method stub
		int flag = revenueCenterMapper.insert(revenueCenter);
		if(flag>0){
			newId=	revenueCenter.getId();
			
		}

		return newId;
	}

	@Override
	public List<RevenueCenter> selectByParam(RevenueCenter revenueCenter) {
		// TODO Auto-generated method stubet
		return revenueCenterMapper.selectByParam(revenueCenter);
	}

	@Override
	public int updateById(RevenueCenter revenueCenter) {
		// TODO Auto-generated method stub
		 revenueCenterMapper.updateById(revenueCenter);
		 return 0;
		
	}

	@Override
	public RevenueCenter selectByRevenueCenter(String revenueCenterName, int id) {
		// TODO Auto-generated method stub
		RevenueCenter param = new RevenueCenter();
		param.setRevName(revenueCenterName);;
		if(id!=0){
			param.setId(id);
		}
		List<RevenueCenter> list = revenueCenterMapper.selectByRevenueCenter(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<RevenueCenterAndPrinter> selectByRevenList(
			RevenueCenterAndPrinter revenueCenterAndPrinter) {
		// TODO Auto-generated method stub
		return revenueCenterMapper.selectByRevenList(revenueCenterAndPrinter);
	}



}
