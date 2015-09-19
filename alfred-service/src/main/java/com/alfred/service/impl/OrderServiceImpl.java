package com.alfred.service.impl;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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

import com.alfred.dao.OrderMapper;
import com.alfred.model.Order;
import com.alfred.model.RevenueCenter;
import com.alfred.service.OrderService;
import com.alfred.vo.DiscountVO;
import com.alfred.vo.OrderCharts;
import com.lowagie.text.Anchor;
import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfWriter;


@Transactional
@Service("orderService")
public class OrderServiceImpl implements OrderService {
private static Log log = LogFactory.getLog(OrderServiceImpl.class);
	
	@Autowired
	@Qualifier("orderMapper")
	private OrderMapper orderMapper = null;
	
	@Override
	public List<OrderCharts> selectByParamCharts(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return orderMapper.selectByParamCharts(map);
	}

	@Override
	public List<Order> selectByParamSession(Order order) {
		// TODO Auto-generated method stub
		return orderMapper.selectByParamSession(order);
	}

	@Override
	public List<RevenueCenter> queryRevenue(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return orderMapper.queryRevenue(map);
	}

	@Override
	public OrderCharts queryOrderToday(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		 OrderCharts or=null;
		 List<OrderCharts> list=orderMapper.queryOrderToday(map);
		 if(list.size()>0){
			 or=list.get(0);
		 }
		return or;
	}

	@Override
	public List<DiscountVO> queryReportDiscount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return orderMapper.queryReportDiscount(map);
	}

	@Override
	public HSSFWorkbook exportReportDiscountExcel(List<DiscountVO> list,
			String startTime, String endTime, String revenueName)
			throws Exception {

		String[] excelHeader = { "Restaurant Date", 
				 "BillNumber",
				 "RevenueName", 
				 "TableName",
				 "ActuallAmount",
				 "Discount",
				 "GrandTotal"
		 }; 
		 SimpleDateFormat fmt=	new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		 HSSFWorkbook wb = new HSSFWorkbook();  
	     HSSFSheet sheet = wb.createSheet("Campaign");  
	   
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
					DiscountVO vo = list.get(i);
					row.createCell(0).setCellValue(fmt.format(vo.getBusinessDate()));
					row.createCell(1).setCellValue(vo.getBillNumber());
					row.createCell(2).setCellValue(vo.getRevenueName());
					row.createCell(3).setCellValue(vo.getTableName());
					row.createCell(4).setCellValue(String.valueOf(vo.getActuallAmount()));
					row.createCell(5).setCellValue(String.valueOf(vo.getDiscount()));
					row.createCell(6).setCellValue(String.valueOf(vo.getDiscount()));
		
				}
			return wb;  
	    }

	@Override
	public boolean exportReportDiscountPdf(
			HttpServletResponse response,
			List<DiscountVO> orderReoprtDiscountVOList, String startTime,
			String endTime, String revenueName) throws Exception {
		
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition","attachment;filename=ReoprtDiscount.pdf");
		OutputStream ouputStream;
		SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		ouputStream = response.getOutputStream();
		Document document = new Document();// 建立一个Document对象
		document.setPageSize(PageSize.A4);// 设置页面大小
		PdfWriter.getInstance(document, ouputStream);// 建立一个PdfWriter对象
		document.open();
		BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
		Font tableFont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
		Font headFont2 = new Font(bfChinese, 9, Font.NORMAL);// 设置字体大小

		Anchor anchorTarget = new Anchor("Business Date:" + startTime + "--"+ endTime);
		Paragraph paragraph1 = new Paragraph();
		paragraph1.setSpacingBefore(10);
		paragraph1.add(anchorTarget);
		document.add(paragraph1);

		Anchor anchorTarget1 = new Anchor("Revenue Name:" + revenueName);
		Paragraph paragraph2 = new Paragraph();
		paragraph2.setSpacingBefore(10);
		paragraph2.add(anchorTarget1);
		document.add(paragraph2);

		Anchor anchorTarget3 = new Anchor("Create Time :"+ fmt.format(new Date()));
		Paragraph paragraph3 = new Paragraph();
		paragraph3.setSpacingBefore(10);
		paragraph3.add(anchorTarget3);
		document.add(paragraph3);
		
		Table t = new Table(7);
		java.text.DateFormat format1 = new java.text.SimpleDateFormat("MM/dd/yyyy");

		float[] widths = { 1f, 1f, 1f, 1f, 1f, 1f, 1f };
		t.setWidths(widths);
		t.setWidth(100);
		t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		Cell c1 = new Cell(new Paragraph("Date", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("RevenueName", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("billNumber", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("tableName", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("actuallAmount", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("discount", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("grandTotal", tableFont));
		t.addCell(c1);

		for (int i = 0; i < orderReoprtDiscountVOList.size(); i++) {
			
			DiscountVO vo = orderReoprtDiscountVOList.get(i);

			Cell c2 = new Cell(new Paragraph(String.valueOf(format1.format(vo.getBusinessDate())), headFont2));
			t.addCell(c2);
			c2 = new Cell(new Paragraph(String.valueOf(vo.getRevenueName()),headFont2));
			t.addCell(c2);
			c2 = new Cell(new Paragraph(String.valueOf(vo.getBillNumber()),headFont2));
			t.addCell(c2);
			c2 = new Cell(new Paragraph(String.valueOf(vo.getTableName()),headFont2));
			t.addCell(c2);
			c2 = new Cell(new Paragraph(String.valueOf(vo.getActuallAmount()),headFont2));
			t.addCell(c2);
			c2 = new Cell(new Paragraph(String.valueOf(vo.getDiscount()), headFont2));
			t.addCell(c2);
			c2 = new Cell(new Paragraph(String.valueOf(vo.getGrandTotal()),headFont2));
			t.addCell(c2);

		}

		document.add(t);
		document.close();
		ouputStream.flush();
		ouputStream.close();

		return true;
	}

}
