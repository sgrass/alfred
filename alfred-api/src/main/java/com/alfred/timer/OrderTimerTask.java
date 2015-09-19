package com.alfred.timer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alfred.service.ReceiveMsgLogService;

@Component
public class OrderTimerTask {
	
	private static Log log = LogFactory.getLog(OrderTimerTask.class);
	
	@Autowired
	@Qualifier("receiveMsgLogService")
	private ReceiveMsgLogService receiveMsgLogService;
	
	@Scheduled(cron = "${timertask.cron.order}")
	public void parseOrder() {
		try {
			log.info("-----------------timertask parseOrder start...");
			
			receiveMsgLogService.parseOrder();
			
			log.info("-----------------timertask parseOrder end...");
		} catch (Exception e) {
			log.error(this,e);
		}
	}
	
}
