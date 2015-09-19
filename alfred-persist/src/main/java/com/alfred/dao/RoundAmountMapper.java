package com.alfred.dao;

import com.alfred.model.RoundAmount;

import java.util.HashMap;
import java.util.List;
public interface RoundAmountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoundAmount roundAmount);

    List<RoundAmount> selectByParam(RoundAmount roundAmount);

    RoundAmount selectByPrimaryKey(Integer id);

    int updateById(RoundAmount roundAmount);
    
    List<RoundAmount> queryRoundValue(HashMap<String, Object> map);
    
}