package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alfred.model.Order;
import com.alfred.model.RevenueCenter;
import com.alfred.vo.DiscountVO;
import com.alfred.vo.OrderCharts;

public interface OrderService {
	
	    List<OrderCharts> selectByParamCharts(HashMap<String, Object> map) throws Exception;
	    
	    List<Order> selectByParamSession(Order order) throws Exception;
	   
	    List<RevenueCenter> queryRevenue(HashMap<String, Object> map) throws Exception;
	   
	    OrderCharts queryOrderToday(HashMap<String, Object> map) throws Exception;
	    
	    List<DiscountVO> queryReportDiscount(HashMap<String, Object> map);
	    
	    public HSSFWorkbook exportReportDiscountExcel(List<DiscountVO> list,String startTime,String endTime,String revenueName) throws Exception;

	    boolean exportReportDiscountPdf(HttpServletResponse response,List<DiscountVO> orderReoprtDiscountVOList, String startTime,String endTime, String revenueName)  throws Exception,Throwable;
	  

}
