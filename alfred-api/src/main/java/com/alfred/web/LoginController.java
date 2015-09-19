package com.alfred.web;

import java.util.Map;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.http.ResultCode;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.RestaurantService;
import com.alfred.service.UserRestaurantService;
import com.alfred.service.UserService;
import com.alfred.util.CacheMap;
import com.alfred.util.ConfigHelper;
import com.alfred.util.JsonUtil;
import com.alfred.util.Skip32Util;

@Scope("prototype")
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

	private static Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restaurantService;
	
	@Autowired
	@Qualifier("userRestaurantService")
	private UserRestaurantService userRestaurantService;
	
	
	@RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> loginVerify(HttpServletRequest request) {
		try {
			String jsonStr = JsonUtil.getJsonStrFromRequest(request);
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			Integer empId = jsonObject.getInt("empId");
			String password = jsonObject.getString("password");
			String restaurantKey = jsonObject.getString("restaurantKey");
			
			if (StringUtils.isBlank(restaurantKey)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
			
			Restaurant restaurant = restaurantService.selectByPrimaryKey(restaurantId);
			if (restaurant == null) {
				resultMap.put(ResultCode.resultKey, ResultCode.RESTAURANT_EMPTY);
				return resultMap;	
			}
			
			User user = userService.selectByEmpId(restaurantId, restaurant.getCompanyId(), empId);
			
			if (user == null) {
				resultMap.put(ResultCode.resultKey, ResultCode.USER_EMPTY);
				return resultMap;
			}
			if (!password.equals(user.getPassword())) {
				resultMap.put(ResultCode.resultKey, ResultCode.USER_PASSWORD_ERROR);
				return resultMap;
			}
			
			if (CacheMap.getCacheMapInstance().containsValue(user.getId())) {
				resultMap.put(ResultCode.resultKey, ResultCode.USER_LOGIN_EXIST);
				return resultMap;
			} 
			
			String userKey = UUID.randomUUID().toString();
			CacheMap.getCacheMapInstance().put(userKey, user.getId());
			
			resultMap.put("userKey", userKey);
			resultMap.put("restaurantKey", restaurantKey);
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}

	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	@ResponseBody
	public int logout() {
		CacheMap.getCacheMapInstance().clearMap();
		return CacheMap.getCacheMapInstance().getCacheMapSize();
	}
}
