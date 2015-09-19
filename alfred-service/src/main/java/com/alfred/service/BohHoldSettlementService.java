package com.alfred.service;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import com.alfred.model.BohHoldSettlement;
import com.alfred.vo.BohHoldSettlementVO;
public interface BohHoldSettlementService {
	
  int deleteByPrimaryKey(Integer id) throws Exception;

  int insert(BohHoldSettlement bohHoldSettlement) throws Exception;

  List<BohHoldSettlement> selectByParam(BohHoldSettlement bohHoldSettlement) throws Exception;
  
  List<BohHoldSettlementVO> selectByDaysDue(Integer restaurantId, Integer revenueId, String startTime, String endTime,Integer startInt,Integer endInt) throws Exception;
  
  HSSFWorkbook listToExcel(Integer restaurantId, Integer revenueId, String startTime, String endTime,String revenueName) throws Exception;
  
  public boolean listToPdf(HttpServletResponse response,Integer restaurantId, Integer revenueId, String startTime, String endTime,String revenueName) throws Exception,Throwable ;
  
  BohHoldSettlement selectByPrimaryKey(Integer id) throws Exception;
  
  int updateById(BohHoldSettlement bohHoldSettlement) throws Exception;
  
  int updatePaid(BohHoldSettlement bohHoldSettlement) throws Exception;
  
  int  getDaysDueCount(Integer restaurantId, Integer revenueId, String startTime, String endTime) throws Exception;
}
