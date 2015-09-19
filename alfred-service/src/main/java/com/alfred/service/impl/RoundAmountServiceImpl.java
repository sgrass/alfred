package com.alfred.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.RoundAmountMapper;
import com.alfred.model.RoundAmount;
import com.alfred.service.RoundAmountService;
@Transactional
@Service("roundAmountService")
public class RoundAmountServiceImpl implements RoundAmountService {
	private static Log log = LogFactory.getLog(RoundAmountService.class);
	@Autowired
	@Qualifier("roundAmountMapper")
	private RoundAmountMapper roundAmountMapper = null;
	@Override
	public RoundAmount queryRoundValue(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
		RoundAmount roundAmount=new RoundAmount();
		List<RoundAmount> list=roundAmountMapper.queryRoundValue(map);
		if(list.size()>0){
			roundAmount=list.get(0);
		}
		
		return roundAmount;
	}

}
