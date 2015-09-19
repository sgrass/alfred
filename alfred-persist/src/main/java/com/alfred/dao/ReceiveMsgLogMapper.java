package com.alfred.dao;

import com.alfred.model.ReceiveMsgLog;
import java.util.List;

public interface ReceiveMsgLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ReceiveMsgLog receiveMsgLog);

    List<ReceiveMsgLog> selectByParam(ReceiveMsgLog receiveMsgLog);

    ReceiveMsgLog selectByPrimaryKey(Integer id);

    int updateById(ReceiveMsgLog receiveMsgLog);
}