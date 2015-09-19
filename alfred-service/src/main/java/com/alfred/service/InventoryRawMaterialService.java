package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.alfred.model.InventoryRawMaterial;
import com.alfred.vo.InventoryRawMaterialVO;

public interface InventoryRawMaterialService {

	int deleteByPrimaryKey(Integer id)throws Exception;

	int insert(InventoryRawMaterial inventoryRawMaterial)throws Exception;

	List<InventoryRawMaterial> selectByParam(InventoryRawMaterial inventoryRawMaterial)throws Exception;

	InventoryRawMaterial selectByPrimaryKey(Integer id)throws Exception;

	int updateById(InventoryRawMaterial inventoryRawMaterial)throws Exception;
	
	List<InventoryRawMaterialVO> selectByParamReoprt(HashMap<String,Object> map) throws Exception;
	    
    int selectByParamReoprtCount(HashMap<String,Object> map) throws Exception;
    
    public HSSFWorkbook exportReportExcel(List<InventoryRawMaterialVO> list,String startTime,String endTime) throws Exception;
    
	public boolean exportReportPdf(HttpServletResponse response,List<InventoryRawMaterialVO> list,String startTime,String endTime) throws Exception,Throwable;
	
}
