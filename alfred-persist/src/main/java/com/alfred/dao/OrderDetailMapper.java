package com.alfred.dao;

import com.alfred.model.OrderDetail;
import com.alfred.vo.OrderReoprtVO;

import java.util.HashMap;
import java.util.List;

public interface OrderDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetail orderDetail);

    List<OrderDetail> selectByParam(OrderDetail orderDetail);

    OrderDetail selectByPrimaryKey(Integer id);

    int updateById(OrderDetail orderDetail);
    
    List<OrderReoprtVO> queryOrderInfo(HashMap<String, Object> map);
    
    int getOrderInfoCount(HashMap<String, Object> map);
    
    
}