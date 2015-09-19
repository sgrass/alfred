package com.alfred.web;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.UserConstant;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.RestaurantService;
import com.alfred.service.UserPermissionService;
import com.alfred.service.UserRestaurantService;
import com.alfred.service.UserService;

@Scope("prototype")
@Controller("LoginController")
@RequestMapping(value = "/login")
public class LoginController {
      
	private static Log log = LogFactory.getLog(LoginController.class);

	@Autowired
	@Qualifier("userPermissionService")
	private UserPermissionService userPermissionService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restaurantService;
	
	@Autowired
	@Qualifier("userRestaurantService")
	private UserRestaurantService userRestaurantService;
	
	private static final String LOGIN_PAGE = "/pages/login/login.jsp";
	
	@RequestMapping("/isLogin")
	public String isLogin(HttpServletRequest request) {
		try {
			Subject subject = SecurityUtils.getSubject();
			
			log.info("remember is status:"+subject.isRemembered());
			
			User user = (User) subject.getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			
			if (user != null && restaurant != null) {
				return getUrlByUserType(user.getType(), restaurant.getId());
			}
			return LOGIN_PAGE;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	@RequestMapping(value = "/loginVerify", method = RequestMethod.POST)
	public String loginVerify(HttpServletRequest request,
			@RequestParam String accountName, @RequestParam String password, 
			@RequestParam(required = false) String remember, Model model) {
		String resultURL = InternalResourceViewResolver.FORWARD_URL_PREFIX + "/pages/login/login.jsp";
		Subject currentUser = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken(accountName, password);
		
		if (StringUtils.isBlank(remember)) {
			token.setRememberMe(false);
		} else {
			token.setRememberMe(true);
		}
//		log.info("token-->" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
		int restaurantId=0;
		try {
			currentUser.login(token);
			log.info("登录用户：" + accountName);
			User user = (User) currentUser.getSession().getAttribute("currentUser");
			//企业级的用户查询所有餐厅
			if (UserConstant.USER_TYPE_CORP_MANAGER == user.getType()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setCompanyId(user.getCompanyId());
				restaurant.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
				List<Restaurant> list = restaurantService.selectByStatus(restaurant);
				request.getSession().setAttribute("restaurantListAll", list);
			 //后台用户查询当前用户下的餐厅 并默认一个餐厅
			} else if (user.getType() == UserConstant.USER_TYPE_CLOUD) {
	
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("userId", user.getId());
				List<Restaurant> resList = restaurantService.selectRestList(map);
				//session记录第一个餐厅的值
				if (resList != null && resList.size() > 0) {
					Restaurant restaurant =resList.get(0);
					request.getSession().setAttribute("restaurant", restaurant);
					restaurantId = restaurant.getId();
				}
				
				request.getSession().setAttribute("restaurantList", resList);
			}
			
			resultURL = getUrlByUserType(user.getType(),restaurantId);
		} catch (Exception e) {
			log.info("[" + accountName + "]用户名或密码不正确");
			log.error(this,e);
			model.addAttribute("errMsg", "用户名或密码不正确");
			return resultURL;
		}
		// 验证是否登录成功
		if (currentUser.isAuthenticated()) {
			log.info("用户[" + accountName + "]登录成功");
		} else {
			token.clear();
		}
		return resultURL;
	}

	/**
	 * 用户登出
	 */
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		SecurityUtils.getSubject().logout();
		return InternalResourceViewResolver.REDIRECT_URL_PREFIX + "/pages/login/login.jsp";
	}
	
	private String getUrlByUserType(Integer type, Integer restaurantId) {
		String url = "redirect:/dashboard/index?resId="+restaurantId;
		
		if (UserConstant.USER_TYPE_CORP_MANAGER == type) {
			url = "redirect:/restaurant/hqrestaurant";
		}
		return url;
		
	}
}
