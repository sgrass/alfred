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

import com.alfred.constant.UserConstant;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.UserPermissionService;
import com.alfred.service.UserService;
import com.alfred.util.PushMsgUtil;

@Scope("prototype")
@Controller("EmpUserController")
@RequestMapping(value = "/empuser")
public class EmpUserController {

	private static Log log = LogFactory.getLog(EmpUserController.class);
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("userPermissionService")
	private UserPermissionService userPermissionService;

	private Map<String, Object> result = new HashMap<String, Object>();

	/**
	 * 员工列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/empUser")
	public String forwardEmpUser(HttpServletRequest request, Model model, @RequestParam(required = false) Integer type) {

		try {
			// 设置当前用户
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");

			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			if (type == null || type == 0) {
				type = UserConstant.USER_TYPE_CLOUD;
			}
			User user = new User();
			user.setType(type);
			user.setCompanyId(currentUsers.getCompanyId());
			// user.setType(UserConstant.USER_TYPE_CLOUD);
			user.setStatus(UserConstant.USER_STATUS_NORMAL);
			// 为了不创建信得模
			// 设置餐厅
			user.setEmpId(restaurantId);

			List<User> userList = null;

			// 查询当前公司的所有manager
			userList = userService.selectByEmp(user);

			model.addAttribute("userList", userList);
			model.addAttribute("type", type);
			return "forward:/pages/user/emp_user.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 新增员工
	 * 
	 * @param user
	 * @return
	 */

	@RequestMapping("/addEmpUser")
	@ResponseBody
	public Map<String, Object> addEmpUser(HttpServletRequest request, @ModelAttribute User user) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");

			user.setCompanyId(currentUsers.getCompanyId());
			// 设置用户状态
			user.setStatus(UserConstant.USER_STATUS_NORMAL);
			// 创建时间
			user.setCreateTime(new Date());
			// 更新时间
			user.setUpdateTime(new Date());

			int flag = userService.insertEmp(user, restaurant.getId());
			// 创建餐厅员工表
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.USER);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 修改员工
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/updateEmpUser")
	@ResponseBody
	public Map<String, Object> updateEmpUser(HttpServletRequest request,@ModelAttribute User user) {

		try {
			user.setUpdateTime(new Date());
			int flag = userService.updateById(user);
			result.put("flag", flag);
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			log.info(PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.USER));
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 删除员工
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/delEmpUser")
	@ResponseBody
	public Map<String, Object> delEmpUser(HttpServletRequest request,@RequestParam int id) {
		try {
			int flag = userService.deleteByPrimaryKey(id);
			result.put("flag", flag);
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.USER);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 验证员工Id是否存在
	 * 
	 * @param empId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkEmpId")
	@ResponseBody
	public Map<String, Object> checkEmpId(HttpServletRequest request, @RequestParam int empId) {
		try {
			boolean flag = true;
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			User user = userService.selectByEmpId(restaurant.getId(), currentUsers.getCompanyId(), empId);
			if (user != null) {
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
