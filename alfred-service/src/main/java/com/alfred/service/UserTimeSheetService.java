package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alfred.model.UserTimeSheet;

public interface UserTimeSheetService {
   
	List<UserTimeSheet> queryUserTimeList(HashMap<String, Object> map) throws Exception ;
	
	HSSFWorkbook  exportExcel(List<UserTimeSheet> userTimeSheetList, String startTime, String endTime,String userName) throws Exception ;
	
	boolean  exportPdf(HttpServletResponse response,List<UserTimeSheet> userTimeSheetList, String startTime, String endTime,String userName) throws Exception , Throwable ;
   
	int getUserTimeCount(HashMap<String, Object> map) throws Exception ;

}
