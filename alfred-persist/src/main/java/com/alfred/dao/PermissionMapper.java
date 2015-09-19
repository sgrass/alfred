package com.alfred.dao;

import com.alfred.model.Permission;
import java.util.List;

public interface PermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Permission permission);

    List<Permission> selectByParam(Permission permission);

    Permission selectByPrimaryKey(Integer id);

    int updateById(Permission permission);
}