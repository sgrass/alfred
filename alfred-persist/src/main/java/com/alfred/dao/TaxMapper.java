package com.alfred.dao;

import com.alfred.model.Tax;

import java.util.HashMap;
import java.util.List;

public interface TaxMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tax tax);

    List<Tax> selectByParam(Tax tax);
    
    List<Tax> selectTaxList(Tax tax);
 
    Tax selectByPrimaryKey(Integer id);

    int updateById(Tax tax);
    
    int batchInsert(HashMap<String,Object> map);
}