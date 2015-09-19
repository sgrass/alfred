package com.alfred.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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

import com.alfred.dao.PaymentSettlementMapper;
import com.alfred.model.PaymentSettlement;
import com.alfred.service.PaymentSettlementService;
import com.alfred.vo.TransactionVO;
import com.lowagie.text.Anchor;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;

@Transactional
@Service("paymentSettlementService")
public class PaymentSettlementServiceImpl implements PaymentSettlementService {
	private static Log log = LogFactory.getLog(ReportPluDayItemServiceImpl.class);

	@Autowired
	@Qualifier("paymentSettlementMapper")
	private PaymentSettlementMapper paymentSettlementMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(PaymentSettlement paymentSettlement) {
		return 0;
	}

	@Override
	public List<PaymentSettlement> selectByParam(PaymentSettlement paymentSettlement) {
		return paymentSettlementMapper.selectByParam(paymentSettlement);
	}

	@Override
	public PaymentSettlement selectByPrimaryKey(Integer id) {
		return paymentSettlementMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(PaymentSettlement paymentSettlement) {
		return 0;
	}

	@Override
	public List<TransactionVO> selectTransaction(Integer restaurantId, Integer reveuneId,String startTime, String endTime,Integer startInt, Integer endInt) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurantId", restaurantId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("revenueId", reveuneId);
		if(endInt!=0){
			map.put("start", startInt);
			map.put("end", endInt);	
		}
		if (reveuneId != null && reveuneId > 0) {
			return paymentSettlementMapper.selectTransaction(map);
		}
		return paymentSettlementMapper.selectTransactionByRest(map);
	}


	@Override
	public List<PaymentSettlement> queryPlaySettment(
			PaymentSettlement paymentSettlement) {
		// TODO Auto-generated method stub
		return paymentSettlementMapper.queryPlaySettment(paymentSettlement);
	}

	@Override
	public HSSFWorkbook listToExcel(Integer restaurantId, Integer reveuneId, String startTime, String endTime,String revenueName) {
		List<TransactionVO> transactionList = this.selectTransaction(restaurantId, reveuneId, startTime, endTime,0,0);
		SimpleDateFormat fmt=new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		String[] excelHeader = { "BusinessDate", "Cashier" ,"PaymentType", "Amount", "Discount", "Tax", "Total"};
		
		 HSSFWorkbook wb = new HSSFWorkbook();  
	     HSSFSheet sheet = wb.createSheet("BohHoldSettlement");  
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
			cell.setCellStyle(style);
			sheet.autoSizeColumn(i);
		}
    
    for (int i = 0; i < transactionList.size(); i++) {
			row = sheet.createRow(i + 5);
			TransactionVO trans = transactionList.get(i);
			row.createCell(0).setCellValue(fmt.format(trans.getBusinessDate()));
			row.createCell(1).setCellValue(trans.getEmpName());
			row.createCell(2).setCellValue(trans.getPaymentTypeId());
			row.createCell(3).setCellValue(trans.getTotalAmount().doubleValue());
			row.createCell(4).setCellValue(trans.getDiscountAmount().doubleValue());
			row.createCell(5).setCellValue(trans.getTaxAmount().doubleValue());
			row.createCell(6).setCellValue(trans.getTotalAmount().doubleValue());
		}
		return wb;
	}

	@Override
	public boolean listToPdf(HttpServletResponse response,
			Integer restaurantId, Integer revenueId, String startTime,
			String endTime, String revenueName) throws Throwable {  
		SimpleDateFormat fmt=new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		response.setContentType("application/pdf");  
		response.setHeader("Content-disposition", "attachment;filename=PaymentSettlement.pdf");  
		OutputStream ouputStream;
		List<TransactionVO> transactionList = this.selectTransaction(restaurantId, revenueId, startTime, endTime,0,0);

			  ouputStream = response.getOutputStream();
			  Document document = new Document();// 建立一个Document对象
			  document.setPageSize(PageSize.A4);// 设置页面大小
			   PdfWriter.getInstance(document, ouputStream);// 建立一个PdfWriter对象
			   document.open();
			   BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
			   //Font titleFont = new Font(bfChinese, 12, Font.BOLD);// 设置字体大小
			   Font tableFont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
			   Font headFont2 = new Font(bfChinese, 9, Font.NORMAL);// 设置字体大小
		   
		       Anchor anchorTarget = new Anchor("Business Date:"+startTime+"--"+endTime);
		       Paragraph paragraph1 = new Paragraph();
		       paragraph1.setSpacingBefore(10);
		       paragraph1.add(anchorTarget);
		       document.add(paragraph1);

		      
		       Anchor anchorTarget1 = new Anchor("Revenue Name:"+revenueName);
		       Paragraph paragraph2 = new Paragraph();
		       paragraph2.setSpacingBefore(10);
		       paragraph2.add(anchorTarget1);
		       document.add(paragraph2);
		      
		      
		       Anchor anchorTarget3 = new Anchor("Create Time :"+fmt.format(new Date()));
		       Paragraph paragraph3 = new Paragraph();
		       paragraph3.setSpacingBefore(10);
		       paragraph3.add(anchorTarget3);
		       document.add(paragraph3);
			      
	            Table t=new Table(6);
	            float[] widths={1f,1f,1f,1f,1f,1f};
	            t.setWidths(widths);
	            t.setWidth(100);
	            t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
	            Cell c1 = new Cell(new Paragraph("Cashier",tableFont));
	            t.addCell(c1);
	            c1 = new Cell(new Paragraph("PaymentType",tableFont));
	            t.addCell(c1);
	            c1 = new Cell(new Paragraph("Amount",tableFont));
	            t.addCell(c1);
	            c1 = new Cell(new Paragraph("Discount",tableFont));
	            t.addCell(c1); 
	            c1 = new Cell(new Paragraph("Tax",tableFont));
	            t.addCell(c1); 
	            c1 = new Cell(new Paragraph("Total",tableFont));
	            t.addCell(c1); 
	            
	            for(int i=0;i<transactionList.size();i++){
	            	TransactionVO vo = transactionList.get(i);
	                Cell c2=new Cell(new Paragraph(vo.getEmpName(), headFont2));
	                t.addCell(c2);
	                c2=new Cell(new Paragraph(String.valueOf(vo.getPaymentTypeId()), headFont2));
	                t.addCell(c2);
	                c2=new Cell(new Paragraph(String.valueOf(vo.getTotalAmount().doubleValue()), headFont2));
	                t.addCell(c2);
	                c2=new Cell(new Paragraph(String.valueOf(vo.getDiscountAmount()), headFont2));
	                t.addCell(c2);
	                c2=new Cell(new Paragraph(String.valueOf(vo.getTaxAmount()), headFont2));
	                t.addCell(c2);
	                c2=new Cell(new Paragraph(String.valueOf(vo.getTotalAmount()), headFont2));
	                t.addCell(c2);
	            }
	            
			   document.add(t);
		
			
				    document.close();
				    ouputStream.flush();  
				    ouputStream.close();   
		
		  return true;
		 }

	@Override
	public int getTransactionCount(Integer restaurantId, Integer reveuneId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurantId", restaurantId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("revenueId", reveuneId);
		return paymentSettlementMapper.getTransactionCount(map);
	}

	@Override
	public int getTransactionByRestCount(Integer restaurantId, Integer reveuneId, String startTime, String endTime) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurantId", restaurantId);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("revenueId", reveuneId);
		return paymentSettlementMapper.getTransactionByRestCount(map);
	}

}
