package com.alfred.dao;

import com.alfred.model.OrderSplit;
import java.util.List;

public interface OrderSplitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderSplit orderSplit);

    List<OrderSplit> selectByParam(OrderSplit orderSplit);

    OrderSplit selectByPrimaryKey(Integer id);

    int updateById(OrderSplit orderSplit);
}