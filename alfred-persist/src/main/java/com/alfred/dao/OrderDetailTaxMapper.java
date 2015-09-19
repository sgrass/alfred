package com.alfred.dao;

import com.alfred.model.OrderDetailTax;
import java.util.List;

public interface OrderDetailTaxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDetailTax orderDetailTax);

    List<OrderDetailTax> selectByParam(OrderDetailTax orderDetailTax);

    OrderDetailTax selectByPrimaryKey(Integer id);

    int updateById(OrderDetailTax orderDetailTax);
}