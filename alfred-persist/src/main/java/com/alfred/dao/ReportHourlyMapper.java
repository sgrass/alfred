package com.alfred.dao;

import com.alfred.model.ReportHourly;

import java.util.HashMap;
import java.util.List;

public interface ReportHourlyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportHourly reportHourly);

    List<ReportHourly> selectByParam(ReportHourly reportHourly);

    ReportHourly selectByPrimaryKey(Integer id);

    int updateById(ReportHourly reportHourly);
    
    int  queryReportHourlyCount(HashMap<String, Object> map);
    
    int  getAllTotal(HashMap<String, Object> map);
    List<ReportHourly> queryReportHourly(HashMap<String, Object> map);
    
    List<ReportHourly> queryReportHourlyRevenueAll(HashMap<String, Object> map);

    
}