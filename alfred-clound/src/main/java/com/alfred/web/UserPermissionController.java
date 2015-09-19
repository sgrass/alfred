package com.alfred.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import com.alfred.model.Permission;
import com.alfred.model.UserPermission;
import com.alfred.service.UserPermissionService;

@Scope("prototype")
@Controller("UserPermissionController")
@RequestMapping(value = "/userPermission")
public class UserPermissionController {

	private static Log log = LogFactory.getLog(UserPermissionController.class);
	@Autowired
	@Qualifier("userPermissionService")
	private UserPermissionService userPermissionService;

	private Map<String, Object> result = new HashMap<String, Object>();

	/**
	 * 给经理分配的权限列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/permission")
	public String forwardPermission(Model model,@RequestParam int userId,@RequestParam String userName) {
		
		try {

			Permission per = new Permission();
			UserPermission userper = new UserPermission();

			// 设置permission level
			per.setPermisLevel(CommonStatusConstant.IS_PERMISSION_LEVEL);
			// 设置permission model

			List<Permission> perList = null;
			List<UserPermission> userperList = null;
			perList = userPermissionService.selectByParamByCommon(per);
			// 查询权限权限表中是否存在
			userper.setUserId(userId);
			userperList = userPermissionService.selectByParam(userper);
			Map<String, List<Permission>> map = new HashMap<String, List<Permission>>();
			for (Permission p : perList) {
				for (UserPermission u : userperList) {
					if (p.getPermissId().equals(u.getPermissionId())) {
						p.setPermissDesc("checked");
						break;
					} else {
						p.setPermissDesc("");
					}
				}

				if (map.containsKey(p.getPermModel())) {
					List<Permission> mapList = map.get(p.getPermModel());
					mapList.add(p);
				} else {
					List<Permission> mapList = new ArrayList<Permission>();
					mapList.add(p);
					map.put(p.getPermModel(), mapList);
				}

			}

			model.addAttribute("userName", userName);
			model.addAttribute("userId", userId);
			model.addAttribute("permissionMap", map);
		    return "forward:/pages/hqmanager/hq_userpremission.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}

		
	}

	
	/**
	 * 新增权限
	 * @param userPermission
	 * @return
	 */
	@RequestMapping("/addUserPermission")
	@ResponseBody
	public Map<String, Object> adduserPermission(
			@ModelAttribute UserPermission userPermission) {

		try {
			int flag = userPermissionService.insert(userPermission);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
		
	}

	/**
	 * 修改权限
	 * @param content
	 * @param checkAll
	 * @param userId
	 * @return
	 */
	@RequestMapping("/updateUserPermission")
	@ResponseBody
	public Map<String, Object> updateUserPermission(@RequestParam String content,@RequestParam int checkAll,@RequestParam int userId) {

		try {
			int flag = userPermissionService.updateByIds(content,checkAll,userId);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}
	
	}

	/**
	 * 删除权限
	 * @param userPermissionId
	 * @return
	 */
	@RequestMapping("/deleteUserPermission")
	@ResponseBody
	public Map<String, Object> deleteCompany(@RequestParam int userPermissionId) {
		try {
			int flag = userPermissionService.deleteByPrimaryKey(userPermissionId);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}
		
	}
	

}
