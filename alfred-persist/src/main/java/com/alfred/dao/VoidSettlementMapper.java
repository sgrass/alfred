package com.alfred.dao;

import com.alfred.model.VoidSettlement;
import java.util.List;

public interface VoidSettlementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VoidSettlement voidSettlement);

    List<VoidSettlement> selectByParam(VoidSettlement voidSettlement);

    VoidSettlement selectByPrimaryKey(Integer id);

    int updateById(VoidSettlement voidSettlement);
}