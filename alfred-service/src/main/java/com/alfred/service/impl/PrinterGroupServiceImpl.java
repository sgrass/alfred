package com.alfred.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.ItemCategoryMapper;
import com.alfred.dao.ItemDetailMapper;
import com.alfred.dao.ItemMainCategoryMapper;
import com.alfred.dao.PrinterGroupMapper;
import com.alfred.dao.PrinterMapper;
import com.alfred.dao.RevenueCenterMapper;
import com.alfred.model.ItemCategory;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemMainCategory;
import com.alfred.model.Printer;
import com.alfred.model.PrinterGroup;
import com.alfred.model.RevenueCenter;
import com.alfred.service.PrinterGroupService;
@Transactional
@Service("printerGroupService")
public class PrinterGroupServiceImpl implements PrinterGroupService {
	private static Log log = LogFactory.getLog(PrinterGroupServiceImpl.class);
	@Autowired
	@Qualifier("printerGroupMapper")
	private PrinterGroupMapper printerGroupMapper = null;
	
	@Autowired
	@Qualifier("printerMapper")
	private PrinterMapper printerMapper = null;
	
	
	@Autowired
	@Qualifier("itemDetailMapper")
	private ItemDetailMapper itemDetailMapper = null;
	
	
	@Autowired
	@Qualifier("itemCategoryMapper")
	private ItemCategoryMapper itemCategoryMapper = null;
	
	@Autowired
	@Qualifier("revenueCenterMapper")
	private RevenueCenterMapper revenueCenterMapper = null;
	
	@Autowired
	@Qualifier("itemMainCategoryMapper")
	private ItemMainCategoryMapper itemMainCategoryMapper = null;
	
	
	@Override
	public int deleteByPrimaryKey(int id,int restaurantId,int companyId,int userId) {
		// TODO Auto-generated method stub
		int flag=0;
		//printerMapper.deleteByPrimaryKey(id);
		PrinterGroup p = new PrinterGroup();
		p.setCompanyId(companyId);
		p.setPrinterGroupId(id);
		p.setRestaurantId(restaurantId);
		
		ItemDetail itemDetail=new ItemDetail();
		itemDetail.setPrinterId(id);
		itemDetail.setRestaurantId(restaurantId);
		itemDetail.setUserId(userId);
		itemDetailMapper.updateById(itemDetail);
		
		ItemCategory itemCategory=new ItemCategory();
		itemCategory.setUpdateTime(new Date());
		itemCategory.setUserId(userId);
		itemCategory.setRestaurantId(restaurantId);
		itemCategory.setPrinterId(id);
		itemCategoryMapper.updateGroupId(itemCategory);
		
		ItemMainCategory itemMainCategory =new ItemMainCategory();
		itemMainCategory.setUpdateTime(new Date());
		itemMainCategory.setPrinterId(id);
		itemMainCategory.setUserId(userId);
		itemMainCategory.setRestaurantId(restaurantId);
		
		itemMainCategoryMapper.updatePrintGroupId(itemMainCategory);
		RevenueCenter revenuecenter=new RevenueCenter();
		revenuecenter.setRestaurantId(restaurantId);
		revenuecenter.setUpdateTime(new Date());
		revenuecenter.setPrintId(id);
		revenueCenterMapper.updateByPrinter(revenuecenter);
		
		printerGroupMapper.deleteGroupId(p);
		return flag;
	}
	
	
	
	
	
	
	@Override
	public int insert(String[] printerId,  String groupName,int restaurantId,int companyId) {
		int flag=0;
			Printer printer = new Printer();
			printer.setCompanyId(companyId);
			printer.setRestaurantId(restaurantId);
			printer.setType(CommonStatusConstant.IS_ACTIVE_DISABLE);
			printer.setPrinterGroupName(groupName);
			printer.setCreateTime(new Date());
			printer.setUpdateTime(new Date());
			printerMapper.insert(printer);
			if (printerId.length > 0) {
				for (int i = 0; i < printerId.length; i++) {
					PrinterGroup printerGroup = new PrinterGroup();
					printerGroup.setCompanyId(companyId);
					printerGroup.setRestaurantId(restaurantId);
					printerGroup.setPrinterGroupId(printer.getId());
					printerGroup.setPrinterId(Integer.parseInt(printerId[i]));
					printerGroupMapper.insert(printerGroup);
				}

			}
		 flag=printer.getId();
		 return flag;
	}
	@Override
	public List<PrinterGroup> selectByParam(PrinterGroup printerGroup) {
		return printerGroupMapper.selectByParam(printerGroup);


	}
	@Override
	public PrinterGroup selectByPrimaryKey(Integer id) {
		return null;
	}
	@Override
	public int updateById(int id,String[] printerId,String groupName,int restaurantId,int companyId) {
		int flag=0;
			Printer printer = new Printer();
			printer.setId(id);
			printer.setCompanyId(companyId);
			printer.setRestaurantId(restaurantId);
			printer.setType(CommonStatusConstant.IS_ACTIVE_DISABLE);
			printer.setPrinterGroupName(groupName);
			printer.setCreateTime(new Date());
			printer.setUpdateTime(new Date());
			printerMapper.updateById(printer);
			//删除组内的信息
			PrinterGroup p=new PrinterGroup();
			p.setCompanyId(companyId);
			p.setPrinterGroupId(id);
			p.setRestaurantId(restaurantId);
			printerGroupMapper.deleteGroupId(p);
			
			if (printerId.length > 0) {
				for (int i = 0; i < printerId.length; i++) {
					PrinterGroup printerGroup = new PrinterGroup();
					printerGroup.setCompanyId(companyId);
					printerGroup.setRestaurantId(restaurantId);
					printerGroup.setPrinterGroupId(printer.getId());
					printerGroup.setPrinterId(Integer.parseInt(printerId[i]));
					printerGroupMapper.insert(printerGroup);
				}

			}
		return flag;
	}






	@Override
	public int deletePtint(int id, int restaurantId, int companyId)
			throws Exception {
		int flag=0;
		printerMapper.deleteByPrimaryKey(id);
		PrinterGroup p = new PrinterGroup();
		p.setCompanyId(companyId);
		p.setPrinterGroupId(id);
		p.setRestaurantId(restaurantId);
	
		
		
		
		printerGroupMapper.deleteGroupId(p);
		return flag;
	}
	
}
