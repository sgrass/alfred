package com.alfred.timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alfred.service.ReceiveMsgLogService;

//@Component
public class ReportHourlyTimerTask {
	
	private static Log log = LogFactory.getLog(ReportHourlyTimerTask.class);
	
	@Autowired
	@Qualifier("receiveMsgLogService")
	private ReceiveMsgLogService receiveMsgLogService;
	
	//@Scheduled(cron = "${timertask.cron.report}")
	public void parseReport() {
		try {
			log.info("-----------------timertask parseHourlyReport start...");
			
			receiveMsgLogService.test();
			
			log.info("-----------------timertask parseHourlyReport end...");
		} catch (Exception e) {
			log.error(this,e);
		}
	}
	
}
