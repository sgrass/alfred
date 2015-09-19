package com.alfred.service.impl;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.ReportDaySalesMapper;
import com.alfred.model.ReportDaySales;
import com.alfred.service.ReportDaySalesService;
import com.alfred.vo.ReportDaySalesVO;
@Transactional
@Service("reportDaySalesService")
public class ReportDaySalesServiceImpl implements ReportDaySalesService {
   
private static Log log = LogFactory.getLog(ReportDaySalesServiceImpl.class);
	@Autowired
	@Qualifier("reportDaySalesMapper")
	private ReportDaySalesMapper reportDaySalesMapper = null;
	@Override
	public List<ReportDaySales> selectByParam(ReportDaySales reportDaySales) {
		// TODO Auto-generated method stub
		
		List<ReportDaySales> list=null;
	    list = reportDaySalesMapper.selectByParam(reportDaySales);
		return list;
	}
	@Override
	public List<ReportDaySales> selectByParamSales(HashMap<String, Object> map) {
		// TODO Auto-generated method stub

		List<ReportDaySales> list=null;
	    list = reportDaySalesMapper.selectByParamSales(map);
		return list;
	}
	@Override
	public HSSFWorkbook export(List<ReportDaySalesVO> list,String startTime,String endTime,String revenueName) {
		// TODO Auto-generated method stub
		
		//
		SimpleDateFormat fmt=new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		String[] excelHeader = { "restaurantName", "reveuneName",
				"businessDate", "totalTax", "itemSales", "itemSalesQty", "discountPer",
				"discountPerQty", "discount", "discountQty", "discountAmt",
				"focBill", "focBillQty", "totalSales", "cash", "cashQty",
				"nets", "netsQty", "visa", "visaQty", "mc", "mcQty", "amex",
				"amexQty", "jbl", "jblQty", "unionPay", "unionPayQty", "diner",
				"dinerQty", "holdld", "holdldQty", "totalCard", "totalCardQty",
				"totalCash", "totalCashQty", "totalVoid", "totalVoidQty",
				"nettSales", "totalBills", "openCount", "firstReceipt",
				"lastReceipt" };
		     HSSFWorkbook wb = new HSSFWorkbook();  
	         HSSFSheet sheet = wb.createSheet("reportDaySales");  
	         HSSFRow row0 = sheet.createRow((int) 0);  
	    	 HSSFCell cel0 = row0.createCell(0);
	    	 cel0.setCellValue("Business Time:"+startTime+"--"+endTime);
	         HSSFRow row1 = sheet.createRow((int) 1);  
	    	 HSSFCell cel1 = row1.createCell(0);
	    	 cel1.setCellValue("Revenue Name:"+revenueName);
	         HSSFRow row2 = sheet.createRow((int) 2);  
	    	 HSSFCell cel2 = row2.createCell(0);
	    	 cel2.setCellValue("Create Time:"+fmt.format(new Date()));
	         HSSFRow row = sheet.createRow((int) 4); 
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
				for (int i = 0; i < excelHeader.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(excelHeader[i]);
					//cell.setCellStyle(style);
					sheet.autoSizeColumn(i);
				}
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + 5);
					ReportDaySalesVO vo = list.get(i);
					row.createCell(0).setCellValue(vo.getRestaurantName());
					row.createCell(1).setCellValue(vo.getRevenueName());
					row.createCell(2).setCellValue(fmt.format(vo.getBusinessDate()));
					row.createCell(3).setCellValue(String.valueOf(vo.getTotalTax()));
					row.createCell(4).setCellValue(String.valueOf(vo.getItemSales()));
					row.createCell(5).setCellValue(String.valueOf(vo.getItemSalesQty()));
					row.createCell(6).setCellValue(String.valueOf(vo.getDiscountPer()));
					row.createCell(7).setCellValue(String.valueOf(vo.getDiscountPerQty()));
					row.createCell(8).setCellValue(String.valueOf(vo.getDiscount()));
					row.createCell(9).setCellValue(String.valueOf(vo.getDiscountQty()));
					row.createCell(10).setCellValue(String.valueOf(vo.getDiscountAmt()));
					row.createCell(11).setCellValue(String.valueOf(vo.getFocBill()));
					row.createCell(12).setCellValue(String.valueOf(vo.getFocBillQty()));
					row.createCell(13).setCellValue(String.valueOf(vo.getTotalSales()));
					row.createCell(14).setCellValue(String.valueOf(vo.getCash()));
					row.createCell(15).setCellValue(String.valueOf(vo.getCashQty()));
					row.createCell(16).setCellValue(String.valueOf(vo.getNets()));
					row.createCell(17).setCellValue(String.valueOf(vo.getNetsQty()));
					row.createCell(18).setCellValue(String.valueOf(vo.getVisa()));
					row.createCell(19).setCellValue(String.valueOf(vo.getVisaQty()));
					row.createCell(20).setCellValue(String.valueOf(vo.getMc()));
					row.createCell(21).setCellValue(String.valueOf(vo.getMcQty()));
					row.createCell(22).setCellValue(String.valueOf(vo.getAmex()));
					row.createCell(23).setCellValue(String.valueOf(vo.getAmexQty()));
					row.createCell(24).setCellValue(String.valueOf(vo.getJbl()));
					row.createCell(25).setCellValue(String.valueOf(vo.getJblQty()));
					row.createCell(26).setCellValue(String.valueOf(vo.getUnionPay()));
					row.createCell(27).setCellValue(String.valueOf(vo.getUnionPayQty()));
					row.createCell(28).setCellValue(String.valueOf(vo.getDiner()));
					row.createCell(29).setCellValue(String.valueOf(vo.getDinerQty()));
					row.createCell(30).setCellValue(String.valueOf(vo.getHoldld()));
					row.createCell(31).setCellValue(String.valueOf(vo.getHoldldQty()));
					row.createCell(32).setCellValue(String.valueOf(vo.getTotalCard()));
					row.createCell(33).setCellValue(String.valueOf(vo.getTotalCardQty()));
					row.createCell(34).setCellValue(String.valueOf(vo.getTotalCash()));
					row.createCell(35).setCellValue(String.valueOf(vo.getTotalCashQty()));
					row.createCell(36).setCellValue(String.valueOf(vo.getTotalVoid()));
					row.createCell(37).setCellValue(String.valueOf(vo.getTotalVoidQty()));
					row.createCell(38).setCellValue(String.valueOf(vo.getNettSales()));
					row.createCell(39).setCellValue(String.valueOf(vo.getTotalBills()));
					row.createCell(40).setCellValue(String.valueOf(vo.getOpenCount()));
					row.createCell(41).setCellValue(String.valueOf(vo.getFirstReceipt()));
					row.createCell(42).setCellValue(String.valueOf(vo.getLastReceipt()));
					
				}
			return wb;  
	    }
	@Override
	public ReportDaySales selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return reportDaySalesMapper.selectByPrimaryKey(id);
	}
	@Override
	public List<ReportDaySalesVO> selectByParamSalesExcel(
			HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDaySalesMapper.selectByParamSalesExcel(map);
	}
	@Override
	public List<ReportDaySalesVO> querySalesPdf(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDaySalesMapper.querySalesPdf(map);
	}
	@Override
	public List<ReportDaySales> querySalesRevenueAll(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDaySalesMapper.querySalesRevenueAll(map);
	}
	@Override
	public int getSalesCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDaySalesMapper.getSalesCount(map);
	}
	@Override
	public int getSalesAllCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportDaySalesMapper.getSalesAllCount(map);
	}

}
