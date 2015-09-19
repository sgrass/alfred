package com.alfred.dao;

import com.alfred.model.Places;

import com.alfred.vo.PlaceTable;

import java.util.List;

public interface PlacesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Places places);

    List<Places> selectByParam(Places places);

    Places selectByPrimaryKey(Integer id);

    int updateById(Places places);
  
    List<PlaceTable> selectByRevenueId(Integer revenueId);
    
    List<Places> selectByPlace(Places places);
    
}