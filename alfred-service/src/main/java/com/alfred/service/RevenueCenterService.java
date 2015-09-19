package com.alfred.service;

import java.util.List;

import com.alfred.model.RevenueCenter;
import com.alfred.vo.RevenueCenterAndPrinter;
public interface RevenueCenterService {
	
	int deleteByPrimaryKey(Integer id,Integer restaurantId) throws Exception;

	int insert(RevenueCenter revenueCenter) throws Exception;

	List<RevenueCenter> selectByParam(RevenueCenter revenueCenter) throws Exception;
	
	
	RevenueCenter selectByRevenueCenter(String revenueCenterName,int id) throws Exception;

	
	int updateById(RevenueCenter revenueCenter) throws Exception;
	
    List<RevenueCenterAndPrinter> selectByRevenList(RevenueCenterAndPrinter revenueCenterAndPrinter) throws Exception;

	
}
