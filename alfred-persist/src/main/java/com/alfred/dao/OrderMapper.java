package com.alfred.dao;

import com.alfred.model.Order;
import com.alfred.model.RevenueCenter;
import com.alfred.vo.DiscountVO;
import com.alfred.vo.OrderCharts;

import java.util.HashMap;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order order);

    List<Order> selectByParam(Order order);

    Order selectByPrimaryKey(Integer id);

    int updateById(Order order);
    
    
    List<OrderCharts> selectByParamCharts(HashMap<String, Object> map);
    
    List<Order> selectByParamSession(Order order);
    
    List<RevenueCenter> queryRevenue(HashMap<String, Object> map);

    List<OrderCharts> queryOrderToday(HashMap<String, Object> map);
    
    List<DiscountVO> queryReportDiscount(HashMap<String, Object> map);
 
    
    
    
    
    
}