package com.alfred.dao;

import com.alfred.model.ReportPluDayModifier;
import java.util.List;

public interface ReportPluDayModifierMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportPluDayModifier reportPluDayModifier);

    List<ReportPluDayModifier> selectByParam(ReportPluDayModifier reportPluDayModifier);

    ReportPluDayModifier selectByPrimaryKey(Integer id);

    int updateById(ReportPluDayModifier reportPluDayModifier);
}