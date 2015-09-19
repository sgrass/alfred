package com.alfred.service.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.ReportPluDayItemMapper;
import com.alfred.model.ReportPluDayItem;
import com.alfred.service.ReportPluDayItemService;
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
@Service("reportPluDayItemService")
public class ReportPluDayItemServiceImpl implements ReportPluDayItemService {
	   
	private static Log log = LogFactory.getLog(ReportPluDayItemServiceImpl.class);
		@Autowired
		@Qualifier("reportPluDayItemMapper")
		private ReportPluDayItemMapper reportPluDayItemMapper = null;
	@Override
	public List<ReportPluDayItem> selectByParam(ReportPluDayItem reportPluDayItem) {
		// TODO Auto-generated method stub
		return reportPluDayItemMapper.selectByParam(reportPluDayItem);
	}
	@Override
	public List<ReportPluDayItem> selectReoprtItemByParam(
			HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportPluDayItemMapper.selectReoprtItemByParam(map);
	}
	@Override
	public HSSFWorkbook export(List<ReportPluDayItem> list,String startTime,String endTime,String revenueName) {

		// TODO Auto-generated method stub
		 String[] excelHeader = {"Main Category   ","Sub Category    ","Item       ","Amount Qty     ","Amount Price    "};
		 HSSFWorkbook wb = new HSSFWorkbook();  
		 HSSFSheet sheet = wb.createSheet("ReportPluDayItem"); 
         HSSFRow row0 = sheet.createRow((int) 0);  
    	 HSSFCell cel0 = row0.createCell(0);
    	 cel0.setCellValue("Business Time:"+startTime+"--"+endTime);
         HSSFRow row1 = sheet.createRow((int) 1);  
    	 HSSFCell cel1 = row1.createCell(0);
    	 cel1.setCellValue("Revenue Name:"+revenueName);
         HSSFRow row2 = sheet.createRow((int) 2);  
    	 HSSFCell cel2 = row2.createCell(0);
    	 cel2.setCellValue("Create Time:"+(new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
         HSSFRow row = sheet.createRow((int) 4);  
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			sheet.autoSizeColumn(i);
		}
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow(i + 5);
			ReportPluDayItem vo = list.get(i);
			row.createCell(0).setCellValue(vo.getItemMainCategoryName());
			row.createCell(1).setCellValue(vo.getItemCategoryName());
			row.createCell(2).setCellValue(vo.getItemName());
			//row.createCell(3).setCellValue(String.valueOf(vo.getItemPrice()));
			row.createCell(3).setCellValue(vo.getItemCount());
			row.createCell(4).setCellValue(String.valueOf(vo.getItemPrice()));
		}
	
			return wb;  
	    }
	
	//PDF 导出
	@Override
	public boolean pluDayPdf( HttpServletResponse response,List<ReportPluDayItem> list,String startTime,String endTime,String revenueName) throws Throwable {  
		response.setContentType("application/pdf");  
		response.setHeader("Content-disposition", "attachment;filename=ReportPluDayItem.pdf");  
		OutputStream ouputStream;
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
	      
	      
	    Anchor anchorTarget3 = new Anchor("Create Time :"+(new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
	    Paragraph paragraph3 = new Paragraph();
	    paragraph3.setSpacingBefore(10);
	    paragraph3.add(anchorTarget3);
	    document.add(paragraph3);
	      
      
        //record header field
        Table t=new Table(5);
        float[] widths={1f,1f,1f,1f,1f};
        t.setWidths(widths);
        t.setWidth(100);
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        Cell c1 = new Cell(new Paragraph("Main Category",tableFont));
        t.addCell(c1);
        c1 = new Cell(new Paragraph("Sub Category",tableFont));
        t.addCell(c1);
        c1 = new Cell(new Paragraph("Item",tableFont));
        t.addCell(c1);
   /*         c1 = new Cell(new Paragraph("Price",tableFont));
        t.addCell(c1); */
        c1 = new Cell(new Paragraph("Amount Qty",tableFont));
        t.addCell(c1); 
        c1 = new Cell(new Paragraph("Amount Price",tableFont));
        t.addCell(c1); 
        
        for(int i=0;i<list.size();i++){
        	ReportPluDayItem vo = list.get(i);
            Cell c2=new Cell(new Paragraph(vo.getItemMainCategoryName(), headFont2));
            t.addCell(c2);
            c2=new Cell(new Paragraph(vo.getItemCategoryName(), headFont2));
            t.addCell(c2);
            c2=new Cell(new Paragraph(vo.getItemName(), headFont2));
            t.addCell(c2);
           // c2=new Cell(new Paragraph(String.valueOf(vo.getItemPrice()), headFont2));
           // t.addCell(c2);
            c2=new Cell(new Paragraph(String.valueOf(vo.getItemCount()), headFont2));
            t.addCell(c2);
            c2=new Cell(new Paragraph(String.valueOf("$"+(vo.getItemPrice().multiply(new BigDecimal(vo.getItemCount())))), headFont2));
                t.addCell(c2);
            }
            
		   document.add(t);
	
		
 
	  
	  return true;
	 }

	
	@Override
	public List<ReportPluDayItem> queryItemCategory(
			ReportPluDayItem reportPluDayItem) {
		// TODO Auto-generated method stub
		return reportPluDayItemMapper.queryItemCategory(reportPluDayItem);
	}
	@Override
	public List<ReportPluDayItem> queryItem(ReportPluDayItem reportPluDayItem) {
		// TODO Auto-generated method stub
		return reportPluDayItemMapper.queryItem(reportPluDayItem);
	}
	@Override
	public int queryItemCount(
			ReportPluDayItem reportPluDayItem) {
		// TODO Auto-generated method stub
		return reportPluDayItemMapper.queryItemCount(reportPluDayItem);
	}
	@Override
	public List<ReportPluDayItem> querySalesMainCategory(
			HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportPluDayItemMapper.querySalesMainCategory(map);
	}
	@Override
	public List<ReportPluDayItem> queryReoprtItemGroup(
			HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportPluDayItemMapper.queryReoprtItemGroup(map);
	}
	@Override
	public int getItemCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportPluDayItemMapper.getItemReportCount(map);
	}
	@Override
	public int getAllTotal(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return reportPluDayItemMapper.getAllItemReportTotal(map);
	}
	
	
	
	
	
	
}
