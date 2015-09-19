package com.alfred.interceptor;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.UserConstant;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.RestaurantService;
import com.alfred.service.UserService;


public class SessionInterceptor implements HandlerInterceptor {

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restaurantService;
	
	private static Log log = LogFactory.getLog(SessionInterceptor.class);

	private final String[] excludes = {"isLogin", "loginVerify","logout" };
	
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
	        throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
	        throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		if (!subject.isAuthenticated() && subject.isRemembered()) {
			try {
				User sessionUser = (User) subject.getSession().getAttribute("currentUser");
				Restaurant sessionRest = (Restaurant) request.getSession().getAttribute("restaurant");
				
				if (sessionUser == null) {
					String accountName = (String) subject.getPrincipal();
					sessionUser = userService.selectByAccountName(accountName);
					subject.getSession().setAttribute("currentUser", sessionUser);
				}
				
				if (sessionRest == null) {
					if (sessionUser.getType() >= UserConstant.USER_TYPE_CORP_MANAGER) {
						Restaurant restaurant = new Restaurant();
						restaurant.setCompanyId(sessionUser.getCompanyId());
						restaurant.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
						List<Restaurant> list = restaurantService.selectByStatus(restaurant);
						request.getSession().setAttribute("restaurantListAll", list);
						if (list != null && list.size() > 0) {
							restaurant =list.get(0);
							request.getSession().setAttribute("restaurant", restaurant);
						}
					} else if (sessionUser.getType() == UserConstant.USER_TYPE_CLOUD) {
						//后台用户查询当前用户下的餐厅 并默认一个餐厅
						HashMap<String, Object> map = new HashMap<String, Object>();
						map.put("userId", sessionUser.getId());
						List<Restaurant> resList = restaurantService.selectRestList(map);
						//session记录第一个餐厅的值
						if (resList != null && resList.size() > 0) {
							Restaurant restaurant =resList.get(0);
							request.getSession().setAttribute("restaurant", restaurant);
						}
						request.getSession().setAttribute("restaurantList", resList);
					}
				}
			} catch (Exception e) {
				log.error(this,e);
				return false;
			}
		}
		return true;
	}

	private boolean exist(String rq) {
		for (String s : excludes) {
			if (s.equals(rq)) {
				return true;
			}
		}
		return false;
	}
}
