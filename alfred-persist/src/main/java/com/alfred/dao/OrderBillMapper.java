package com.alfred.dao;

import com.alfred.model.OrderBill;
import java.util.List;

public interface OrderBillMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderBill orderBill);

    List<OrderBill> selectByParam(OrderBill orderBill);

    OrderBill selectByPrimaryKey(Integer id);

    int updateById(OrderBill orderBill);
}