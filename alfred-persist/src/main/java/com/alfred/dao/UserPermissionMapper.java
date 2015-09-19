package com.alfred.dao;

import com.alfred.model.UserPermission;
import java.util.List;

public interface UserPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserPermission userpermission);

    List<UserPermission> selectByParam(UserPermission userpermission);

    UserPermission selectByPrimaryKey(Integer id);

    int updateById(UserPermission userpermission);
    
    List<UserPermission> selectByUserId(UserPermission userpermission);
    
    int selectByUserIdPer(UserPermission userpermission);

}