package com.alfred.dao;
import com.alfred.model.User;
import com.alfred.vo.UserManager;
import com.alfred.vo.UserRestaurantVO;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User user);

    List<User> selectByParam(User user);
    
    User selectByPrimaryKey(Integer id);

    int updateById(User user);
    
    List<UserManager> selectByUserRes(UserManager user);
    
    List<User> selectByEmp(User user);
    
    List<User> selectByManager(User user);
    
    List<UserRestaurantVO> selectUserRestaurantRes(UserRestaurantVO user);
    
    User selectByEmpId(Map<String, Object> map);
    
}