package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import com.alfred.model.Printer;
import com.alfred.vo.PrinterGroupVO;


public interface PrinterService {
	
	
	int deleteByPrimaryKey(Integer id) throws Exception;

	int insert(Printer printer) throws Exception;

	List<Printer> selectByParam(Printer printer) throws Exception;
	
	List<Printer> selectByRestId(Integer companyId, Integer restaurantId) throws Exception;
	
	int updateById(Printer printer) throws Exception;
	
	Printer selectByPrinter(String printer,int id,int resId) throws Exception;
	
    List<PrinterGroupVO> selectPrinterGroup(HashMap<String, Object> map) throws Exception;

	
}
