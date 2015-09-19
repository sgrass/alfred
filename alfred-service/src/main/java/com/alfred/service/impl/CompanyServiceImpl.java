package com.alfred.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.CompanyMapper;
import com.alfred.dao.PermissionMapper;
import com.alfred.dao.UserMapper;
import com.alfred.dao.UserPermissionMapper;
import com.alfred.model.Company;
import com.alfred.model.Permission;
import com.alfred.model.User;
import com.alfred.model.UserPermission;
import com.alfred.service.CompanyService;


@Transactional
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	
	private static Log log = LogFactory.getLog(CompanyServiceImpl.class);
	@Autowired
	@Qualifier("companyMapper")
	private CompanyMapper companyMapper = null;

	@Autowired
	@Qualifier("userMapper")
	private UserMapper userMapper = null;
	
	@Autowired
	@Qualifier("permissionMapper")
	private PermissionMapper permissionMapper = null;
	
	@Autowired
	@Qualifier("userPermissionMapper")
	private UserPermissionMapper userPermissionMapper = null;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return companyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.insert(company);
	}

	@Override
	public List<Company> selectByParam(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.selectByParam(company);
	}

	@Override
	public Company selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return companyMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(Company company) {
		// TODO Auto-generated method stub
		return companyMapper.updateById(company);
	}

	
	/**
	 * 注册用户和公司
	 */
	@Override
	public int insertCompanyAndUser(Company company, User user) {
		int resultRow = 0;
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		resultRow+=userMapper.insert(user);
		
		// 设置创建用户的Id
	
		company.setUserId(user.getId());
		company.setEmail(user.getAccountName());
		company.setCreateTime(new Date());
		company.setUpdateTime(new Date());
		resultRow+=companyMapper.insert(company);
		
		//跟新用户表中的状态
		user.setCompanyId(company.getId());
		userMapper.updateById(user);
		
		Permission param = new Permission();
		param.setPermissId(1);
		List<Permission> permissList = permissionMapper.selectByParam(param);
		if (permissList != null && permissList.size() > 0) {
			Permission permiss = permissList.get(0);
			
			UserPermission userPermiss = new UserPermission();
			userPermiss.setUserId(user.getId());
			userPermiss.setPermissionId(permiss.getPermissId());
			userPermiss.setRule(permiss.getPermissRule());
			resultRow+=userPermissionMapper.insert(userPermiss);
		}
		
		return resultRow;
	}
	
	
	
	

}
