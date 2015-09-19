package com.alfred.dao;

import com.alfred.model.RevenueCenter;
import com.alfred.vo.RevenueCenterAndPrinter;

import java.util.List;

public interface RevenueCenterMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RevenueCenter revenueCenter);

    List<RevenueCenter> selectByParam(RevenueCenter revenueCenter);

    RevenueCenter selectByPrimaryKey(Integer id);

    int updateById(RevenueCenter revenueCenter);
    
    
    int updateByPrinter(RevenueCenter revenuecenter);
    
    
    List<RevenueCenter> selectByRevenueCenter(RevenueCenter revenuecenter);
    
    List<RevenueCenterAndPrinter> selectByRevenList(RevenueCenterAndPrinter revenueCenterAndPrinter);
    
    
    
    
}