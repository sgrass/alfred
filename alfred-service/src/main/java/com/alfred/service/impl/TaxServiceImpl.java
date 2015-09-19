package com.alfred.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.ItemDetailMapper;
import com.alfred.dao.TaxCategoryMapper;
import com.alfred.dao.TaxMapper;
import com.alfred.model.ItemDetail;
import com.alfred.model.Tax;
import com.alfred.model.TaxCategory;
import com.alfred.service.TaxService;
import com.alfred.vo.TaxCategoryVO;

@Transactional
@Service("taxService")
public class TaxServiceImpl implements TaxService{
	
private static Log log = LogFactory.getLog(TaxServiceImpl.class);

	@Autowired
	@Qualifier("taxMapper")
	private TaxMapper taxMapper = null;
	
	@Autowired
	@Qualifier("taxCategoryMapper")
	private TaxCategoryMapper taxCategoryMapper = null;
	
	@Autowired
	@Qualifier("itemDetailMapper")
	private ItemDetailMapper itemDetailMapper = null;

	@Override
	public int deleteByPrimaryKey(Integer id) {
	    int res=1;
		//删除
		Tax tax = new Tax();
		tax.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
		tax.setId(id);
		// TODO Auto-generated method stub
		int flag=taxMapper.updateById(tax);
		if(flag>0){
			res=0;
		}
		
		return res;
	}

	@Override
	public int insert(Tax tax) {
		// TODO Auto-generated method stub
		taxMapper.insert(tax);
		return tax.getId();
				
	}

	@Override
	public List<Tax> selectByParam(Tax tax) {
		// TODO Auto-generated method stub
		return taxMapper.selectByParam(tax);
	}

	@Override
	public int updateById(Tax tax) {
		// TODO Auto-generated method stub
		int res=1;
		int flag = taxMapper.updateById(tax);
		if (flag > 0) {
			res = 0;
		}
		return res;
	}

	@Override
	public List<Tax> selectTaxList(Tax tax) {
		// TODO Auto-generated method stub
		return taxMapper.selectTaxList(tax);
	}

	@Override
	public List<TaxCategoryVO> selectByParam(TaxCategoryVO taxCategory) {
		// TODO Auto-generated method stub
		List<TaxCategoryVO> li=new ArrayList<TaxCategoryVO>();
		li = taxCategoryMapper.selectByTaxCategory(taxCategory);
		return li;
	}

	@Override
	public int insertTaxCategory(String taxCategoryName, int companyId,
			int restaurantId, int tax1Id, int tax2Id, int tax2OnValue,
			int tax3Id, int tax3OnValue) {
		int flag=0;
		// TODO Auto-generated method stub
		//四条记录
		//第一条 新增名称
		TaxCategory taxC = new TaxCategory();;
		taxC.setCompanyId(companyId);
		taxC.setRestaurantId(restaurantId);
		taxC.setIndex(0);
		taxC.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
		taxC.setTaxCategoryName(taxCategoryName);
		taxCategoryMapper.insert(taxC);
		flag=taxC.getId();
			//新增第二条Tax1
	    if (tax1Id != 0) {
				TaxCategory tax1 = new TaxCategory();
				tax1.setCompanyId(companyId);
				tax1.setRestaurantId(restaurantId);
				tax1.setIndex(1);
				tax1.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
				
				tax1.setTaxCategoryId(taxC.getId());
				tax1.setTaxId(tax1Id);
				taxCategoryMapper.insert(tax1);
				if (tax2Id != 0) {
					TaxCategory tax2 = new TaxCategory();
					tax2.setCompanyId(companyId);
					tax2.setRestaurantId(restaurantId);
					tax2.setIndex(2);
					tax2.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
					tax2.setTaxOn(tax2OnValue);
					if(tax2OnValue==1){
						tax2.setTaxOnId(tax1.getId());
					}
					tax2.setTaxCategoryId(taxC.getId());
					tax2.setTaxId(tax2Id);
					taxCategoryMapper.insert(tax2);
					if (tax3Id != 0) {
						TaxCategory tax3 = new TaxCategory();
						tax3.setCompanyId(companyId);
						tax3.setRestaurantId(restaurantId);
						tax3.setIndex(3);
						tax3.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
						tax3.setTaxOn(tax3OnValue);
						if(tax3OnValue==1){
							tax3.setTaxOnId(tax1.getId());
						}else if(tax3OnValue==2){
							tax3.setTaxOnId(tax2.getId());
						}
						tax3.setTaxCategoryId(taxC.getId());
						tax3.setTaxId(tax3Id);
						taxCategoryMapper.insert(tax3);
					}
				}

			}
		
		return flag;
	}

