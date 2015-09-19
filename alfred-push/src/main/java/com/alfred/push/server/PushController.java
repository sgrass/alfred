package com.alfred.push.server;

import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Scope("prototype")
@Controller
@RequestMapping(value = "/push")
public class PushController extends BaseController{

	private static Log log = LogFactory.getLog(PushController.class);

	private final int port = 8085;
	
	@RequestMapping(value = "/1", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> test() {
		resultMap.put("getUserMap", PushMap.getUserMap());
		resultMap.put("getRestaurantMap", PushMap.getRestaurantMap());
		resultMap.put("getRevenueMap", PushMap.getRevenueMap());
		
		return resultMap;
	}
	
	@RequestMapping(value = "/sendRevenue")
	@ResponseBody
	public Map<String, Object> sendRevenue(@RequestParam Integer revenueId,@RequestParam String msgContent) {
		String clientId = PushMap.getRevenueMap().get(revenueId);
		if (StringUtils.isBlank(clientId)) {
			resultMap.put("resultCode", -1);
			return resultMap;
		}
		User u = PushMap.getUserMap().get(clientId);
		if (u == null) {
			resultMap.put("resultCode", -1);
			return resultMap;
		}
		
		Message msg = new Message();
		msg.setMsg(msgContent);
		msg.setTo(u.getAddress());
		msg.setType(1);
		msg.setFrom("server发");
		String msgStr = JSONObject.fromObject(msg).toString();
		
		u.getChannel().writeAndFlush(new TextWebSocketFrame(msgStr));
		resultMap.put("resultCode", 0);
		return resultMap;
	}
	
	@RequestMapping(value = "/sendRestaurant")
	@ResponseBody
	public Map<String, Object> sendRestaurant(@RequestParam Integer restId,@RequestParam String msgContent) {
		Map<Integer, String> revenueIdMap = PushMap.getRestaurantMap().get(restId);
		for (Map.Entry<Integer, String> entry: revenueIdMap.entrySet()) {
			Integer revenueId = entry.getKey();
			
			if (revenueId == null || revenueId <=0) {
				resultMap.put("resultCode", -1);
				return resultMap;
			}
			String clientId = PushMap.getRevenueMap().get(revenueId);
			
			if (StringUtils.isBlank(clientId)) {
				resultMap.put("resultCode", -1);
				return resultMap;
			}
			
			User u = PushMap.getUserMap().get(clientId);
			if (u != null) {
				Message msg = new Message();
				msg.setMsg(msgContent);
				msg.setTo(u.getAddress());
				msg.setType(1);
				msg.setFrom("server发送");
				String msgStr = JSONObject.fromObject(msg).toString();
				
				u.getChannel().writeAndFlush(new TextWebSocketFrame(msgStr));
			}
		}
		
		resultMap.put("resultCode", 0);
		return resultMap;
	}
}
