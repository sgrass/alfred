package com.alfred.web;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.model.User;
import com.alfred.service.UserService;

@Scope("prototype")
@Controller("UserInfoController")
@RequestMapping(value = "/userInfo")
public class UserInfoController {
	private static Log log = LogFactory.getLog(UserController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	//修改当前登陆用户的密码
	
	@RequestMapping("/userPass")
	public String forwardUserPass(HttpServletRequest request, Model model) {
		return "forward:/pages/user/password.jsp";
	}
	
	
	@RequestMapping("/updatePassword")
	@ResponseBody
	public Map<String,Object> updatePassword(HttpServletRequest request, Model model,
			@RequestParam(required = false) String oldPassword,
			@RequestParam(required = false) String firstPassword,@RequestParam(required = false) String comfirmPassword) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			User user=userService.selectByPrimaryKey(currentUsers.getId());
			if(oldPassword.equals(user.getPassword())){
				if(firstPassword.equals(comfirmPassword)){
					User u=new User();
					u.setId(currentUsers.getId());
					u.setPassword(comfirmPassword);
					int flag=userService.updateById(u);
					if(flag==0){
						result.put("status", "update sucess");	
	
					}else{
						result.put("status", "update failure");	
					}
				}else{
					result.put("status", "password not same");	
				}
			}else{
				result.put("status", "old password failure");	
			}
			return result;
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}

	
	
}
