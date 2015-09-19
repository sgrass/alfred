package com.alfred.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
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

import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.ItemMainCategory;
import com.alfred.model.Printer;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.ItemCategoryService;
import com.alfred.service.ItemMainCategoryService;
import com.alfred.service.PrinterService;
import com.alfred.util.PushMsgUtil;
import com.alfred.vo.ItemMainCategoryVO;

@Scope("prototype")
@Controller
@RequestMapping(value = "mainCategory")
public class ItemMainCategoryController {

	private static Log log = LogFactory.getLog(ItemMainCategoryController.class);

	@Autowired
	@Qualifier("itemMainCategoryService")
	private ItemMainCategoryService itemMainCategoryService;

	@Autowired
	@Qualifier("itemCategoryService")
	private ItemCategoryService itemCategoryService;
	
	@Autowired
	@Qualifier("printerService")
	private PrinterService printerService;
	
	@RequestMapping("queryCategory")
	public String queryCategory(HttpServletRequest request, Model model) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute("currentUser");
			
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			List<ItemMainCategoryVO> categoryList = itemMainCategoryService.selectByRestaurant(restaurantId);
			
			List<Printer> printerList = printerService.selectByRestId(user.getCompanyId(), restaurantId);
			
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("printerList", printerList);
			return "/pages/item/item_category.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute ItemMainCategory itemMainCategory) {
		try {
			User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			itemMainCategory.setUserId(user.getId());
			itemMainCategory.setRestaurantId(restaurantId);
			itemMainCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			if (StringUtils.indexOf(CommonStatusConstant.CASCAD_REST_TYPE, restaurant.getType().toString()) > -1) {
				itemMainCategoryService.insertCascad(itemMainCategory);
			} else {
				itemMainCategoryService.insert(itemMainCategory);
			}
			PushMsgUtil.sendRestaurant(restaurantId, PushMsgUtil.ITEM);
			return "redirect:/mainCategory/queryCategory";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/updateById")
	public String updateById(HttpServletRequest request, @ModelAttribute ItemMainCategory itemMainCategory) {
		try {
			User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			
			itemMainCategory.setUserId(user.getId());
			itemMainCategory.setRestaurantId(restaurant.getId());
			
			itemMainCategoryService.updateCascadById(itemMainCategory);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.ITEM);
			return "redirect:/mainCategory/queryCategory";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/deleteById")
	public String deleteById(HttpServletRequest request, @ModelAttribute ItemMainCategory itemMainCategory) {
		try {
			User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			itemMainCategoryService.deleteCascadById(restaurant.getId(),itemMainCategory.getId(),user.getId());
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.ITEM);
			return "redirect:/mainCategory/queryCategory";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
}
