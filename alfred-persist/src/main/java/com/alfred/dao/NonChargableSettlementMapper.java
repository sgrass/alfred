package com.alfred.dao;

import com.alfred.model.NonChargableSettlement;
import java.util.List;

public interface NonChargableSettlementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NonChargableSettlement nonChargableSettlement);

    List<NonChargableSettlement> selectByParam(NonChargableSettlement nonChargableSettlement);

    NonChargableSettlement selectByPrimaryKey(Integer id);

    int updateById(NonChargableSettlement nonChargableSettlement);
}