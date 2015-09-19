package com.alfred.service.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.dao.ReportDayTaxMapper;
import com.alfred.model.ReportDayTax;
import com.alfred.service.ReportDayTaxService;
@Transactional
@Service("reportDayTaxService")
public class ReportDayTaxServiceImpl implements ReportDayTaxService {
	  
	private static Log log = LogFactory.getLog(ReportDayTaxServiceImpl.class);
		@Autowired
		@Qualifier("reportDayTaxMapper")
		private ReportDayTaxMapper reportDayTaxMapper = null;
		@Override
		public List<ReportDayTax> selectByParam(ReportDayTax reportDayTax) {
			// TODO Auto-generated method stub
			return reportDayTaxMapper.selectByParam(reportDayTax);
		}
		@Override
		public List<ReportDayTax> queryTaxGroup(HashMap<String, Object> map) {
			// TODO Auto-generated method stub
			return reportDayTaxMapper.queryTaxGroup(map);
		}


}
