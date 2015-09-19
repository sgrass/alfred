package com.alfred.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.dao.PermissionMapper;
import com.alfred.dao.UserPermissionMapper;
import com.alfred.model.Permission;
import com.alfred.model.UserPermission;
import com.alfred.service.UserPermissionService;

@Transactional
@Service("userPermissionService")
public class UserPermissionServiceImpl implements UserPermissionService {

	
	private static Log log = LogFactory.getLog(UserPermissionService.class);

	@Autowired
	@Qualifier("userPermissionMapper")
	private UserPermissionMapper userPermissionMapper = null;
	
	@Autowired
	@Qualifier("permissionMapper")
	private PermissionMapper permissionMapper = null;
	
	
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userPermissionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserPermission userpermission) {
		// TODO Auto-generated method stub
		return userPermissionMapper.insert(userpermission);

	}

	@Override
	public List<UserPermission> selectByParam(UserPermission userpermission) {
		return userPermissionMapper.selectByParam(userpermission);
	}

	@Override
	public UserPermission selectByPrimaryKey(Integer id) {
		return userPermissionMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(UserPermission userpermission) {
		// TODO Auto-generated method stub
		return userPermissionMapper.updateById(userpermission);
	}

	@Override
	public List<UserPermission> selectByUserId(Integer userId) {
		UserPermission param = new UserPermission();
		param.setUserId(userId);
		List<UserPermission> list = this.selectByParam(param);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	@Override
	public List<Permission> selectByParamByCommon(Permission permission) {
		// TODO Auto-generated method stub
		return permissionMapper.selectByParam(permission);
	}

	@Override
	public int updateByIds(String content,int checkAll,int userId) {
		//数据录入逻辑整理
		int flag=0;
		String val[] =content.split(";");
	    for (int i = 0; i < val.length; i++) {
				String values[] = val[i].split(",");

				for (int j = 0; j < val.length; j++) {
					//数据新增
					int checkId = Integer.parseInt(values[3]);
					UserPermission userPer = new UserPermission();
					userPer.setPermissionId(Integer.parseInt(values[1]));
					userPer.setUserId(Integer.parseInt(values[0]));
					userPer.setRule(values[2]);
					//判断数据库里面是否存在该条记录
					UserPermission u = selectByPremissionId(
							Integer.parseInt(values[1]),
							Integer.parseInt(values[0]));
					//执行语句
					if (checkId == 0) {
						if (u == null) {
							//执行新增
							userPermissionMapper.insert(userPer);
							//删除主记录
							UserPermission d = selectByPremissionId(CommonStatusConstant.IS_PERMISSION_LEVEL,userId);
							if(d!=null){
								userPermissionMapper.deleteByPrimaryKey(d.getId());	
							}
						
							
						}
					} else {
						if (u != null) {
							log.info(u.getId());
							//执行删除
							userPermissionMapper.deleteByPrimaryKey(u.getId());
						}
					}

				}

			}
		return flag;
	}

	@Override
	public UserPermission selectByPremissionId(int permissionId,int userId) {
		// TODO Auto-generated method stub
		UserPermission param = new UserPermission();
				param.setPermissionId(permissionId);
				param.setUserId(userId);
				List<UserPermission> list = this.selectByParam(param);
				if (list != null && list.size() > 0) {
					return list.get(0);
				}
				return null;
			}

	@Override
	public int selectByUserId(UserPermission userpermission) {
		// TODO Auto-generated method stub
		int flag=0;
		List<UserPermission> list= userPermissionMapper.selectByUserId(userpermission);
		if(list.size()>0){
			flag=1;
		}
		return flag;
	}
	

}
