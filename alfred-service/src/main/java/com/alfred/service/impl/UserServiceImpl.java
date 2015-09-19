package com.alfred.service.impl;

import java.util.HashMap;
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
import com.alfred.constant.UserConstant;
import com.alfred.dao.PermissionMapper;
import com.alfred.dao.UserMapper;
import com.alfred.dao.UserPermissionMapper;
import com.alfred.dao.UserRestaurantMapper;
import com.alfred.model.Permission;
import com.alfred.model.User;
import com.alfred.model.UserPermission;
import com.alfred.model.UserRestaurant;
import com.alfred.service.UserService;
import com.alfred.vo.UserManager;
import com.alfred.vo.UserRestaurantVO;

@Transactional
@Service("userService")
public class UserServiceImpl implements UserService {
	
	private static Log log = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	@Qualifier("userMapper")
	private UserMapper userMapper = null;
	
	@Autowired
	@Qualifier("userRestaurantMapper")
	private UserRestaurantMapper userRestaurantMapper = null;
	
	@Autowired
	@Qualifier("userPermissionMapper")
	private UserPermissionMapper userPermissionMapper = null;
	
	
	@Autowired
	@Qualifier("permissionMapper")
	private PermissionMapper permissionMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
	    int res=1;
		User u = new User();
		u.setId(id);
		u.setStatus(UserConstant.USER_STATUS_DELETE);
		int flag = userMapper.updateById(u);
		if (flag > 0) {
			res = 0;
		}
		return res;
	}

	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public List<User> selectByParam(User user) {
		return userMapper.selectByParam(user);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(User user) {
		    int res=1;
			int flag = userMapper.updateById(user);
		        if(flag>0){
		        	res=0;	
		        }
		    return res;
	}

	@Override
	public User selectByAccountName(String accountName) {
		User param = new User();
		param.setAccountName(accountName);
		param.setStatus(UserConstant.USER_STATUS_NORMAL);
		List<User> list = this.selectByParam(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int addManager(User user,UserRestaurant userRestaurant,String restaurantId) {
		int newUserId=0;
		userMapper.insert(user);
		userRestaurant.setUserId(user.getId());
		//绑定创建新型和餐厅之间的关系
		String [] resIds=restaurantId.split(",");
		for(String str:resIds){
		    int s=0;
			if(!StringUtils.isBlank(str)){
				s=Integer.parseInt(str);
			}
			userRestaurant.setRestaurantId(s);
			userRestaurantMapper.insert(userRestaurant);
		}
	     //查询所有的perssion
		Permission p=new Permission();
		p.setPermisLevel(CommonStatusConstant.IS_PERMISSION_LEVEL);
		
		List<Permission> l=permissionMapper.selectByParam(p);
		for(Permission tp:l){
			UserPermission userPer = new UserPermission();
			userPer.setPermissionId(tp.getPermissId());
			userPer.setUserId(user.getId());
			userPer.setRule(tp.getPermissRule());
			userPermissionMapper.insert(userPer);
		}
		
		newUserId =user.getId();
		
		return newUserId;
		
	}
	
	@Override
	public int updateManager(User user,UserRestaurant userRestaurant,String restaurantId) {
		
		int res=1;
		userMapper.updateById(user);
		userRestaurant.setUserId(user.getId());
		log.info(user.getCompanyId());
		//绑定创建新型和餐厅之间的关系
		//删除所有绑定的用户与餐厅之间的关联
		userRestaurantMapper.deleteByUserId(user.getId());
		//新增用户与新餐厅之间的关联
		String [] resIds=restaurantId.split(",");
		for(String str:resIds){
		    int s=0;
			if(!StringUtils.isBlank(str)){
				s=Integer.parseInt(str);
			}
			userRestaurant.setRestaurantId(s);
			userRestaurantMapper.insert(userRestaurant);
		}
		int flag=2;
		if(flag>0){
			res=0;	
		}
		return res;
		
	}

	@Override
	public List<UserManager> selectByUserRes(UserManager user) {
		// TODO Auto-generated method stub
		return userMapper.selectByUserRes(user);
	}

	@Override
	public int delManager(int userId) {
	    int res=1;
			//删除
			User u=new User();
			u.setId(userId);
			u.setStatus(UserConstant.USER_STATUS_DELETE);
			int flag=userMapper.updateById(u);
			
			int flags=userRestaurantMapper.deleteByUserId(userId);

			if(flag>0){
				res=0;
			}
			
		

		return res;
	}

	@Override
	public List<User> selectByEmp(User user) {
		// TODO Auto-generated method stub
		return userMapper.selectByEmp(user);
	}

	@Override
	public User selectByEmpId(int empId) {
		// TODO Auto-generated method stub
		User param = new User();
		param.setEmpId(empId);
		List<User> list = this.selectByParam(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public User selectByManager(User user) {
		// TODO Auto-generated method stub
		List<User> list = userMapper.selectByManager(user);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public int insertEmp(User user, int restaurantId) {
		int res=1;
		userMapper.insert(user);
		UserRestaurant u = new UserRestaurant();
		u.setRestaurantId(restaurantId);
		u.setUserId(user.getId());
		int flag = userRestaurantMapper.insert(u);
		if(flag>0){
			res=0;
		}
		return res;
	}

	@Override
	public List<UserManager> selectByRestaurantId(UserManager user) {
		// TODO Auto-generated method stub
		List<UserManager> li=null;
	    li=userMapper.selectByUserRes(user);
		return li;
	}

	@Override
	public List<UserRestaurantVO> selectUserRestaurantRes(UserRestaurantVO user) {
		// TODO Auto-generated method stub
		return userMapper.selectUserRestaurantRes(user);
	}

	@Override
	public User selectByEmpId(Integer restaurantId, Integer companyId, Integer empId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("restaurantId", restaurantId);
		map.put("companyId", companyId);
		map.put("empId", empId);
		map.put("type", UserConstant.USER_TYPE_CLOUD);
		return userMapper.selectByEmpId(map);
	}


	
}
