package com.alfred.dao;

import com.alfred.model.NetsSettlement;
import java.util.List;

public interface NetsSettlementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(NetsSettlement netsSettlement);

    List<NetsSettlement> selectByParam(NetsSettlement netsSettlement);

    NetsSettlement selectByPrimaryKey(Integer id);

    int updateById(NetsSettlement netsSettlement);
}