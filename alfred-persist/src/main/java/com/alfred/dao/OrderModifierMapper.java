package com.alfred.dao;

import com.alfred.model.OrderModifier;
import java.util.List;

public interface OrderModifierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderModifier orderModifier);

    List<OrderModifier> selectByParam(OrderModifier orderModifier);

    OrderModifier selectByPrimaryKey(Integer id);

    int updateById(OrderModifier orderModifier);
}