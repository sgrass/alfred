package com.alfred.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.UserRestaurantMapper;
import com.alfred.model.UserRestaurant;
import com.alfred.service.UserRestaurantService;



@Transactional
@Service("userRestaurantService")
public class UserRestaurantServiceImpl implements UserRestaurantService{

	private static Log log = LogFactory.getLog(UserRestaurantServiceImpl.class);

	
	@Autowired
	@Qualifier("userRestaurantMapper")
	private UserRestaurantMapper userRestaurantMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(UserRestaurant userRestaurant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<UserRestaurant> selectByParam(UserRestaurant userRestaurant) {
		// TODO Auto-generated method stub
	  return userRestaurantMapper.selectByParam(userRestaurant);

	}
	@Override
	public UserRestaurant selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int updateById(UserRestaurant userRestaurant) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateManPower(String[] userIds, int revenueId,int restaurantId) {
		  int res=1;
		//新增用户惊喜操作
		  UserRestaurant u=new UserRestaurant();
		  u.setRestaurantId(restaurantId);
		  u.setRevenueId(revenueId);
		  userRestaurantMapper.delByRevenueId(u);
		  if(userIds!=null){
			 //撤销时把表里面的revenueId设置为空
			for (String userId : userIds) {
				UserRestaurant temp = new UserRestaurant();
				temp.setRestaurantId(restaurantId);
				temp.setUserId(Integer.parseInt(userId));
				temp.setRevenueId(revenueId);
				userRestaurantMapper.insert(temp);
			}
		}
		return res;
	}

	@Override
	public List<UserRestaurant> selectByRestaurantId(Integer restaurantId, Integer companyId, Integer type) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurantId", restaurantId);
		map.put("companyId", companyId);
		map.put("type", type);
		
		return userRestaurantMapper.selectByRestaurantId(map);
	}

}
