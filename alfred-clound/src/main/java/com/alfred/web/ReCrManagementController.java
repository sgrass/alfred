package com.alfred.web;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alfred.constant.UserConstant;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.model.UserRestaurant;
import com.alfred.service.RevenueCenterService;
import com.alfred.service.UserRestaurantService;
import com.alfred.service.UserService;
import com.alfred.vo.UserManager;
@Scope("prototype")
@Controller("ReCrManagementController")
@RequestMapping(value = "/reCrManagement")
public class ReCrManagementController {
	
	private static Log log = LogFactory.getLog(ReCrManagementController.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;

	@Autowired
	@Qualifier("userRestaurantService")
	private UserRestaurantService userRestaurantService;
	
	//private Map<String, Object> result = new HashMap<String, Object>();

	/**
	 * revenue 列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/revenueTabIndex")
	public String forwardrevenueTabIndex(HttpServletRequest request,Model model) {
		
		try {
		// 获取revenue tab 信息
		RevenueCenter revenueCenter = new RevenueCenter();
		
		List<RevenueCenter> revenueCenterList = null;
		// 获取餐厅的ID
		Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
	    int revenueId=0;
		// 设置餐厅下面revenueCenter
        revenueCenter.setRestaurantId(res.getId());
		UserRestaurant userRestaurant=new UserRestaurant();
		UserManager u=new UserManager();
	    u.setCompanyId(res.getCompanyId());
	    u.setRestaurantId(res.getId());
	    u.setStatus(UserConstant.USER_STATUS_DELETE);
		List<UserManager> userManagerList=null; 
	    List<UserManager> userList=new ArrayList<UserManager>();
	
			revenueCenterList = revenueCenterService.selectByParam(revenueCenter);
            if(revenueCenterList!=null){
            	for(RevenueCenter t:revenueCenterList){
    				revenueId=t.getId();
    				break;
    			}
            }
            userRestaurant.setRevenueId(revenueId);
			userManagerList=userService.selectByRestaurantId(u);
			//查询权限表中存哪些
			List<UserRestaurant> li=userRestaurantService.selectByParam(userRestaurant);
			for (UserManager p : userManagerList) {
				
				if(li!=null){
					for(UserRestaurant userRes:li){
						if(p.getUserId().equals(userRes.getUserId())&&p.getRestaurantId().equals(userRes.getRestaurantId())){
							p.setRevenueId(userRes.getRevenueId());
							break;
						}
					}
				}
				userList.add(p);
			}	
		   model.addAttribute("revenueId",revenueId);
		   model.addAttribute("revenueCenterList", revenueCenterList);
		   model.addAttribute("userManagerList",userList);
			
	        if(revenueId==0){
	        	return "forward:/pages/rtmanager/rt_prompt.jsp";
	        }
			return "forward:/pages/rtmanager/rt_revenueCenM.jsp";

		} catch (Exception e) {
			// TODO: handle exception
			log.error(this,e);
			return null;
		}
     

	}
	
	
	
	
	/**
	 * revenue 列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/revenueTab")
	public String forwardrevenueTabIndex(HttpServletRequest request,Model model,@RequestParam int revenueId) {
		// 获取revenue tab 信息
		
		try {
		RevenueCenter revenueCenter = new RevenueCenter();
		
		List<RevenueCenter> revenueCenterList = null;
		// 获取餐厅的ID
		Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
		// 设置餐厅下面revenueCenter
        revenueCenter.setRestaurantId(res.getId());
		UserRestaurant userRestaurant=new UserRestaurant();
		UserManager u=new UserManager();
	    u.setCompanyId(res.getCompanyId());
	    u.setRestaurantId(res.getId());
	    u.setStatus(UserConstant.USER_STATUS_DELETE);
		List<UserManager> userManagerList=null; 
	    List<UserManager> userList=new ArrayList<UserManager>();

			revenueCenterList = revenueCenterService.selectByParam(revenueCenter);
            userRestaurant.setRevenueId(revenueId);
			userManagerList=userService.selectByRestaurantId(u);
			//查询权限表中存哪些
			List<UserRestaurant> li=userRestaurantService.selectByParam(userRestaurant);
			for (UserManager p : userManagerList) {
				
				if(li!=null){
					for(UserRestaurant userRes:li){
						if(p.getUserId().equals(userRes.getUserId())&&p.getRestaurantId().equals(userRes.getRestaurantId())){
							p.setRevenueId(userRes.getRevenueId());
							break;
						}
					}
				}
				userList.add(p);
			}	
			
	    model.addAttribute("revenueId",revenueId);
		model.addAttribute("revenueCenterList", revenueCenterList);
		model.addAttribute("userManagerList",userList);
        if(revenueId==0){
        	
        	return "forward:/pages/rtmanager/rt_prompt.jsp";
        }
		return "forward:/pages/rtmanager/rt_revenueCenM.jsp";	

		} catch (Exception e) {
			// TODO: handle exception
			log.error(this,e);
			return null;
		}
       

	}
	
	


	/**
	 * 
	 * @param request
	 * @param printer
	 * @return
	 */
	@RequestMapping("/addManPower")
	public String addManPower(HttpServletRequest request,@RequestParam int revenueId,Model model) {
		try {
			Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
			String[] userIds=request.getParameterValues("country");
			userRestaurantService.updateManPower(userIds,revenueId,res.getId());
			return "redirect:/reCrManagement/revenueTab?revenueId="+revenueId;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
		

	}

}
