package com.alfred.web;

import java.util.List;

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

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.SysRestTemplate;
import com.alfred.model.Modifier;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.ItemMainCategoryService;
import com.alfred.service.ModifierService;
import com.alfred.service.PrinterService;
import com.alfred.util.PushMsgUtil;
import com.alfred.vo.ModifierVO;

@Scope("prototype")
@Controller
@RequestMapping(value = "modifier")
public class ModifierController {

	private static Log log = LogFactory.getLog(ModifierController.class);

	@Autowired
	@Qualifier("itemMainCategoryService")
	private ItemMainCategoryService itemMainCategoryService;

	@Autowired
	@Qualifier("printerService")
	private PrinterService printerService;
	
	@Autowired
	@Qualifier("modifierService")
	private ModifierService modifierService;
	
	
	@RequestMapping("queryCategory")
	public String queryCategory(HttpServletRequest request, Model model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			List<ModifierVO> categoryList = modifierService.selectModifierCategory(restaurantId);
			
			Integer templateRestId = SysRestTemplate.getRestConfigMap().get(restaurant.getType());
			List<Modifier> templateModifierList = modifierService.selectByRestaurant(templateRestId, CommonStatusConstant.IS_ACTIVE_NORMAL, 0);
			
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("templateModifierList", templateModifierList);
			return "/pages/item/modifier_category.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute Modifier modifier, 
			@RequestParam(required=false) String status, @RequestParam(required=false) Integer templateCateId) {
		try {
			User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			if ("category".equals(status)) {
				modifier.setType(0);
			} else {
				modifier.setType(1);
			}
			modifier.setRestaurantId(restaurantId);
			modifierService.insert(modifier, templateCateId);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.MODIFIER);
			return "redirect:/modifier/queryCategory";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/updateById")
	public String updateById(HttpServletRequest request, @ModelAttribute Modifier modifier, @RequestParam(required=false) String status) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			
			Modifier param = new Modifier();
			param.setId(modifier.getId());
			if ("category".equals(status)) {
				param.setCategoryId(modifier.getCategoryId());
				param.setCategoryName(modifier.getCategoryName());
			} else {
				Integer restaurantId = restaurant.getId();
				param.setRestaurantId(restaurantId);
				param.setCategoryId(modifier.getCategoryId());
				param.setModifierName(modifier.getModifierName());
				param.setPrice(modifier.getPrice());
			}
			param.setIsActive(modifier.getIsActive());
			param.setIsDefault(modifier.getIsDefault());
			modifierService.updateById(param);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.MODIFIER);
			return "redirect:/modifier/queryCategory";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/deleteById")
	public String deleteById(HttpServletRequest request,@RequestParam(value="id") Integer id, @RequestParam(required=false) String status) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			
			modifierService.deleteById(id,status);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.MODIFIER);
			return "redirect:/modifier/queryCategory";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	
}
