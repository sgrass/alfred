package com.alfred.service;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.alfred.model.PaymentSettlement;
import com.alfred.vo.TransactionVO;
public interface PaymentSettlementService {
	
	  int deleteByPrimaryKey(Integer id) throws Exception;
	
	  int insert(PaymentSettlement paymentSettlement) throws Exception;
	
	  List<PaymentSettlement> selectByParam(PaymentSettlement paymentSettlement) throws Exception;
	
	  PaymentSettlement selectByPrimaryKey(Integer id) throws Exception;
	
	  int updateById(PaymentSettlement paymentSettlement) throws Exception;
	  
	  List<TransactionVO> selectTransaction(Integer restaurantId, Integer reveuneId, String startTime, String endTime,Integer startInt, Integer endInt) throws Exception;
	  
	  HSSFWorkbook listToExcel(Integer restaurantId, Integer reveuneId, String startTime, String endTime,String revenueName) throws Exception;
	 
	  public boolean listToPdf(HttpServletResponse response,Integer restaurantId, Integer revenueId, String startTime, String endTime,String revenueName) throws Exception,Throwable ;
	
	  List<PaymentSettlement> queryPlaySettment(PaymentSettlement paymentSettlement) throws Exception;
	
	  int  getTransactionCount(Integer restaurantId, Integer reveuneId, String startTime, String endTime) throws Exception;
	  
	  int  getTransactionByRestCount(Integer restaurantId, Integer reveuneId, String startTime, String endTime) throws Exception;
	}
