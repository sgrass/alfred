package com.alfred.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.ModifierMapper;
import com.alfred.model.Modifier;
import com.alfred.pagination.Pagination;
import com.alfred.service.ModifierService;
import com.alfred.util.MapUtil;
import com.alfred.vo.ModifierVO;

@Transactional
@Service("modifierService")
public class ModifierServiceImpl implements ModifierService {

	private static Log log = LogFactory.getLog(ModifierServiceImpl.class);
	
	@Autowired
	@Qualifier("modifierMapper")
	private ModifierMapper modifierMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return 0;
	}

	@Override
	public int insert(Modifier modifier) {
		return modifierMapper.insert(modifier);
	}

	@Override
	public List<Modifier> selectByParam(Modifier modifier) {
		return modifierMapper.selectByParam(modifier);
	}

	@Override
	public List<ModifierVO> selectModifierCategory(Integer restaurantId) {
		return modifierMapper.selectModifierCategory(restaurantId);
	}

	@Override
	public Modifier selectByPrimaryKey(Integer id) {
		return modifierMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(Modifier modifier) {
		this.updateByCategoryId(modifier.getRestaurantId(),modifier.getCategoryId());
		modifier.setRestaurantId(null);
		modifier.setCategoryId(null);
		return modifierMapper.updateById(modifier);
	}

	@Override
	public int updateByCategoryId(Integer restaurantId, Integer categoryId) {
		Modifier modifier = new Modifier();
		modifier.setRestaurantId(restaurantId);
		modifier.setCategoryId(categoryId);
		return modifierMapper.updateByCategoryId(modifier);
	}

	@Override
	public int insert(Modifier modifier, Integer templateCategoryId) throws Exception {
		int resultRow = 0;
		resultRow += this.insert(modifier);
		if (templateCategoryId != null && templateCategoryId > 0) {
			Modifier param = new Modifier();
			param.setCategoryId(templateCategoryId);
			param.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			param.setType(1);
			List<Modifier> modifierList = this.selectByParam(param);
			for (Modifier m : modifierList) {
				m.setId(null);
				m.setRestaurantId(modifier.getRestaurantId());
				m.setCategoryId(modifier.getId());
				resultRow += this.insert(m);
			}
		}
		return resultRow;
	}

	@Override
	public List<Modifier> selectByRestaurant(Integer restaurantId, Integer isactive, Integer type) throws Exception {
		
		Modifier modifier = new Modifier();
		modifier.setRestaurantId(restaurantId);
		modifier.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
		modifier.setType(0);
		return this.selectByParam(modifier);
	}

	@Override
	public int deleteById(Integer id, String status) throws Exception {
		Modifier param = new Modifier();
		param.setId(id);
		param.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		if (StringUtils.isBlank(status)) {
			return modifierMapper.updateById(param);
		} else {
			modifierMapper.updateById(param);
			Modifier modifier = new Modifier();
			modifier.setCategoryId(id);
			return modifierMapper.deleteByCategoryId(modifier);
		}
	}

	@Override
	public Integer selectCountByParam(Modifier modifier) throws Exception {
		return modifierMapper.selectCountByParam(modifier);
	}

	@Override
	public List<Modifier> selectPageByParam(Modifier modifier, Pagination page) throws Exception {
		Map<String, Object> map = null;;
		map = MapUtil.toMap(modifier);
		map.put("startRow", page.getStartRow() > 0 ? page.getStartRow() - 1 : 0);
		map.put("perPageSize", page.getItemsPerPage());
		return modifierMapper.selectPageByParam(map);
	}

}
