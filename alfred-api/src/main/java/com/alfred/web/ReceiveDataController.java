package com.alfred.web;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.UserConstant;
import com.alfred.http.ResultCode;
import com.alfred.model.ReceiveMsgLog;
import com.alfred.model.User;
import com.alfred.service.ReceiveMsgLogService;
import com.alfred.service.UserService;
import com.alfred.util.CacheMap;
import com.alfred.util.ConfigHelper;
import com.alfred.util.JsonUtil;
import com.alfred.util.Skip32Util;

@Scope("prototype")
@Controller
@RequestMapping(value = "/receive")
public class ReceiveDataController extends BaseController {

	private static Log log = LogFactory.getLog(ReceiveDataController.class);

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("receiveMsgLogService")
	private ReceiveMsgLogService receiveMsgLogService;
	
	
	
	@RequestMapping(value = "/dataMsg", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> sendOrderMsg(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			
			String userKey = jsonObject.getString("userKey");
			String restaurantKey = jsonObject.getString("restaurantKey");
			
			JSONObject jsondata = jsonObject.getJSONObject("syncMsg");
			
			int revenueId = jsondata.getInt("revenueId");
			String id = jsondata.getString("id");
			int msgType = jsondata.getInt("msgType");
			String data = jsondata.getString("data");
			long createTime = jsondata.getLong("createTime");
			
			if (StringUtils.isBlank(userKey)) {
				resultMap.put(ResultCode.resultKey, ResultCode.USER_KEY_ERROR);
			}
			
			Integer userId = (Integer) CacheMap.getCacheMapInstance().get(userKey);
			//验证用户类型是否为大堂经理
//			User user = userService.selectByPrimaryKey(userId);
//			
//			if (user == null || UserConstant.USER_TYPE_LOBBY_MANAGER != user.getType()) {
//				resultMap.put(ResultCode.resultKey, ResultCode.USER_NO_PERMISS);
//			}
			
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
			
			ReceiveMsgLog receiveMsgLog = new ReceiveMsgLog();
			receiveMsgLog.setId(id);
			receiveMsgLog.setMsgType(msgType);
			receiveMsgLog.setDataJson(data);
			receiveMsgLog.setCreateTime(new Date(createTime));
			receiveMsgLog.setStatus(CommonStatusConstant.NO_PROCESS);
			receiveMsgLog.setRestaurantId(restaurantId);
			receiveMsgLog.setRevenueId(revenueId);
			receiveMsgLog.setUserId(userId);
			int row = receiveMsgLogService.insert(receiveMsgLog);
			if (row > 0) {
				resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			} else {
				resultMap.put(ResultCode.resultKey, ResultCode.INSERT_ERROR);
			}
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}

	
}
