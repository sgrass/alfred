package com.alfred.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.ItemCategory;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.ItemCategoryService;
import com.alfred.util.PushMsgUtil;

@Scope("prototype")
@Controller
@RequestMapping(value = "category")
public class ItemCategoryController {

	private static Log log = LogFactory.getLog(ItemCategoryController.class);

	@Autowired
	@Qualifier("itemCategoryService")
	private ItemCategoryService itemCategoryService;

	@RequestMapping("/queryByMainCategoryId")
	@ResponseBody
	public List queryByMainCategoryId(@RequestParam Integer mainCategoryId) {
		try {
			List<ItemCategory> list = itemCategoryService.selectByMainCategory(mainCategoryId);
			
			return list;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute ItemCategory itemCategory) {
		try {
			User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			
			
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			
			itemCategory.setUserId(user.getId());
			itemCategory.setRestaurantId(restaurantId);
			itemCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			itemCategoryService.insert(itemCategory);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.ITEM);
			return "redirect:/mainCategory/queryCategory";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	
	@RequestMapping("/updateById")
	public String updateById(HttpServletRequest request, @ModelAttribute ItemCategory itemCategory) {
		try {
			User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			itemCategory.setRestaurantId(restaurant.getId());
			
			itemCategory.setUserId(user.getId());
			itemCategoryService.updateCascadById(itemCategory);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.ITEM);
			return "redirect:/mainCategory/queryCategory";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	@RequestMapping("/deleteById")
	public String deleteById(HttpServletRequest request, @RequestParam Integer id) {
		try {
			User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			
			itemCategoryService.deleteCascadById(restaurant.getId(),id,user.getId());
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.ITEM);
			return "redirect:/mainCategory/queryCategory";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
}
