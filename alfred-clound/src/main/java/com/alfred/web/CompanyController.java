package com.alfred.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.UserConstant;
import com.alfred.model.Company;
import com.alfred.model.User;
import com.alfred.service.CompanyService;
import com.alfred.service.UserService;

@Scope("prototype")
@Controller("companyController")
@RequestMapping(value = "/company")
public class CompanyController {

	private static Log log = LogFactory.getLog(CompanyController.class);
	@Autowired
	@Qualifier("companyService")
	private CompanyService companyService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	private Map<String, Object> result = new HashMap<String, Object>();

	/**
	 * 公司注册页面
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/forwardRegistration")
	public String forwardRegistration() {
		return "forward:/pages/login/registration.jsp";
	}

	/**
	 * 用户录入 公司录入
	 * 
	 * @param company
	 * @param user
	 * @param confirm_password
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/registration")
	public String forwardLogin(@ModelAttribute Company company, @ModelAttribute User user,
			@RequestParam String confirm_password) {
		try {
			String forwardPath = "";
			// 密碼相同就进行数据持久化
			if (user.getPassword().equals(confirm_password)) {

				// 设置公司级别
				company.setLevel(UserConstant.COMPANY_FREE);
				// 设置用户级别
				user.setType(UserConstant.USER_TYPE_CORP_MANAGER);
				// 设置用户状态
				user.setStatus(UserConstant.USER_STATUS_NORMAL);

				companyService.insertCompanyAndUser(company, user);

				// 条状到登陆页面进行登陆
				forwardPath = "forward:/pages/login/login.jsp";

			} else {
				// 继续留在注册页面
				forwardPath = "forward:/pages/login/registration.jsp?password";

			}
			return forwardPath;

		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 用户名验证是否重复
	 * 
	 * @param account_name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkAccountName")
	@ResponseBody
	public Map<String, Object> checkAccountName(@RequestParam String account_name) {
		boolean flag = true;
		try {
			User user = userService.selectByAccountName(account_name);
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

	/**
	 * 获取当前登陆用户的所有店铺
	 * 
	 * @return
	 */
	@RequestMapping("/getCompanyJSON")
	@ResponseBody
	public Map<String, Object> getCompanyJSON() {
		// 查询当前用户的所有店铺
		Company com = new Company();
		// 设置当前用户的ID session
		com.setUserId(8);

		List<Company> companyList;
		try {
			// 查询当前用户的所有店铺
			companyList = companyService.selectByParam(com);
			result.put("companyJson", companyList);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 新增店铺
	 * 
	 * @param company
	 *          店铺信息
	 * @return
	 */
	@RequestMapping("/addCompany")
	@ResponseBody
	public Map<String, Object> addCompany(@ModelAttribute Company company) {

		try {
			int flag = companyService.insert(company);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 修改店铺
	 * 
	 * @param company
	 *          店铺信息
	 * @return
	 */
	@RequestMapping("/updateCompany")
	@ResponseBody
	public Map<String, Object> updateCompany(@ModelAttribute Company company) {

		try {
			int flag = companyService.updateById(company);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 删除店铺
	 * 
	 * @param companyId
	 *          店铺ID
	 * @return
	 */
	@RequestMapping("/deleteCompany")
	@ResponseBody
	public Map<String, Object> deleteCompany(@RequestParam int companyId) {

		try {
			int flag = companyService.deleteByPrimaryKey(companyId);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

}
