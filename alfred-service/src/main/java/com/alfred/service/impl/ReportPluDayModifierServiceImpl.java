package com.alfred.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.alfred.dao.ReportPluDayModifierMapper;
import com.alfred.model.ReportPluDayModifier;
import com.alfred.service.ReportPluDayModifierService;
@Transactional
@Service("reportPluDayModifierService")
public class ReportPluDayModifierServiceImpl implements ReportPluDayModifierService {
	   
	private static Log log = LogFactory.getLog(ReportPluDayModifierServiceImpl.class);
		@Autowired
		@Qualifier("reportPluDayModifierMapper")
		private ReportPluDayModifierMapper reportPluDayModifierMapper = null;
	@Override
	public List<ReportPluDayModifier> selectByParam(
			ReportPluDayModifier reportPluDayModifier) {
		// TODO Auto-generated method stub
		return reportPluDayModifierMapper.selectByParam(reportPluDayModifier);
	}

}
