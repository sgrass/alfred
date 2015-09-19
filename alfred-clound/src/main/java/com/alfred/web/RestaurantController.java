package com.alfred.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.alfred.model.Company;
import com.alfred.model.Printer;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.CompanyService;
import com.alfred.service.PrinterService;
import com.alfred.service.RestaurantService;
import com.alfred.util.PushMsgUtil;
import com.alfred.vo.RestaurantUser;

@Scope("prototype")
@Controller("RestaurantController")
@RequestMapping(value = "/restaurant")
public class RestaurantController {

	private static Log log = LogFactory.getLog(RestaurantController.class);
	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restaurantService;
	
	@Autowired
	@Qualifier("printerService")
	private PrinterService printerService;
	
	@Autowired
	@Qualifier("companyService")
	private CompanyService companyService;
	
	
	private Map<String, Object> result = new HashMap<String, Object>();

	
	/**
	 * 餐厅列表
	 * @param model
	 * @return
	 */
	@RequestMapping("/hqrestaurant")
	public String forwardMmanager(Model model) {

		try {
			RestaurantUser res = new RestaurantUser();
			// 设置当前用户
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute(
					"currentUser");
			res.setCompanyId(currentUsers.getCompanyId());
			res.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
			List<RestaurantUser> resList = null;
			//查询当前公司的所有manager
			resList = restaurantService.selectByResM(res);
			
			Company company = companyService.selectByPrimaryKey(currentUsers.getCompanyId());
			
			model.addAttribute("resList", resList);
			model.addAttribute("company", company);
			return "forward:/pages/hqmanager/hq_restaurant.jsp";
		} catch (Exception e) {
			log.error(this,e);
            return null;
		}
	}
	
	
	

	/**
	 * 新增餐厅
	 * @param restaurant
	 * @return
	 */
	@RequestMapping("/addRestaurant")
	@ResponseBody
	public Map<String, Object> addRestaurant(@ModelAttribute Restaurant restaurant) {
		try {
		
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			restaurant.setCompanyId(currentUsers.getCompanyId());
			// 设置用户级别
			//restaurant.setType(UserConstant.USER_TYPE_CLOUD);
						// 设置用户状态
			restaurant.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			//创建时间
			restaurant.setCreateTime(new Date());
			//更新时间
			restaurant.setUpdateTime(new Date());

			int flag = restaurantService.insert(restaurant);
			//创建餐厅初始化打印机
			Printer printer=new Printer();
			printer.setType(CommonStatusConstant.IS_ACTIVE_NORMAL);
			printer.setCreateTime(new Date());
			printer.setUpdateTime(new Date());
			printer.setCompanyId(currentUsers.getCompanyId());
			printer.setRestaurantId(flag);
			printer.setPrinterName("cash");
			printerService.insert(printer);

			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}
		
	}


	/**
	 * 更新餐厅
	 * @param restaurant
	 * @return
	 */
	@RequestMapping("/updateRestaurant")
	@ResponseBody
	public Map<String, Object> updateRestaurant(@ModelAttribute Restaurant restaurant) {
		try {
		
			
			//更新时间
			restaurant.setUpdateTime(new Date());
			int flag = restaurantService.updateById(restaurant);
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.RESTAURANT);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}
		
	}
	
	
	
	/**
	 * 删除餐厅
	 * @param id
	 * @return
	 */
	@RequestMapping("/delRestaurant")
	@ResponseBody
	public Map<String, Object> delRestaurant(@RequestParam int id) {
		try {
		
			int flag = restaurantService.deleteByPrimaryKey(id);
			
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}
	
	}
	/**
	 * 验证餐厅
	 * @param restaurantName
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkRestaurant")
	@ResponseBody
	public Map<String, Object> checkRestaurant(
			@RequestParam String restaurantName,@RequestParam int id){
		boolean flag = true;
		Subject currentUser = SecurityUtils.getSubject();
		User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
	
		Restaurant re = new Restaurant();
		re.setRestaurantName(restaurantName);
		re.setCompanyId(currentUsers.getCompanyId());
		re.setStatus(2);
		
		if (id != 0) {
			re.setId(id);
		}
		Restaurant res = new Restaurant();
		try {

			res = restaurantService.selectByRestaurant(re);
			if (res != null) {
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
