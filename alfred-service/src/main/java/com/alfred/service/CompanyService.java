package com.alfred.service;
import java.util.List;
import com.alfred.model.Company;
import com.alfred.model.User;
public interface CompanyService {
	
	  int deleteByPrimaryKey(Integer id) throws Exception;

	  int insert(Company company) throws Exception;

	  int insertCompanyAndUser(Company company,User user) throws Exception;
	  
	  List<Company> selectByParam(Company company) throws Exception;

	  Company selectByPrimaryKey(Integer id) throws Exception;

	  int updateById(Company company) throws Exception;


}
