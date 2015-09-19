package com.alfred.service.impl;
import java.io.IOException;
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

import com.alfred.dao.OrderDetailMapper;
import com.alfred.model.OrderDetail;
import com.alfred.service.OrderDetailService;
import com.alfred.vo.BohHoldSettlementVO;
import com.alfred.vo.OrderReoprtVO;
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
@Service("orderDetailService")
public class OrderDetailServiceImpl implements OrderDetailService{
private static Log log = LogFactory.getLog(OrderDetailServiceImpl.class);
	
	@Autowired
	@Qualifier("orderDetailMapper")
	private OrderDetailMapper orderDetailMapper = null;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderDetail> selectByParam(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateById(OrderDetail orderDetail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderReoprtVO> queryOrderInfo(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDetailMapper.queryOrderInfo(map);
	}

	@Override
	public HSSFWorkbook export(List<OrderReoprtVO> list,String startTime,String endTime,String revenueName,String userName) {
		// TODO Auto-generated method stub
		 String[] excelHeader = { "Restaurant Date", 
				 "Order ID", 
				 "Table No",
				 "Item Name",
				 "Qty","Total",
				 "User Name",
		         "Reventure Center",
		         "itemNum","reason",
		         "itemPrice","taxPrice",
		         "discountPrice",
		         "discountRate",
		         "realPrice",
		         "modifierPrice"
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
	    	 
	    	 HSSFRow rowUserName = sheet.createRow((int) 2);  
	    	 HSSFCell celUserName = rowUserName.createCell(0);
	    	 celUserName.setCellValue("User Name:"+userName);
	    	 
	         HSSFRow row2 = sheet.createRow((int) 3);  
	    	 HSSFCell cel2 = row2.createCell(0);
	    	 cel2.setCellValue("Create Time:"+fmt.format(new Date()));
	         
	    	 
	    	 HSSFRow row = sheet.createRow((int) 5); 
	        
	        HSSFCellStyle style = wb.createCellStyle();  
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
	  

				for (int i = 0; i < excelHeader.length; i++) {
					HSSFCell cell = row.createCell(i);
					cell.setCellValue(excelHeader[i]);
					//cell.setCellStyle(style);
					sheet.autoSizeColumn(i);
				}
				for (int i = 0; i < list.size(); i++) {
					row = sheet.createRow(i + 6);
					OrderReoprtVO vo = list.get(i);
					row.createCell(0).setCellValue(fmt.format(vo.getBusinessDate()));
					row.createCell(1).setCellValue(vo.getId());
					row.createCell(2).setCellValue(vo.getTableName());
					row.createCell(3).setCellValue(vo.getItemName());
					row.createCell(4).setCellValue(vo.getPersons());
					row.createCell(5).setCellValue((String.valueOf(vo.getTotal())));
					row.createCell(6).setCellValue(vo.getEmpName());
					row.createCell(7).setCellValue(vo.getRevName());
					row.createCell(8).setCellValue(vo.getItemNum());
					row.createCell(9).setCellValue(vo.getReason());
					row.createCell(10).setCellValue(String.valueOf(vo.getItemPrice()));
					row.createCell(11).setCellValue(String.valueOf(vo.getTaxPrice()));
					row.createCell(12).setCellValue(String.valueOf(vo.getDiscountPrice()));
					row.createCell(13).setCellValue(String.valueOf(vo.getDiscountRate()));
					row.createCell(14).setCellValue(String.valueOf(vo.getRealPrice()));
					row.createCell(15).setCellValue(String.valueOf(vo.getModifierPrice()));
				}
			return wb;  
	    }

	@Override
	public List<OrderReoprtVO> queryOrderInfoExcel(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDetailMapper.queryOrderInfo(map);
	}

	@Override
	public boolean orderInfoPdf(HttpServletResponse response,
			List<OrderReoprtVO> orderReoprtVOList, String startTime,
			String endTime, String revenueName, String userName) throws Throwable {  
		      response.setContentType("application/pdf");  
		      response.setHeader("Content-disposition", "attachment;filename=OrderReoprt.pdf");  
		      OutputStream ouputStream;
		      SimpleDateFormat fmt=	new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
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
		      
		      Anchor paragraphusers = new Anchor("User Name:"+userName);
		      Paragraph paragraphuser = new Paragraph();
		      paragraphuser.setSpacingBefore(10);
		      paragraphuser.add(paragraphusers);
		      document.add(paragraphuser);
		      
		      
		      Anchor anchorTarget3 = new Anchor("Create Time :"+fmt.format(new Date()));
		      Paragraph paragraph3 = new Paragraph();
		      paragraph3.setSpacingBefore(10);
		      paragraph3.add(anchorTarget3);
		      document.add(paragraph3);
		      
          //"BillNumber ", "Customer" ,"PhoneNumber", "status", "Amount", "Authorized By", "DaysDue", "PaymentType","BillDate"};
            //record header field
            Table t=new Table(7);
            java.text.DateFormat format1 = new java.text.SimpleDateFormat("MM/dd/yyyy");

            float[] widths={1f,1f,1f,1f,1f,1f,1f};
            t.setWidths(widths);
            t.setWidth(100);
            t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
            Cell c1 = new Cell(new Paragraph("Date",tableFont));
            t.addCell(c1);
            c1 = new Cell(new Paragraph("TableNO",tableFont));
            t.addCell(c1);
            c1 = new Cell(new Paragraph("Item",tableFont));
            t.addCell(c1);
            c1 = new Cell(new Paragraph("Price",tableFont));
            t.addCell(c1);
            c1 = new Cell(new Paragraph("Qty",tableFont));
            t.addCell(c1); 
            c1 = new Cell(new Paragraph("Amount",tableFont));
            t.addCell(c1); 
            c1 = new Cell(new Paragraph("UserName",tableFont));
            t.addCell(c1); 
            
            for(int i=0;i<orderReoprtVOList.size();i++){
            	OrderReoprtVO vo = orderReoprtVOList.get(i);
            	
            	Cell  c2=new Cell(new Paragraph(String.valueOf(format1.format(vo.getBusinessDate())), headFont2));
            	t.addCell(c2);
            	c2=new Cell(new Paragraph(String.valueOf(vo.getTableName()), headFont2));
                t.addCell(c2);
                c2=new Cell(new Paragraph(String.valueOf(vo.getItemName()), headFont2));
                t.addCell(c2);
                c2=new Cell(new Paragraph(String.valueOf(vo.getItemPrice()), headFont2));
                t.addCell(c2);
                c2=new Cell(new Paragraph(String.valueOf(vo.getItemNum()), headFont2));
                t.addCell(c2);
                c2=new Cell(new Paragraph(String.valueOf(vo.getItemNum()*vo.getItemPrice().floatValue()), headFont2));
                t.addCell(c2);
                c2=new Cell(new Paragraph(String.valueOf(vo.getEmpName()), headFont2));
                t.addCell(c2);
             
             

            }
            
		   document.add(t);
		  

	      document.close();
	      ouputStream.flush();  
	      ouputStream.close();   
			   
		  
		  return true;
		 }

	@Override
	public int getOrderInfoCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return orderDetailMapper.getOrderInfoCount(map);
	}  
	

}
