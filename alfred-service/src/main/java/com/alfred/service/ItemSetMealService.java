package com.alfred.service;

import java.util.List;

import com.alfred.model.ItemDetail;
import com.alfred.model.ItemSetMeal;
import com.alfred.pagination.Pagination;
import com.alfred.vo.ItemSetMealVO;

public interface ItemSetMealService {
	
    int deleteByPrimaryKey(Integer id)throws Exception;

    int insert(ItemDetail itemDetail,String[] itemSetMeal)throws Exception;

    List<ItemSetMeal> selectByParam(ItemSetMeal itemSetMeal)throws Exception;

    ItemSetMeal selectByPrimaryKey(Integer id)throws Exception;

    int updateById(ItemDetail itemDetail,String[] itemSetMeal)throws Exception;
    
	int deleteCascadById(Integer restaurantId ,Integer itemId, Integer userId) throws Exception;
    
    List<ItemSetMealVO> selectSetMealPageByParam(ItemDetail itemDetail, Pagination page)throws Exception;


}
