package com.alfred.dao;

import com.alfred.model.Payment;
import java.util.List;

public interface PaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Payment payment);

    List<Payment> selectByParam(Payment payment);

    Payment selectByPrimaryKey(Integer id);

    int updateById(Payment payment);
}