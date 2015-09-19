package com.alfred.dao;

import com.alfred.model.Modifier;
import com.alfred.vo.ModifierVO;

import java.util.List;
import java.util.Map;

public interface ModifierMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByCategoryId(Modifier modifier);

    int insert(Modifier modifier);

    List<Modifier> selectByParam(Modifier modifier);
    
    Integer selectCountByParam(Modifier modifier);
    
    List<Modifier> selectPageByParam(Map<String, Object> map);
    
    List<ModifierVO> selectModifierCategory(Integer restaurantId);

    Modifier selectByPrimaryKey(Integer id);

    int updateById(Modifier modifier);
    
    int updateByCategoryId(Modifier modifier);
}