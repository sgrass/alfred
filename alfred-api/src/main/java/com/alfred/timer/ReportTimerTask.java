package com.alfred.timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alfred.service.ReceiveMsgLogService;

@Component
public class ReportTimerTask {
	
	private static Log log = LogFactory.getLog(ReportTimerTask.class);
	
	@Autowired
	@Qualifier("receiveMsgLogService")
	private ReceiveMsgLogService receiveMsgLogService;
	
	@Scheduled(cron = "${timertask.cron.report}")
	public void parseReport() {
		try {
			log.info("-----------------timertask parseReport start...");
			
			receiveMsgLogService.parseReport();
			
			log.info("-----------------timertask parseReport end...");
		} catch (Exception e) {
			log.error(this,e);
		}
	}
	
}
