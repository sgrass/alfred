package com.alfred.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.SysRestTemplate;
import com.alfred.dao.RestaurantMapper;
import com.alfred.dao.TaxCategoryMapper;
import com.alfred.dao.TaxMapper;
import com.alfred.model.Restaurant;
import com.alfred.model.Tax;
import com.alfred.model.TaxCategory;
import com.alfred.service.RestaurantService;
import com.alfred.vo.RestaurantUser;

@Transactional
@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

	private static Log log = LogFactory.getLog(RestaurantServiceImpl.class);
	@Autowired
	@Qualifier("restaurantMapper")
	private RestaurantMapper restaurantMapper = null;

	@Autowired
	@Qualifier("taxMapper")
	private TaxMapper taxMapper = null;

	@Autowired
	@Qualifier("taxCategoryMapper")
	private TaxCategoryMapper taxCategoryMapper = null;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		int res = 1;
		Restaurant restaurant = new Restaurant();
		restaurant.setId(id);
		restaurant.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
		int flag = restaurantMapper.updateById(restaurant);
		if (flag > 0) {
			res = 0;
		}
		return res;
	}

	@Override
	public int insert(Restaurant restaurant) {
		int newId = 0;
		int flag = restaurantMapper.insert(restaurant);

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> maptaxc = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> maptaxc1 = new HashMap<Integer, Integer>();

		int restId = SysRestTemplate.getRestConfigMap().get(restaurant.getType());
		log.info("restId=" + restId);
		// map.put("restId", 19);
		// map.put("restaurantId", restaurant.getId());
		// map.put("companyId", restaurant.getCompanyId());
		// taxMapper.batchInsert(map);
		// taxCategoryMapper.batchInsert(map);

		// 逻辑整理
		Tax tax = new Tax();
		TaxCategory taxCategory = new TaxCategory();
		tax.setRestaurantId(restaurant.getId());
		tax.setRestaurantId(18);
		List<Tax> taxList = taxMapper.selectByParam(tax);
		taxCategory.setRestaurantId(18);
		taxCategory.setStatus(1);
		List<TaxCategory> taxCList = taxCategoryMapper.selectByParams(taxCategory);
        
		
		
		for (Tax t : taxList) {
			int oldtId = t.getId();
			t.setId(null);
			t.setCompanyId(restaurant.getCompanyId());
			t.setRestaurantId(restaurant.getId());
			t.setCreateTime(new Date());
			t.setUpdateTime(new Date());
			taxMapper.insert(t);
			map.put(oldtId, t.getId());

		}

		for (TaxCategory tc : taxCList) {
			if (tc.getIndex().equals(0)) {
				int oldtaxc=tc.getId();
				tc.setId(null);
				tc.setCompanyId(restaurant.getCompanyId());
				tc.setRestaurantId(restaurant.getId());
				taxCategoryMapper.insert(tc);
				maptaxc.put(oldtaxc, tc.getId());

			}

		}
		
		

		for (TaxCategory tc : taxCList) {
			
			if(tc.getIndex().equals(0)){
				
				maptaxc1 = new HashMap<Integer, Integer>();
			}
			if (tc.getIndex()>0) {
				int tcId=tc.getId();
				tc.setId(null);
				tc.setTaxCategoryId(maptaxc.get(tc.getTaxCategoryId()));
				tc.setCompanyId(restaurant.getCompanyId());
				tc.setRestaurantId(restaurant.getId());
				tc.setTaxId(map.get(tc.getTaxId()));
				tc.setTaxOnId(maptaxc1.get(tc.getTaxOnId()));
				taxCategoryMapper.insert(tc);
				maptaxc1.put(tcId, tc.getId());
			}

		}

		if (flag > 0) {
			newId = restaurant.getId();
		}
		log.info("restaurantId==" + newId);
		return newId;

	}

	@Override
	public List<Restaurant> selectByParam(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return restaurantMapper.selectByParam(restaurant);
	}

	@Override
	public int updateById(Restaurant restaurant) {
		// TODO Auto-generated method stub
		int res = 1;
		int flag = restaurantMapper.updateById(restaurant);
		if (flag > 0) {
			res = 0;
		}
		return res;
	}

	@Override
	public List<Restaurant> selectByStatus(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return restaurantMapper.selectByStatus(restaurant);
	}

	@Override
	public Restaurant selectByRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		List<Restaurant> list = restaurantMapper.selectByRestaurant(restaurant);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<RestaurantUser> selectByResM(RestaurantUser restaurantUser) {
		// TODO Auto-generated method stub
		return restaurantMapper.selectByResM(restaurantUser);
	}

	@Override
	public List<Restaurant> selectByCompany(Integer companyId) {
		Restaurant param = new Restaurant();
		param.setCompanyId(companyId);
		return this.selectByParam(param);
	}

	@Override
	public Restaurant selectByPrimaryKey(Integer id) {
		return restaurantMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Restaurant> selectRestList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return restaurantMapper.selectRestList(map);
	}

	@Override
	public int updateImgById(Restaurant restaurant) throws Exception {
		// TODO Auto-generated method stub
		int res = 1;
		int flag = restaurantMapper.updateImgById(restaurant);
		if (flag > 0) {
			res = 0;
		}
		return res;
	}

}
