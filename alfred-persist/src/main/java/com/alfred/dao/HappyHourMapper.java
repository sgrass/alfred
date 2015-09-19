package com.alfred.dao;

import com.alfred.model.HappyHour;
import com.alfred.vo.HappyHourVO;

import java.util.List;

public interface HappyHourMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HappyHour happyHour);

    List<HappyHour> selectByParam(HappyHour happyHour);

    HappyHour selectByPrimaryKey(Integer id);

    int updateById(HappyHour happyHour);
    
    List<HappyHourVO> selectHappyHourAll(Integer restaurantId);
    
}