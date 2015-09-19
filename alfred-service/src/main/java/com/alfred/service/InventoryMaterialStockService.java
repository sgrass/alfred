package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alfred.model.InventoryMaterialStock;
import com.alfred.model.InventoryRecipeMaterial;
import com.alfred.model.ReportHourly;
import com.alfred.vo.InventoryMaterialStockVO;

public interface InventoryMaterialStockService {

	int deleteByPrimaryKey(Integer id) throws Exception;

	int insert(InventoryMaterialStock inventoryMaterialStock) throws Exception;

	List<InventoryMaterialStock> selectByParam(InventoryMaterialStock inventoryMaterialStock) throws Exception;

    List<InventoryMaterialStockVO> selectStock(InventoryMaterialStock inventoryMaterialStock)throws Exception;
    
    public int deleteById(InventoryMaterialStock inventoryMaterialStock) throws Exception ;
	
	InventoryMaterialStock selectByPrimaryKey(Integer id) throws Exception;

	int updateById(InventoryMaterialStock inventoryMaterialStock) throws Exception;
	
    List<InventoryMaterialStock> selectByParamReoprt(HashMap<String,Object> map) throws Exception;
    
    int selectByParamReoprtCount(HashMap<String,Object> map) throws Exception;
    
    public HSSFWorkbook exportReportExcel(List<InventoryMaterialStock> list,String startTime,String endTime) throws Exception;
    
   	public boolean exportReportPdf(HttpServletResponse response,List<InventoryMaterialStock> list,String startTime,String endTime) throws Exception,Throwable;
   	
    
}
