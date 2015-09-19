package com.alfred.dao;

import com.alfred.model.ItemCategory;
import java.util.List;

public interface ItemCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemCategory itemCategory);

    List<ItemCategory> selectByParam(ItemCategory itemCategory);

    ItemCategory selectByPrimaryKey(Integer id);

    int updateById(ItemCategory itemCategory);
    
    int updateGroupId(ItemCategory itemCategory);

    int updateByMainCateId(ItemCategory itemCategory);
}