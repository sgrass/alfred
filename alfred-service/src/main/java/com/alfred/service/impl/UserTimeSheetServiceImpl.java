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
import com.alfred.dao.UserTimeSheetMapper;
import com.alfred.model.UserTimeSheet;
import com.alfred.service.UserTimeSheetService;
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
@Service("userTimeSheetService")
public class UserTimeSheetServiceImpl implements UserTimeSheetService{

	private static Log log = LogFactory.getLog(UserTimeSheetServiceImpl.class);
	@Autowired
	@Qualifier("userTimeSheetMapper")
	private UserTimeSheetMapper userTimeSheetMapper = null;
	
	@Override
	public List<UserTimeSheet> queryUserTimeList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return userTimeSheetMapper.queryUserTimeList(map);
	}
	@Override
	public 	HSSFWorkbook  exportExcel(List<UserTimeSheet> userTimeSheetList, String startTime,
		 String endTime, String userName) {
		String[] excelHeader = { "Restaurant Date", "EMP ID", "EMP Name",
				"Login                       Time",
				"Logout                      Time"
				};
	    SimpleDateFormat fmt=	new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	    HSSFWorkbook wb = new HSSFWorkbook();  
        HSSFSheet sheet = wb.createSheet("UserTimeSheetWWWW");  
        HSSFRow row0 = sheet.createRow((int) 0);  
    	HSSFCell cel0 = row0.createCell(0);
    	cel0.setCellValue("Business Time:"+startTime+"--"+endTime);
    	HSSFRow rowUserName = sheet.createRow((int) 1);  
    	HSSFCell celUserName = rowUserName.createCell(0);
    	celUserName.setCellValue("User Name:"+userName);
    	 
        HSSFRow row2 = sheet.createRow((int) 2);  
    	HSSFCell cel2 = row2.createCell(0);
    	cel2.setCellValue("Create Time:"+fmt.format(new Date()));
    	HSSFRow row = sheet.createRow((int) 4); 
        HSSFCellStyle style = wb.createCellStyle();  
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
			for (int i = 0; i < excelHeader.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellValue(excelHeader[i]);
				sheet.autoSizeColumn(i);
			}
			for (int i = 0; i < userTimeSheetList.size(); i++) {
				row = sheet.createRow(i + 5);
				UserTimeSheet vo = userTimeSheetList.get(i);
				row.createCell(0).setCellValue(fmt.format(vo.getBusinessDate()));
				row.createCell(1).setCellValue(vo.getEmpId());
				row.createCell(2).setCellValue(vo.getEmpName());
				row.createCell(3).setCellValue(fmt.format(vo.getLoginTime()));
				row.createCell(4).setCellValue(fmt.format(vo.getLogoutTime()));
			}
		
		return wb;  
	    }

	@Override
	public boolean exportPdf(HttpServletResponse response,
			List<UserTimeSheet> userTimeSheetList, String startTime,
			String endTime, String userName) throws IOException, Throwable {
		
		
		response.setContentType("application/pdf");  
		response.setHeader("Content-disposition", "attachment;filename=UserTimeSheet.pdf");  
		OutputStream ouputStream;
		SimpleDateFormat fmt=	new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
	    ouputStream = response.getOutputStream();
	    Document document = new Document();// 建立一个Document对象
	    document.setPageSize(PageSize.A4);// 设置页面大小
	    PdfWriter.getInstance(document, ouputStream);// 建立一个PdfWriter对象
	    document.open();
	    BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
	    Font tableFont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小	
	    Font headFont2 = new Font(bfChinese, 9, Font.NORMAL);// 设置字体大小
	    Anchor anchorTarget = new Anchor("Business Date:"+startTime+"--"+endTime);
        Paragraph paragraph1 = new Paragraph();
        paragraph1.setSpacingBefore(10);
        paragraph1.add(anchorTarget);
        document.add(paragraph1);
        
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

        Table t=new Table(5);

        float[] widths={1f,1f,1f,1f,1f};
        t.setWidths(widths);
        t.setWidth(100);
        t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        
        Cell c1 = new Cell(new Paragraph("Date",tableFont));
        t.addCell(c1);
        c1 = new Cell(new Paragraph("Emp ID",tableFont));
        t.addCell(c1);
        c1 = new Cell(new Paragraph("Emp Name",tableFont));
        t.addCell(c1);
        c1 = new Cell(new Paragraph("Login Time",tableFont));
        t.addCell(c1);
        c1 = new Cell(new Paragraph("Logout Time",tableFont));
        t.addCell(c1); 
	     
	            
        for(int i=0;i<userTimeSheetList.size();i++){
        	
        	UserTimeSheet vo = userTimeSheetList.get(i);
            Cell  c2=new Cell(new Paragraph(String.valueOf(fmt.format(vo.getBusinessDate())), headFont2));
        	t.addCell(c2);
        	c2=new Cell(new Paragraph(String.valueOf(vo.getEmpId()), headFont2));
            t.addCell(c2);
            c2=new Cell(new Paragraph(String.valueOf(vo.getEmpName()), headFont2));
            t.addCell(c2);
            c2=new Cell(new Paragraph(fmt.format(vo.getLoginTime()), headFont2));
            t.addCell(c2);
            c2=new Cell(new Paragraph(fmt.format(vo.getLogoutTime()), headFont2));
            t.addCell(c2);
         

        }
        
		 document.add(t);
	     document.close();
	     ouputStream.flush();  
	     ouputStream.close();   
		  return true;
		 }

	@Override
	public int getUserTimeCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return userTimeSheetMapper.getUserTimeCount(map);
	}

}
