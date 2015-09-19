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
import com.alfred.model.Company;
import com.alfred.model.HappyHour;
import com.alfred.model.Printer;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.model.User;
import com.alfred.service.CompanyService;
import com.alfred.service.HappyHourService;
import com.alfred.service.PrinterService;
import com.alfred.service.RevenueCenterService;
import com.alfred.util.DateUtil;
import com.alfred.util.PushMsgUtil;
import com.alfred.vo.RevenueCenterAndPrinter;

@Scope("prototype")
@Controller("RevenueCenterController")
@RequestMapping(value = "/revenueCenter")
public class RevenueCenterController {
	

	private static Log log = LogFactory.getLog(RevenueCenterController.class);
	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;
	
	
	
	@Autowired
	@Qualifier("printerService")
	private PrinterService printerService;
	
	
	@Autowired
	@Qualifier("happyHourService")
	private HappyHourService happyHourService;
	
	@Autowired
	@Qualifier("companyService")
	private CompanyService companyService;
	
	
	private Map<String, Object> result = new HashMap<String, Object>();
	
	
	
	
	/**
	 * revenue Center
	 * @param model
	 * @return
	 */
		@RequestMapping("/rtRevenueCenter")
		public String forwardRevenueCenter(HttpServletRequest request ,Model model) {
			
		 try {
				
			// 设置当前用户
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
          
			Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");

			//revenueCenter
			RevenueCenterAndPrinter revenueCenter=new RevenueCenterAndPrinter();
			Printer pe=new Printer();
			
			//设置公司 打印机
			pe.setCompanyId(currentUsers.getCompanyId());
			//设置餐厅 打印机
			pe.setRestaurantId(res.getId());
			pe.setType(CommonStatusConstant.IS_ACTIVE_NORMAL);
			//设置餐厅   收入中心
			revenueCenter.setRestaurantId(res.getId());
			revenueCenter.setIsActive(CommonStatusConstant.IS_ACTIVE_DISABLE);
			
			HappyHour happyHour=new HappyHour();
			happyHour.setRestaurantId(res.getId());
			//happyHour.setIsActive(isActive);
			
			
			List<RevenueCenterAndPrinter>  revenueCenterList = null;
			List<Printer>  printerlist = null;
			List<HappyHour> happyHourList=null;
			
	
	            //查询当前餐厅下的所有收入中心
			revenueCenterList = revenueCenterService.selectByRevenList(revenueCenter);
			  //查询当前公司所有的打印机
			printerlist=printerService.selectByParam(pe);
			happyHourList=happyHourService.selectByParam(happyHour);
			
			Company company = companyService.selectByPrimaryKey(currentUsers.getCompanyId());
			
			model.addAttribute("revenueCenterList",revenueCenterList);
			model.addAttribute("printerlist",printerlist);
			model.addAttribute("happyHourList",happyHourList);
			model.addAttribute("company", company);
			return "forward:/pages/rtmanager/rt_revenuecenter.jsp";
				
			} catch (Exception e) {
				log.error(this, e);
				return null;
			}
			
			
			
		}
		
		
	
		/**
		 * RevenueCenter
		 * @paramRevenueCenter
		 * @return
		 */
		@RequestMapping("/addRevenueCenter")
		@ResponseBody
		public Map<String, Object> addRevenueCenter(HttpServletRequest request ,@ModelAttribute RevenueCenter revenueCenter,@RequestParam String startTime,@RequestParam String endTime) {
			try {
		
				Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
                
				// 设置用户状态
				revenueCenter.setRestaurantId(res.getId());
				//revenueCenter.setIsActive(1);
				revenueCenter.setCreateTime(new Date());
				revenueCenter.setUpdateTime(new Date());
			    if(!startTime.equals("")){
			    	startTime+=" 00:00:00";
			    	revenueCenter.setHappyStartTime(DateUtil.getDateParse(DateUtil.DATE_SHORT_PATTERN, startTime));
			    }
			    if(!endTime.equals("")){
			    	endTime+=" 23:59:59";
			    	revenueCenter.setHappyEndTime(DateUtil.getDateParse(DateUtil.DATE_SHORT_PATTERN, endTime));

			    }
				
				//更新时
				int flag = revenueCenterService.insert(revenueCenter);
				result.put("flag", flag);
				
				return result;
			} catch (Exception e) {
				log.error(this, e);
				return null;
			}

		}

		
			
		/**
		 * 修改收银中心
		 * @param revenueCenter
		 * @param startTime
		 * @param endTime
		 * @return
		 */
		@RequestMapping("/updateRevenueCenter")
		@ResponseBody
		public Map<String, Object> updateRevenueCenter(HttpServletRequest request ,@ModelAttribute RevenueCenter revenueCenter,@RequestParam String startTime,@RequestParam String endTime) {
			try {
				Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
				revenueCenter.setUpdateTime(new Date());
				   if(!startTime.equals("")){
				    	startTime+=" 00:00:00";
				    	revenueCenter.setHappyStartTime(DateUtil.getDateParse(DateUtil.DATE_SHORT_PATTERN, startTime));
				    }
				    if(!endTime.equals("")){
				    	endTime+=" 23:59:59";
				    	revenueCenter.setHappyEndTime(DateUtil.getDateParse(DateUtil.DATE_SHORT_PATTERN, endTime));
				    }
				int flag = revenueCenterService.updateById(revenueCenter);
				result.put("flag", flag);
				PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.RESTAURANT);

				return result;
			} catch (Exception e) {
				log.error(this, e);
				return null;

			}
			
		}

		/**
		 * 删除收银中心
		 * @param request
		 * @param id
		 * @return
		 */
		@RequestMapping("/delRevenueCenter")
		@ResponseBody
		public Map<String, Object> delRevenueCenter(HttpServletRequest request ,@RequestParam int id) {
			try {
				
				
				Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");

				
				int flag = revenueCenterService.deleteByPrimaryKey(id,res.getId());
		
				result.put("flag", flag);
				
				PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.RESTAURANT);

				return result;
			} catch (Exception e) {
				log.error(this, e);
				return null;

			}
			
		}
		

		/**
		 */
		@RequestMapping("/checkRevenueCenter")
		@ResponseBody
		public Map<String, Object> checkRevenueCenter(
				@RequestParam String revenueCenterName,@RequestParam int id){
			boolean flag = true;
			try {
				RevenueCenter revenueCenter = revenueCenterService.selectByRevenueCenter(revenueCenterName,id);
				if (revenueCenter != null) {
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
