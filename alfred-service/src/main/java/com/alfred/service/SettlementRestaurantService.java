package com.alfred.service;

import java.util.List;

import com.alfred.model.SettlementRestaurant;


public interface SettlementRestaurantService {
	
	
	int deleteByPrimaryKey(Integer id) throws Exception;

	int insert(SettlementRestaurant settlementRestaurant) throws Exception;
	
	int bachInsert(String[] media, String[] adjuestMent, String reasons,String remarks,int restaurantId,String general,String otherPayment) throws Exception;

	List<SettlementRestaurant> selectByParam(SettlementRestaurant settlementRestaurant) throws Exception;

	SettlementRestaurant selectByPrimaryKey(Integer id) throws Exception;

	int updateById( SettlementRestaurant settlementRestaurant) throws Exception;

}
