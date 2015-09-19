package com.alfred.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alfred.model.ItemHappyHour;
import com.alfred.model.Restaurant;
import com.alfred.service.ItemHappyHourService;
import com.alfred.util.PushMsgUtil;

@Scope("prototype")
@Controller
@RequestMapping(value = "/itemHappy")
public class ItemHappyHourController {

	private static Log log = LogFactory.getLog(ItemHappyHourController.class);

	@Autowired
	@Qualifier("itemHappyHourService")
	private ItemHappyHourService itemHappyHourService;


	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute ItemHappyHour itemHappyHour) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			
			int type = 1;
			if (itemHappyHour.getItemCategoryId() != null) {
				type = 2;
			} else {
				itemHappyHour.setItemCategoryName(null);
			}
			if (itemHappyHour.getItemId() != null) {
				type = 3;
			} else {
				itemHappyHour.setItemName(null);
			}
			
			itemHappyHour.setType(type);
			itemHappyHourService.insert(itemHappyHour);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.HAPPY_HOURS);
			return "redirect:/happyHour/queryAll";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	@RequestMapping("/updateById")
	public String updateById(HttpServletRequest request, @ModelAttribute ItemHappyHour itemHappyHour) {
		Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
		try {
			int type = 1;
			if (itemHappyHour.getItemCategoryId() != null) {
				type = 2;
			} else {
				itemHappyHour.setItemCategoryName(null);
			}
			if (itemHappyHour.getItemId() != null) {
				type = 3;
			} else {
				itemHappyHour.setItemName(null);
			}
			itemHappyHour.setType(type);
			itemHappyHourService.updateById(itemHappyHour);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.HAPPY_HOURS);
			return "redirect:/happyHour/queryAll";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/deleteById")
	public String deleteById(HttpServletRequest request, @RequestParam Integer id) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			
			itemHappyHourService.deleteByPrimaryKey(id);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.HAPPY_HOURS);
			return "redirect:/happyHour/queryAll";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
}
