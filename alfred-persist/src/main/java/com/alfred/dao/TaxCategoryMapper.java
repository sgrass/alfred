package com.alfred.dao;

import com.alfred.model.TaxCategory;
import com.alfred.vo.TaxCategoryVO;

import java.util.HashMap;
import java.util.List;

public interface TaxCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TaxCategory taxCategory);

    int getIsDelCount(HashMap<String,Object> map);
    
    List<TaxCategory> selectByRestaurantId(TaxCategory taxCategory);
    
    List<TaxCategory> selectByParam(TaxCategory taxCategory);
    
    List<TaxCategory> selectByParams(TaxCategory taxCategory);
    
    

    TaxCategory selectByPrimaryKey(Integer id);

    int updateById(TaxCategory taxCategory);
    
    List<TaxCategoryVO> selectByTaxCategory(TaxCategoryVO taxCategoryVO);

    int deleteByTaxCategoryID(Integer id); 
    
    
    int updateByCategoryId(TaxCategory taxCategory);
    
    int batchInsert(HashMap<String,Object> map);
    
    
}