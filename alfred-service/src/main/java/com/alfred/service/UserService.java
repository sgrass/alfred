package com.alfred.service;

import java.util.List;
import com.alfred.model.User;
import com.alfred.model.UserRestaurant;
import com.alfred.vo.UserManager;
import com.alfred.vo.UserRestaurantVO;

public interface UserService {
	
	int deleteByPrimaryKey(Integer id) throws Exception;

	int insert(User user) throws Exception;

	List<User> selectByParam(User user) throws Exception ;

	User selectByAccountName(String accountName) throws Exception;

	User selectByEmpId(int empId) throws Exception;

	User selectByPrimaryKey(Integer id) throws Exception;

	int updateById(User user) throws Exception;

	List<User> selectByEmp(User user) throws Exception;

	List<UserManager> selectByUserRes(UserManager user) throws Exception;

	List<UserManager> selectByRestaurantId(UserManager user) throws Exception;

	List<UserRestaurantVO> selectUserRestaurantRes(UserRestaurantVO user) throws Exception;

	User selectByManager(User user) throws Exception;

	int addManager(User user, UserRestaurant userRestaurant, String restaurantId) throws Exception;

	int delManager(int userId) throws Exception;

	int updateManager(User user, UserRestaurant userRestaurant, String restaurantId) throws Exception;

	// 创建员工
	int insertEmp(User user, int restaurantId) throws Exception;
	
	User selectByEmpId(Integer restaurantId, Integer companyId, Integer empId) throws Exception;

}
