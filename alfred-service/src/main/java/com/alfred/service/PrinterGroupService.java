package com.alfred.service;

import java.util.List;

import com.alfred.model.ItemCategory;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemMainCategory;
import com.alfred.model.PrinterGroup;

public interface PrinterGroupService {
	
	    int deleteByPrimaryKey(int id,int restaurantId,int companyId,int userId) throws Exception;
	    
	    int deletePtint(int id,int restaurantId,int companyId) throws Exception;


	    int insert(String[] printerId,String groupName,int resId,int comId) throws Exception;

	    List<PrinterGroup> selectByParam(PrinterGroup printerGroup) throws Exception;

	    PrinterGroup selectByPrimaryKey(Integer id) throws Exception;

	    int updateById(int id,String[] printerId,  String groupName,int restaurantId,int companyId) throws Exception;
	
	 /*   int updateGroupId(ItemCategory itemCategory);
	    
	    int  updatePrintGroupId(ItemMainCategory itemMainCategory);
	    
	    int updateGroupId(ItemDetail itemDetail);*/


	
}