	@Override
	public int updateTaxCategory(int taxGroupId, String taxCategoryName,
			int companyId, int restaurantId, int tax1Id, int tax2Id,
			int tax2OnValue, int tax3Id, int tax3OnValue) {
		// TODO Auto-generated method stub
		//四条记录
		//第一条 新增名称
		TaxCategory taxC = new TaxCategory();;
		taxC.setId(taxGroupId);
		taxC.setTaxCategoryName(taxCategoryName);
		taxCategoryMapper.updateById(taxC);
			//删除其他三条记录
		taxCategoryMapper.deleteByTaxCategoryID(taxGroupId);
		//新增第二条Tax1
		if (tax1Id != 0) {
			TaxCategory tax1 = new TaxCategory();
			tax1.setCompanyId(companyId);
			tax1.setRestaurantId(restaurantId);
			tax1.setIndex(1);
			tax1.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			tax1.setTaxCategoryId(taxGroupId);
			tax1.setTaxId(tax1Id);
			taxCategoryMapper.insert(tax1);
			if (tax2Id != 0) {
				TaxCategory tax2 = new TaxCategory();
				tax2.setCompanyId(companyId);
				tax2.setRestaurantId(restaurantId);
				tax2.setIndex(2);
				tax2.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
				tax2.setTaxOn(tax2OnValue);
				if(tax2OnValue==1){
					tax2.setTaxOnId(tax1.getId());
				}
				tax2.setTaxCategoryId(taxGroupId);
				tax2.setTaxId(tax2Id);
				taxCategoryMapper.insert(tax2);
				if (tax3Id != 0) {
					TaxCategory tax3 = new TaxCategory();
					tax3.setCompanyId(companyId);
					tax3.setRestaurantId(restaurantId);
					tax3.setIndex(3);
					tax3.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
					tax3.setTaxOn(tax3OnValue);
					if(tax3OnValue==1){
						tax3.setTaxOnId(tax1.getId());
					}else if(tax3OnValue==2){
						tax3.setTaxOnId(tax2.getId());
					}
					tax3.setTaxCategoryId(taxGroupId);
					tax3.setTaxId(tax3Id);
					taxCategoryMapper.insert(tax3);
				}
			}
	
		}
		
		return 0;
	}


	@Override
	public int updateByCategoryId(TaxCategory tc) {
		// TODO Auto-generated method stub
		int res=0;
		
		ItemDetail itemDetail=new ItemDetail();
		itemDetail.setTaxCategoryId(tc.getId());
		itemDetail.setRestaurantId(tc.getRestaurantId());
		itemDetail.setUpdateTime(new Date());;
		itemDetailMapper.updateById(itemDetail);
		
		int flag = taxCategoryMapper.updateByCategoryId(tc);
		res=flag;
		return res;
	}

	@Override
	public List<TaxCategory> selectByRestaurantId(TaxCategory taxCategory) {
		// TODO Auto-generated method stub
		return taxCategoryMapper.selectByRestaurantId(taxCategory);
	}

	@Override
	public List<TaxCategory> selectByParam(TaxCategory taxCategory) {
		// TODO Auto-generated method stub
		return taxCategoryMapper.selectByParam(taxCategory);
	}

	@Override
	public int delTaxCategory(int taxGroupId, int restaurantId)
			throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getIsDelCount(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return taxCategoryMapper.getIsDelCount(map);
	}

}
