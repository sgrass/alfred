package com.alfred.service;

import java.util.HashMap;
import java.util.List;

import com.alfred.model.ReportDayTax;

public interface ReportDayTaxService {
	
	  List<ReportDayTax> selectByParam(ReportDayTax reportDayTax) throws Exception;
	  
	  List<ReportDayTax> queryTaxGroup(HashMap<String, Object> map) throws Exception;


}
