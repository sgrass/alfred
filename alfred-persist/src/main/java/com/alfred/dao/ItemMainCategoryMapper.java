package com.alfred.dao;

import com.alfred.model.ItemMainCategory;
import com.alfred.vo.ItemMainCategoryVO;

import java.util.List;

public interface ItemMainCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemMainCategory itemMainCategory);

    List<ItemMainCategory> selectByParam(ItemMainCategory itemMainCategory);

    ItemMainCategory selectByPrimaryKey(Integer id);

    int updateById(ItemMainCategory itemMainCategory);
    
    List<ItemMainCategoryVO> selectByRestaurant(Integer restaurantId);
    
    int  updatePrintGroupId(ItemMainCategory itemMainCategory);
}