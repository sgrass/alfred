package com.alfred.service.impl;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.InventoryMaterialStockMapper;
import com.alfred.dao.InventoryRawMaterialMapper;
import com.alfred.model.InventoryMaterialStock;
import com.alfred.model.InventoryRawMaterial;
import com.alfred.service.InventoryMaterialStockService;
import com.alfred.vo.InventoryMaterialStockVO;
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
@Service("inventoryMaterialStockService")
public class InventoryMaterialStockServiceImpl implements InventoryMaterialStockService {

	@Autowired
	@Qualifier("inventoryMaterialStockMapper")
	private InventoryMaterialStockMapper inventoryMaterialStockMapper = null;
	
	@Autowired
	@Qualifier("inventoryRawMaterialMapper")
	private InventoryRawMaterialMapper inventoryRawMaterialMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {
		return 0;
	}

	@Override
	public int insert(InventoryMaterialStock inventoryMaterialStock) throws Exception {
		int rowResult = 0;
		
		inventoryMaterialStock.setCreateTime(new Date());
		inventoryMaterialStock.setUpdateTime(new Date());
		inventoryMaterialStock.setUnitPrice(inventoryMaterialStock.getStockTotalPrice().divide(new BigDecimal(inventoryMaterialStock.getStockQty())));
		rowResult += inventoryMaterialStockMapper.insert(inventoryMaterialStock);
		
		InventoryRawMaterial inventoryRawMaterial = new InventoryRawMaterial();
		inventoryRawMaterial.setId(inventoryMaterialStock.getMaterialId());
		inventoryRawMaterial.setQuantityCurrent(new BigDecimal(inventoryMaterialStock.getStockQty()));
		rowResult += inventoryRawMaterialMapper.updateAddQtyById(inventoryRawMaterial);
		return rowResult;
	}

	@Override
	public List<InventoryMaterialStock> selectByParam(InventoryMaterialStock inventoryMaterialStock) throws Exception {
		return inventoryMaterialStockMapper.selectByParam(inventoryMaterialStock);
	}

