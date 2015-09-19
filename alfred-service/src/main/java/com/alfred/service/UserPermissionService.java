package com.alfred.service;

import java.util.List;

import com.alfred.model.Permission;
import com.alfred.model.UserPermission;

public interface UserPermissionService {
	
  int deleteByPrimaryKey(Integer id) throws Exception;

  int insert(UserPermission userpermission) throws Exception;

  List<UserPermission> selectByParam(UserPermission userpermission) throws Exception;
  
  List<UserPermission> selectByUserId(Integer userId) throws Exception;

  UserPermission selectByPrimaryKey(Integer id) throws Exception;

  int updateById(UserPermission userpermission) throws Exception;
  
  List<Permission> selectByParamByCommon(Permission permission) throws Exception;
  
  int updateByIds(String content,int checkAll,int userId) throws Exception;
  
  UserPermission selectByPremissionId(int permissionId,int userId) throws Exception;

  int selectByUserId(UserPermission userpermission) throws Exception;



  
  
  
}
