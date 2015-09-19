package com.alfred.dao;

import com.alfred.model.CashInOut;
import java.util.List;

public interface CashInOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CashInOut cashInOut);

    List<CashInOut> selectByParam(CashInOut cashInOut);

    CashInOut selectByPrimaryKey(Integer id);

    int updateById(CashInOut cashInOut);
}