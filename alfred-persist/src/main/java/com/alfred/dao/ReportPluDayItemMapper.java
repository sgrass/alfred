package com.alfred.dao;

import com.alfred.model.ReportPluDayItem;

import java.util.HashMap;
import java.util.List;

public interface ReportPluDayItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportPluDayItem reportPluDayItem);

    List<ReportPluDayItem> selectByParam(ReportPluDayItem reportPluDayItem);
    
    List<ReportPluDayItem> selectReoprtItemByParam(HashMap<String, Object> map);

    ReportPluDayItem selectByPrimaryKey(Integer id);

    int updateById(ReportPluDayItem reportPluDayItem);
    
    List<ReportPluDayItem> queryItemCategory(ReportPluDayItem reportPluDayItem);
    
    List<ReportPluDayItem> queryItem(ReportPluDayItem reportPluDayItem);
   
    int queryItemCount(ReportPluDayItem reportPluDayItem); 
    
    List<ReportPluDayItem> querySalesMainCategory(HashMap<String, Object> map);
    
    List<ReportPluDayItem> queryReoprtItemGroup(HashMap<String, Object> map);

    int  getItemReportCount (HashMap<String, Object> map);
    
    int  getAllItemReportTotal (HashMap<String, Object> map);

    
    
}