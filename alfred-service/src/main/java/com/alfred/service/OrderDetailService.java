package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alfred.model.OrderDetail;
import com.alfred.vo.OrderReoprtVO;
public interface OrderDetailService {
	
		int deleteByPrimaryKey(Integer id) throws Exception;
	
		int insert(OrderDetail orderDetail) throws Exception;
		
		List<OrderDetail> selectByParam(OrderDetail orderDetail) throws Exception;
		
		int updateById(OrderDetail orderDetail) throws Exception;
		
		List<OrderReoprtVO> queryOrderInfo(HashMap<String, Object> map) throws Exception;
		
	    List<OrderReoprtVO> queryOrderInfoExcel(HashMap<String, Object> map) throws Exception;
	    
		public HSSFWorkbook export(List<OrderReoprtVO> list,String startTime,String endTime,String revenueName,String userName) throws Exception;
		
		public boolean orderInfoPdf( HttpServletResponse response,List<OrderReoprtVO> orderReoprtVOList,String startTime,String endTime,String revenueName,String userName) throws Exception,Throwable;
	    
		int getOrderInfoCount(HashMap<String, Object> map) throws Exception;


}
