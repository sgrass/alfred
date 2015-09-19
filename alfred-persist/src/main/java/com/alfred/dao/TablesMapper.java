package com.alfred.dao;

import com.alfred.model.Tables;
import java.util.List;

public interface TablesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tables tables);

    List<Tables> selectByParam(Tables tables);

    Tables selectByPrimaryKey(Integer id);

    int updateById(Tables tables);
    
    List<Tables> selectByTable(Tables tables);
    
}