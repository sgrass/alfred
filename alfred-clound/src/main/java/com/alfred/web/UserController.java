package com.alfred.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.UserConstant;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.model.UserRestaurant;
import com.alfred.service.RestaurantService;
import com.alfred.service.UserService;
import com.alfred.vo.UserRestaurantVO;


@Scope("prototype")
@Controller("UserController")
@RequestMapping(value = "/user")
public class UserController {
	
	private static Log log = LogFactory.getLog(UserController.class);
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	
	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restaurantService;
	
	private Map<String, Object> result = new HashMap<String, Object>();



/*********************************************************************************************************/
	//经理级别的用户模块
	/**
	 * 经理  列表
	 * 
	 * @return
	 */
	@RequestMapping("/managers")
	public String forwardMmanager(Model model) {
		try {
			
			UserRestaurantVO userPer = new UserRestaurantVO();
			// 设置当前用户
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			userPer.setCompanyId(currentUsers.getCompanyId());
			userPer.setType(UserConstant.USER_TYPE_CLOUD);
			userPer.setStatus(UserConstant.USER_STATUS_DELETE);
			Restaurant res = new Restaurant();
			res.setCompanyId(currentUsers.getCompanyId());
			res.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
			List<Restaurant> resList = null;
			List<UserRestaurantVO> userList = null;
			// 查询当前公司的所有manager
			userList = userService.selectUserRestaurantRes(userPer);
			resList = restaurantService.selectByStatus(res);
			model.addAttribute("resList", resList);
			model.addAttribute("userList", userList);
			return "forward:/pages/hqmanager/hq_manager.jsp";

		} catch (Exception e) {
			log.error(this, e);
			return null;
		}

		
	}


	/**
	 * 删除经理
	 * @param userId
	 * @param userRestaurantId
	 * @return
	 */
	@RequestMapping("/delManager")
	@ResponseBody
	public Map<String, Object> delManager(@RequestParam int userId) {
		
		try {
			int flag = userService.delManager(userId);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}

	}
	
	


	/**
	 * 新增经理
	 * @param user
	 * @param userRestaurant
	 * @return
	 */
	@RequestMapping("/addManager")
	@ResponseBody
	public Map<String, Object> addManager(HttpServletRequest request,@ModelAttribute User user,@ModelAttribute UserRestaurant userRestaurant,@RequestParam String restaurantIds) {
		try {

			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			user.setCompanyId(currentUsers.getCompanyId());
			String restaurantId = request.getParameter("restaurantIds");
			// 设置用户级别
			user.setType(UserConstant.USER_TYPE_CLOUD);
			// 设置用户状态
			user.setStatus(UserConstant.USER_STATUS_NORMAL);
			// 创建时间
			user.setCreateTime(new Date());
			// 更新时间
			user.setUpdateTime(new Date());
			
			int flag = userService.addManager(user, userRestaurant,restaurantId);
			
			result.put("flag", flag);
			
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}
	
	}
	

	/**
	 * 修改经理
	 * @param user
	 * @param userRestaurant
	 * @param userRestaurantId
	 * @return
	 */
	@RequestMapping("/updateManager")
	@ResponseBody
	public Map<String, Object> updateManager(HttpServletRequest request,@ModelAttribute User user,@ModelAttribute UserRestaurant userRestaurant,@RequestParam String restaurantIds,@RequestParam String uId) {
		try {
			
			String restaurantId= request.getParameter("restaurantIds");
			String uid=request.getParameter("uId");
			user.setId(Integer.parseInt(uid));
			userRestaurant.setUserId(user.getId());
			user.setUpdateTime(new Date());
		
			int flag = userService.updateManager(user, userRestaurant,restaurantId);
			
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}
	
	}
	
	
/******************************************************************************************************/
	/**
	 * 用户名验证是否重复
	 * 
	 * @param account_name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkManagerName")
	@ResponseBody
	public Map<String, Object> checkManagerName(
			@RequestParam String accountName, @RequestParam int id)
			throws Exception {

		try {
			boolean flag = true;
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			User u = new User();
			if (id != 0) {
				u.setId(id);
			}
			u.setCompanyId(currentUsers.getCompanyId());
			u.setStatus(UserConstant.USER_STATUS_DELETE);
			u.setAccountName(accountName);

			User users = userService.selectByManager(u);
			if (users != null) {
				flag = false;
			}
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	
	
	
	

}
