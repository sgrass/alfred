package com.alfred.dao;

import com.alfred.model.ItemSetMeal;
import java.util.List;

public interface ItemSetMealMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteBySetMeal(Integer id);

    int insert(ItemSetMeal itemSetMeal);

    List<ItemSetMeal> selectByParam(ItemSetMeal itemSetMeal);

    ItemSetMeal selectByPrimaryKey(Integer id);

    int updateById(ItemSetMeal itemSetMeal);
}