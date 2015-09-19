package com.alfred.dao;

import com.alfred.model.RoundRule;
import java.util.List;

public interface RoundRuleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RoundRule roundRule);

    List<RoundRule> selectByParam(RoundRule roundRule);

    RoundRule selectByPrimaryKey(Integer id);

    int updateById(RoundRule roundRule);
}