package com.alfred.service;

import java.util.List;

import com.alfred.model.ReceiveMsgLog;

public interface ReceiveMsgLogService {
	
  int deleteByPrimaryKey(Integer id) throws Exception;

  int insert(ReceiveMsgLog receiveMsgLog) throws Exception;

  List<ReceiveMsgLog> selectByParam(ReceiveMsgLog receiveMsgLog) throws Exception;

  ReceiveMsgLog selectByPrimaryKey(Integer id) throws Exception;

  int updateById(ReceiveMsgLog receiveMsgLog) throws Exception;
  
  void parseOrder() throws Exception;
  
  void parseReport() throws Exception;

	void test() throws Exception;
  
}
