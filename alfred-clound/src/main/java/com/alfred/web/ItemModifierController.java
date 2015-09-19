package com.alfred.web;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.ItemConstant;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemModifier;
import com.alfred.model.Modifier;
import com.alfred.model.Restaurant;
import com.alfred.service.ItemDetailService;
import com.alfred.service.ItemMainCategoryService;
import com.alfred.service.ItemModifierService;
import com.alfred.service.ModifierService;
import com.alfred.util.PushMsgUtil;
import com.alfred.vo.ItemMainCategoryVO;

@Scope("prototype")
@Controller
@RequestMapping(value = "itemModifier")
public class ItemModifierController {

	private static Log log = LogFactory.getLog(ItemModifierController.class);

	@Autowired
	@Qualifier("itemMainCategoryService")
	private ItemMainCategoryService itemMainCategoryService;

	@Autowired
	@Qualifier("modifierService")
	private ModifierService modifierService;

	@Autowired
	@Qualifier("itemModifierService")
	private ItemModifierService itemModifierService;
	
	@Autowired
	@Qualifier("itemDetailService")
	private ItemDetailService itemDetailService;
	
	@RequestMapping("/querySubCateModifier")
	public String querySubCateModifier(HttpServletRequest request, Model model) {

		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			List<ItemMainCategoryVO> categoryList = itemMainCategoryService.selectByRestaurant(restaurantId);
			List<ItemModifier> itemModifierList = itemModifierService.selectItemModifier(restaurantId);
			Modifier param = new Modifier();
			param.setRestaurantId(restaurantId);
			param.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			param.setType(0);
			List<Modifier> modifierCategoryList = modifierService.selectByParam(param);
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("itemModifierList", itemModifierList);
			model.addAttribute("modifierCategoryList", modifierCategoryList);
			return "/pages/item/item_category_modifier.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	@RequestMapping("/queryItemModifier")
	public String queryItemModifier(HttpServletRequest request,
			@RequestParam(required = false) Integer itemMainCategoryId,
			@RequestParam(required = false) Integer itemCategoryId, Model model) {

		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			List<ItemModifier> itemModifierList = itemModifierService.selectItemModifier(restaurantId);
			ItemDetail itemDetail = new ItemDetail();
			itemDetail.setRestaurantId(restaurantId);
			itemDetail.setItemMainCategoryId(itemMainCategoryId);
			itemDetail.setItemCategoryId(itemCategoryId);
			itemDetail.setItemType(ItemConstant.ITEM_TYPE_TEMPLATE);
			itemDetail.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<ItemDetail> itemDetailList = itemDetailService.selectByParam(itemDetail);
			Modifier param = new Modifier();
			param.setRestaurantId(restaurantId);
			param.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			param.setType(0);
			List<Modifier> modifierCategoryList = modifierService.selectByParam(param);
			model.addAttribute("itemModifierList", itemModifierList);
			model.addAttribute("modifierCategoryList", modifierCategoryList);
			model.addAttribute("itemDetailList", itemDetailList);
			return "/pages/item/item_modifier.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public int insert(HttpServletRequest request, @RequestParam Integer itemCategoryId,
			@RequestParam Integer itemId, @RequestParam Integer... modifierIds) {
		
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			int resultRow = itemModifierService.insert(restaurant.getId(), itemCategoryId, itemId, modifierIds);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.MODIFIER);
			return resultRow;
		} catch (Exception e) {
			log.error(this, e);
			return 0;
		}
	}

	@RequestMapping("/insertCateModifier")
	@ResponseBody
	public boolean insertCateModifier(HttpServletRequest request, 
			@RequestParam Integer itemCategoryId, @RequestParam Integer... modifierIds) {
		try {
			boolean flag = false;
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			flag = itemModifierService.insertSubCateModifier(restaurant.getId(), itemCategoryId, modifierIds);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.MODIFIER);
			return flag;
		} catch (Exception e) {
			log.error(this, e);
			return false;
		}
	}
}
