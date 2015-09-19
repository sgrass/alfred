package com.alfred.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.service.ItemDetailService;
import com.alfred.service.RevenueCenterService;
import com.alfred.vo.MainMuenTree;
@Scope("prototype")
@Controller("RVCMenuController")
@RequestMapping(value = "/rvcMenu")
public class RVCMenuController {
	private static Log log = LogFactory.getLog(RVCMenuController.class);
	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;
	
	@Autowired
	@Qualifier("itemDetailService")
	private ItemDetailService itemDetailService;
	
	
	private Map<String, Object> result = new HashMap<String, Object>();

	@RequestMapping("/revenueTabIndex")
	public String forwardrevenueTabIndex(HttpServletRequest request,Model model) {
		// 获取revenue tab 信息
		try {
			
		RevenueCenter revenueCenter = new RevenueCenter();
		List<RevenueCenter> revenueCenterList = null;
		Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
	    int revenueId=0;
		// 设置餐厅下面revenueCenter
        revenueCenter.setRestaurantId(res.getId());
        
        revenueCenterList = revenueCenterService.selectByParam(revenueCenter);
			
            if(revenueCenterList!=null){
            	for(RevenueCenter t:revenueCenterList){
    				revenueId=t.getId();
    				break;
    			}
            }
            
            model.addAttribute("revenueId",revenueId);
    		model.addAttribute("revenueCenterList", revenueCenterList);
    		
            if(revenueId==0){
            	return "forward:/pages/rtmanager/rt_prompt.jsp";
            }
    		return "forward:/pages/rtmanager/rt_rvcMenu.jsp";
            
		} catch (Exception e) {
			// TODO: handle exception
			log.error(this,e);
			return null;
		}
    

	}
	
	/**
	 */
	@RequestMapping("/loadTreeData")
	@ResponseBody
	public Map<String, Object> loadTreeData(HttpServletRequest request) {
		List<MainMuenTree> mtl=new ArrayList<MainMuenTree>();
		try {
			Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
	
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("restaurantId",res.getId());
			
			mtl=itemDetailService.selectItemDetailTree(map);
			
			result.put("mtlJson", mtl);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	/**
	 */
	@RequestMapping("/loadUpdateTreeData")
	@ResponseBody
	public Map<String, Object> loadUpdateTreeData(HttpServletRequest request,@RequestParam int revenueId){
		List<MainMuenTree> mtl=new ArrayList<MainMuenTree>();
		Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
		try {
			HashMap<String, Object> map=new HashMap<String, Object>();
			map.put("restaurantId",res.getId());
			map.put("revenueId",revenueId);
			mtl=itemDetailService.selectItemUpdateDetailTree(map);
			
			result.put("mtlJson", mtl);
			return result;
		} catch (Exception e) {
			log.error(this, e);
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
	public String forwardrevenueTabIndex(HttpServletRequest request,
			Model model, @RequestParam int revenueId) {
		// 获取revenue tab 信息
		try {

			RevenueCenter revenueCenter = new RevenueCenter();

			List<RevenueCenter> revenueCenterList = null;
			// 获取餐厅的ID
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			// 设置餐厅下面revenueCenter
			revenueCenter.setRestaurantId(res.getId());

			revenueCenterList = revenueCenterService.selectByParam(revenueCenter);
			model.addAttribute("revenueId", revenueId);
			model.addAttribute("revenueCenterList", revenueCenterList);
			if (revenueId == 0) {

				return "forward:/pages/rtmanager/rt_prompt.jsp";
			}
			return "forward:/pages/rtmanager/rt_rvcMenu.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			log.error(this, e);
			return null;
		}

	}
	

	/**
	 * 新增revenueCenterMenuTree
	 * @param request
	 * @param revenueCenter
	 * @return
	 */
	@RequestMapping("/addRevenueCenterMenuTree")
	@ResponseBody
	public Map<String, Object> addRevenueCenterMenuTree(HttpServletRequest request,@RequestParam String[] itemDetailIds,@RequestParam int revenueId,@RequestParam String[] removeItemDetailId) {
		try {
			
			Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
			itemDetailService.insertRevenueCenterMenu(itemDetailIds, revenueId,removeItemDetailId,res.getId());
			result.put("flag", 1);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	
	}

	
	
	
	
	
	
	
	
}
