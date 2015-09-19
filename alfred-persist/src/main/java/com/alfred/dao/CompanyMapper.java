package com.alfred.dao;

import com.alfred.model.Company;
import java.util.List;

public interface CompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Company company);

    List<Company> selectByParam(Company company);

    Company selectByPrimaryKey(Integer id);

    int updateById(Company company);
}