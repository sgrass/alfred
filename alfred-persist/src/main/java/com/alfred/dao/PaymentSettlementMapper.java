package com.alfred.dao;

import com.alfred.model.PaymentSettlement;
import com.alfred.vo.TransactionVO;

import java.util.List;
import java.util.Map;

public interface PaymentSettlementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PaymentSettlement paymentSettlement);

    List<PaymentSettlement> selectByParam(PaymentSettlement paymentSettlement);

    PaymentSettlement selectByPrimaryKey(Integer id);

    int updateById(PaymentSettlement paymentSettlement);
    
    List<TransactionVO> selectTransaction(Map<String, Object> map);
    
    List<TransactionVO> selectTransactionByRest(Map<String, Object> map);
    
    
    List<PaymentSettlement> queryPlaySettment(PaymentSettlement paymentSettlement);
    
    int  getTransactionCount(Map<String, Object> map);
   
    int  getTransactionByRestCount(Map<String, Object> map);
    
    
}