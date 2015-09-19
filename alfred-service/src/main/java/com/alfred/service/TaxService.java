package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import com.alfred.model.Tax;
import com.alfred.model.TaxCategory;
import com.alfred.vo.TaxCategoryVO;

public interface TaxService {
	
	int deleteByPrimaryKey(Integer id) throws Exception;

	int insert(Tax tax) throws Exception;

	List<Tax> selectByParam(Tax tax) throws Exception;
	
    List<TaxCategory> selectByRestaurantId(TaxCategory taxCategory) throws Exception;

	List<Tax> selectTaxList(Tax tax) throws Exception;
	
	int updateById(Tax tax) throws Exception;
	
	List<TaxCategory> selectByParam(TaxCategory taxCategory) throws Exception;
	
	List<TaxCategoryVO> selectByParam(TaxCategoryVO taxCategory) throws Exception;
	
	
	int insertTaxCategory(String taxCategoryName,int companyId,int restaurantId,int tax1Id,int tax2Id,int tax2OnValue,int tax3Id,int tax3OnValue) throws Exception;

	int updateTaxCategory(int taxGroupId,String taxCategoryName,int companyId,int restaurantId,int tax1Id,int tax2Id,int tax2OnValue,int tax3Id,int tax3OnValue) throws Exception;
	
	int delTaxCategory(int taxGroupId,int restaurantId) throws Exception;
	
	int updateByCategoryId(TaxCategory tc) throws Exception;
	
    int getIsDelCount(HashMap<String,Object> map);


	
	
	
}
