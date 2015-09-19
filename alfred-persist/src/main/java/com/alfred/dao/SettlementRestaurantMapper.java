package com.alfred.dao;

import com.alfred.model.SettlementRestaurant;
import java.util.List;

public interface SettlementRestaurantMapper {
    int deleteByPrimaryKey(Integer id);
    
    
    int deleteByRestaurantId(Integer restaurantId);
    
    

    int insert(SettlementRestaurant settlementRestaurant);

    List<SettlementRestaurant> selectByParam(SettlementRestaurant settlementRestaurant);

    SettlementRestaurant selectByPrimaryKey(Integer id);
    
    int updateById(SettlementRestaurant settlementRestaurant);
}