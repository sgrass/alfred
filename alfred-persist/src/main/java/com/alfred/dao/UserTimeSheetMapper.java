package com.alfred.dao;

import com.alfred.model.UserTimeSheet;

import java.util.HashMap;
import java.util.List;

public interface UserTimeSheetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserTimeSheet userTimeSheet);
    
    List<UserTimeSheet> selectByParam(UserTimeSheet userTimeSheet);

    UserTimeSheet selectByPrimaryKey(Integer id);

    int updateById(UserTimeSheet userTimeSheet);
    
    List<UserTimeSheet> queryUserTimeList(HashMap<String, Object> map);
    int getUserTimeCount(HashMap<String, Object> map);
}