package com.alfred.dao;

import com.alfred.model.ReportDaySales;
import com.alfred.vo.ReportDaySalesVO;

import java.util.HashMap;
import java.util.List;

public interface ReportDaySalesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReportDaySales reportDaySales);

    List<ReportDaySales> selectByParam(ReportDaySales reportDaySales);

    ReportDaySales selectByPrimaryKey(Integer id);

    int updateById(ReportDaySales reportDaySales);
    
    List<ReportDaySales> selectByParamSales(HashMap<String, Object> map);
    
    
    List<ReportDaySalesVO> selectByParamSalesExcel(HashMap<String, Object> map);
    
    List<ReportDaySalesVO> querySalesPdf(HashMap<String, Object> map);
    
    
    List<ReportDaySales> querySalesRevenueAll(HashMap<String, Object> map);
    
    int getSalesCount(HashMap<String, Object> map);
    
    int getSalesAllCount(HashMap<String, Object> map);
    
}