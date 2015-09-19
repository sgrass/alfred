package com.alfred.dao;

import com.alfred.model.ItemDetail;
import com.alfred.model.ItemModifier;
import com.alfred.vo.ItemModifierVO;

import java.util.List;

public interface ItemModifierMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByItemId(ItemModifier itemModifier);
    
    int deleteBySubCateId(ItemModifier itemModifier);

    int insert(ItemModifier itemModifier);

    List<ItemModifier> selectByParam(ItemModifier itemModifier);
    
    List<ItemModifierVO> selectItemModifier(ItemDetail itemDetail);

    ItemModifier selectByPrimaryKey(Integer id);

    int updateById(ItemModifier itemModifier);
}