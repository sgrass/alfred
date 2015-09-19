package com.alfred.service;

import java.util.List;

import com.alfred.model.Modifier;
import com.alfred.pagination.Pagination;
import com.alfred.vo.ModifierVO;

public interface ModifierService {

	int deleteByPrimaryKey(Integer id) throws Exception;

	int deleteById(Integer id, String status) throws Exception;

	int insert(Modifier modifier) throws Exception;

	int insert(Modifier modifier, Integer templateCategoryId) throws Exception;

	List<Modifier> selectByParam(Modifier modifier) throws Exception;

	Integer selectCountByParam(Modifier modifier) throws Exception;

	List<Modifier> selectPageByParam(Modifier modifier, Pagination page) throws Exception;

	List<Modifier> selectByRestaurant(Integer restaurantId, Integer isactive, Integer type) throws Exception;

	List<ModifierVO> selectModifierCategory(Integer restaurantId) throws Exception;

	Modifier selectByPrimaryKey(Integer id) throws Exception;

	int updateById(Modifier modifier) throws Exception;

	int updateByCategoryId(Integer restaurantId, Integer categoryId) throws Exception;
}
