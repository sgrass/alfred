package com.alfred.web;

import java.util.List;
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
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.model.UserRestaurant;
import com.alfred.service.RestaurantService;
import com.alfred.service.UserRestaurantService;
import com.alfred.service.UserService;
import com.alfred.util.ConfigHelper;
import com.alfred.util.Skip32Util;


@Scope("prototype")
@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController{
	
	private static Log log = LogFactory.getLog(UserController.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("userRestaurantService")
	private UserRestaurantService userRestaurantService;
	
	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restaurantService;
	
	/**
	 * 根据餐厅id查询餐厅下前台用户信息
	 * 
	 * @return
	 */
	@RequestMapping(value="/getUser", method=RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getUser(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");
			
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
			Restaurant restaurant = restaurantService.selectByPrimaryKey(restaurantId);
//			Integer restaurantId=19;
			
			User user = new User();
			user.setCompanyId(restaurant.getCompanyId());
			user.setType(UserConstant.USER_TYPE_CLOUD);
			user.setEmpId(restaurantId);
			user.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			
			List<User> userList = userService.selectByEmp(user);
			
			List<UserRestaurant> userRestaurantList = userRestaurantService.selectByRestaurantId(restaurantId, restaurant.getCompanyId(), UserConstant.USER_TYPE_CLOUD);
			
			resultMap.put("userList", userList);
			resultMap.put("userRestaurantList", userRestaurantList);
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}


	
	
	
	

}
