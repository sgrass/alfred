package com.alfred.dao;

import com.alfred.model.HappyHoursDetails;
import java.util.List;

public interface HappyHoursDetailsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HappyHoursDetails happyHoursDetails);

    List<HappyHoursDetails> selectByParam(HappyHoursDetails happyHoursDetails);

    HappyHoursDetails selectByPrimaryKey(Integer id);

    int updateById(HappyHoursDetails happyHoursDetails);
   
    int updateByHappyHoursId(HappyHoursDetails happyHoursDetails);
    
    
}