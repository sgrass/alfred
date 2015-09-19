package com.alfred.dao;

import com.alfred.model.ReportDayTax;

import java.util.HashMap;
import java.util.List;

public interface ReportDayTaxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportDayTax reportDayTax);

    List<ReportDayTax> selectByParam(ReportDayTax reportDayTax);

    ReportDayTax selectByPrimaryKey(Integer id);

    int updateById(ReportDayTax reportDayTax);
    
    List<ReportDayTax> queryTaxGroup(HashMap<String, Object> map);
    
}