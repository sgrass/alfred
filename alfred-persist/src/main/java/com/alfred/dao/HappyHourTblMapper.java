package com.alfred.dao;

import com.alfred.model.HappyHourTbl;
import com.alfred.vo.HappyHoursVO;

import java.util.List;

public interface HappyHourTblMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HappyHourTbl happyHourTbl);

    List<HappyHourTbl> selectByParam(HappyHourTbl happyHourTbl);

    HappyHourTbl selectByPrimaryKey(Integer id);

    int updateById(HappyHourTbl happyHourTbl);
    
    
    List<HappyHoursVO> selectHappyHours(HappyHoursVO happyHoursVO);
}