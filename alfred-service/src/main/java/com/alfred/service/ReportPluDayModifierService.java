package com.alfred.service;

import java.util.List;
import com.alfred.model.ReportPluDayModifier;

public interface ReportPluDayModifierService {
	
	  List<ReportPluDayModifier> selectByParam(ReportPluDayModifier reportPluDayModifier) throws Exception;


}
