package com.alfred.dao;

import com.alfred.model.BohHoldSettlement;
import com.alfred.vo.BohHoldSettlementVO;

import java.util.List;
import java.util.Map;

public interface BohHoldSettlementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BohHoldSettlement bohHoldSettlement);

    List<BohHoldSettlement> selectByParam(BohHoldSettlement bohHoldSettlement);

    List<BohHoldSettlementVO> selectByDaysDue(Map<String, Object> map);
    
    int  getDaysDueCount(Map<String, Object> map);
    
    BohHoldSettlement selectByPrimaryKey(Integer id);

    int updateById(BohHoldSettlement bohHoldSettlement);
    
    int updatePaid(BohHoldSettlement bohHoldSettlement);
    
}