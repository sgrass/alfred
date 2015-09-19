package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alfred.model.ReportHourly;
public interface ReportHourlyService {
	
	 List<ReportHourly> selectByParam(ReportHourly reportHourly) throws Exception;
	 
	 List<ReportHourly> queryReportHourly(HashMap<String, Object> map) throws Exception;
	 
	 public HSSFWorkbook exportReportHourlyExcel(List<ReportHourly> list,String startTime,String endTime,String revenueName) throws Exception;
     
	 public boolean exportReportHourlyPdf( HttpServletResponse response,List<ReportHourly> list,String startTime,String endTime,String revenueName) throws Exception,Throwable;
     
	 List<ReportHourly> queryReportHourlyRevenueAll(HashMap<String, Object> map) throws Exception;
     
	 int  queryReportHourlyCount(HashMap<String, Object> map) throws Exception;
     
	 int  queryReportHourlyAllCount(HashMap<String, Object> map) throws Exception;

}
