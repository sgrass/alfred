package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alfred.model.ReportDaySales;
import com.alfred.vo.ReportDaySalesVO;
public interface ReportDaySalesService {
	
	    List<ReportDaySales> selectByParam(ReportDaySales reportDaySales) throws Exception;
	   
	    List<ReportDaySales> selectByParamSales(HashMap<String, Object> map) throws Exception;
		
	    public HSSFWorkbook export(List<ReportDaySalesVO> list,String startTime,String endTime,String revenueName) throws Exception;
	   
	    ReportDaySales selectByPrimaryKey(Integer id) throws Exception;
	   
	    List<ReportDaySalesVO> selectByParamSalesExcel(HashMap<String, Object> map) throws Exception;
	   
	    List<ReportDaySalesVO> querySalesPdf(HashMap<String, Object> map) throws Exception;
	   
	    List<ReportDaySales> querySalesRevenueAll(HashMap<String, Object> map) throws Exception;
	   
	    int getSalesCount(HashMap<String, Object> map) throws Exception;
	   
	    int getSalesAllCount(HashMap<String, Object> map) throws Exception;



}
