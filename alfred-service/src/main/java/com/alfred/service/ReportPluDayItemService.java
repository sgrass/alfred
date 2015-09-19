package com.alfred.service;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alfred.model.ReportPluDayItem;
public interface ReportPluDayItemService {
	List<ReportPluDayItem> selectByParam(ReportPluDayItem reportPluDayItem) throws Exception;

	List<ReportPluDayItem> selectReoprtItemByParam(HashMap<String, Object> map) throws Exception;
	
	List<ReportPluDayItem> queryItemCategory(ReportPluDayItem reportPluDayItem) throws Exception;
	    
	List<ReportPluDayItem> queryItem(ReportPluDayItem reportPluDayItem) throws Exception;
	

	public HSSFWorkbook export(List<ReportPluDayItem> list,String startTime,String endTime,String revenueName) throws Exception;
	
	public boolean pluDayPdf( HttpServletResponse response,List<ReportPluDayItem> list,String startTime,String endTime,String revenueName) throws Exception,Throwable;
	
    int queryItemCount(ReportPluDayItem reportPluDayItem) throws Exception; 
    
    List<ReportPluDayItem> querySalesMainCategory(HashMap<String, Object> map) throws Exception;
    
    List<ReportPluDayItem> queryReoprtItemGroup(HashMap<String, Object> map) throws Exception;
    
    int  getItemCount (HashMap<String, Object> map) throws Exception;
    
    int  getAllTotal (HashMap<String, Object> map) throws Exception;



}
