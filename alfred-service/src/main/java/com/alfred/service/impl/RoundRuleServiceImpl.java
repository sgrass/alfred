package com.alfred.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.RoundRuleMapper;
import com.alfred.model.RoundRule;
import com.alfred.service.RoundRuleService;

@Transactional
@Service("roundRuleService")
public class RoundRuleServiceImpl implements RoundRuleService {

	@Autowired
	@Qualifier("roundRuleMapper")
	private RoundRuleMapper roundRuleMapper = null;
	
	@Override
	public int deleteByPrimaryKey(Integer id) throws Exception {
		return 0;
	}

	@Override
	public int insert(RoundRule roundRule) throws Exception {
		return 0;
	}

	@Override
	public List<RoundRule> selectByParam(RoundRule roundRule) throws Exception {
		return roundRuleMapper.selectByParam(roundRule);
	}

	@Override
	public RoundRule selectByCountry(String country) throws Exception {
		RoundRule param = new RoundRule();
		param.setCountry(country);
		param.setStatus(1);
		List<RoundRule> list = this.selectByParam(param);
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public RoundRule selectByPrimaryKey(Integer id) throws Exception {
		return roundRuleMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(RoundRule roundRule) throws Exception {
		return 0;
	}

}