	@Override
	public InventoryMaterialStock selectByPrimaryKey(Integer id) throws Exception {
		return inventoryMaterialStockMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(InventoryMaterialStock inventoryMaterialStock) throws Exception {
		inventoryMaterialStock.setUpdateTime(new Date());
		
		inventoryMaterialStock.setUnitPrice(inventoryMaterialStock.getStockTotalPrice().divide(new BigDecimal(inventoryMaterialStock.getStockQty())));
		return inventoryMaterialStockMapper.updateById(inventoryMaterialStock);
	}
	
	@Override
	public int deleteById(InventoryMaterialStock inventoryMaterialStock) throws Exception {
	  return inventoryMaterialStockMapper.deleteByPrimaryKey(inventoryMaterialStock.getId());
	}
	

	@Override
	public List<InventoryMaterialStockVO> selectStock(
			InventoryMaterialStock inventoryMaterialStock) {
		// TODO Auto-generated method stub
		return inventoryMaterialStockMapper.selectStock(inventoryMaterialStock);
	}

	@Override
	public List<InventoryMaterialStock> selectByParamReoprt(
			HashMap<String, Object> map) throws Exception {
		// TODO Auto-generated method stub
		return inventoryMaterialStockMapper.selectByParamReoprt(map);
	}

	@Override
	public int selectByParamReoprtCount(HashMap<String, Object> map)
			throws Exception {
		// TODO Auto-generated method stub
		return inventoryMaterialStockMapper.selectByParamReoprtCount(map);
	}

	@Override
	public HSSFWorkbook exportReportExcel(List<InventoryMaterialStock> list,
			String startTime, String endTime) throws Exception {
		// TODO Auto-generated method stub
		String[] excelHeader = {"createTime", "materialName", "supplierName", "stockQty","stockTotalPrice" ,"unitPrice"};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Receipt");
		HSSFRow row0 = sheet.createRow((int) 0);
		HSSFCell cel0 = row0.createCell(0);
		cel0.setCellValue("Business Time:" + startTime + "--" + endTime);
	
		HSSFRow row2 = sheet.createRow((int) 1);
		HSSFCell cel2 = row2.createCell(0);
		cel2.setCellValue("Create Time:"+ (new java.text.SimpleDateFormat("MM/dd/yyyy hh:mm:ss")).format(new Date()));
		HSSFRow row = sheet.createRow((int) 3);
		for (int i = 0; i < excelHeader.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellValue(excelHeader[i]);
			sheet.autoSizeColumn(i);
		}
		if (list != null && list.size() > 0) {
			for (int j = 0; j < list.size(); j++) {
				row = sheet.createRow(j + 4);
				InventoryMaterialStock vo = list.get(j);
				row.createCell(0).setCellValue(new java.text.SimpleDateFormat("yyyy-MM-dd").format(vo.getCreateTime()));
				row.createCell(1).setCellValue(vo.getMaterialName());
				row.createCell(2).setCellValue(vo.getSupplierName());
				row.createCell(3).setCellValue(vo.getStockQty());
				row.createCell(4).setCellValue(vo.getStockTotalPrice().toString());
				row.createCell(5).setCellValue(vo.getUnitPrice().toString());
			}
		}
		return wb;
	}

	@Override
	public boolean exportReportPdf(HttpServletResponse response,
			List<InventoryMaterialStock> list, String startTime, String endTime)
			throws Exception, Throwable {
		response.setContentType("application/pdf");
		response.setHeader("Content-disposition","attachment;filename=ReportReceipt.pdf");
		OutputStream ouputStream;
		ouputStream = response.getOutputStream();
		Document document = new Document();// 建立一个Document对象
		document.setPageSize(PageSize.A4);// 设置页面大小
		PdfWriter.getInstance(document, ouputStream);// 建立一个PdfWriter对象
		document.open();
		BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);// 设置中文字体
		// Font titleFont = new Font(bfChinese, 12, Font.BOLD);// 设置字体大小
		Font tableFont = new Font(bfChinese, 10, Font.BOLD);// 设置字体大小
		Font headFont2 = new Font(bfChinese, 9, Font.NORMAL);// 设置字体大小

		Anchor anchorTarget = new Anchor("Business Date:" + startTime + "--"+ endTime);
		Paragraph paragraph1 = new Paragraph();
		paragraph1.setSpacingBefore(10);
		paragraph1.add(anchorTarget);
		document.add(paragraph1);

		Anchor anchorTarget3 = new Anchor("Create Time :"+ (new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss")).format(new Date()));
		Paragraph paragraph3 = new Paragraph();
		paragraph3.setSpacingBefore(10);
		paragraph3.add(anchorTarget3);
		document.add(paragraph3);
		//"createTime", "materialName", "supplierName", "stockQty","stockTotalPrice" ,"unitPrice"
		Table t = new Table(6);
		float[] widths = { 1f, 1f, 1f, 1f ,1f,1f};
		t.setWidths(widths);
		t.setWidth(100);
		t.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
		Cell c1 = new Cell(new Paragraph("createTime", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("materialName", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("supplierName", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("stockQty", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("stockTotalPrice", tableFont));
		t.addCell(c1);
		c1 = new Cell(new Paragraph("unitPrice", tableFont));
		t.addCell(c1);
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				InventoryMaterialStock vo = list.get(i);
				Cell c2 = new Cell(new Paragraph(new java.text.SimpleDateFormat("yyyy-MM-dd").format(vo.getCreateTime()), headFont2));
				t.addCell(c2);
				c2 = new Cell(new Paragraph(String.valueOf(vo.getMaterialName()),headFont2));
				t.addCell(c2);
				c2 = new Cell(new Paragraph(String.valueOf(vo.getSupplierName()),headFont2));
				t.addCell(c2);
				c2 = new Cell(new Paragraph(String.valueOf(vo.getStockQty()), headFont2));
				t.addCell(c2);
				c2 = new Cell(new Paragraph(String.valueOf(vo.getStockTotalPrice()), headFont2));
				t.addCell(c2);
				c2 = new Cell(new Paragraph(String.valueOf(vo.getUnitPrice()), headFont2));
				t.addCell(c2);
			}
		}

		document.add(t);
		document.close();
		ouputStream.flush();
		ouputStream.close();
		return true;
	}

}
