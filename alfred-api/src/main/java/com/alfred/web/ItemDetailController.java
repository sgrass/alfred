package com.alfred.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.ItemConstant;
import com.alfred.http.ResultCode;
import com.alfred.model.ItemCategory;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemMainCategory;
import com.alfred.model.ItemModifier;
import com.alfred.model.ItemSetMeal;
import com.alfred.model.Modifier;
import com.alfred.service.ItemCategoryService;
import com.alfred.service.ItemDetailService;
import com.alfred.service.ItemMainCategoryService;
import com.alfred.service.ItemModifierService;
import com.alfred.service.ItemSetMealService;
import com.alfred.service.ModifierService;
import com.alfred.util.ConfigHelper;
import com.alfred.util.Skip32Util;
import com.itextpdf.text.pdf.AcroFields.Item;


@Scope("prototype")
@Controller
@RequestMapping(value = "/item")
public class ItemDetailController extends BaseController{
	
	@Autowired
	@Qualifier("itemMainCategoryService")
	private ItemMainCategoryService itemMainCategoryService;

	@Autowired
	@Qualifier("itemCategoryService")
	private ItemCategoryService itemCategoryService;
	
	@Autowired
	@Qualifier("itemDetailService")
	private ItemDetailService itemDetailService;
	
	@Autowired
	@Qualifier("modifierService")
	private ModifierService modifierService;
	
	@Autowired
	@Qualifier("itemModifierService")
	private ItemModifierService itemModifierService;
	
	@Autowired
	@Qualifier("itemSetMealService")
	private ItemSetMealService itemSetMealService;
	
	
	
	private static Log log = LogFactory.getLog(ItemDetailController.class);
	
	
	/**
	 * 根据餐厅id获取菜单主分类、子分类
	 * @return
	 */
	@RequestMapping(value="/getItemCategory", method =RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getItemCategory(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");
			
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
			
			ItemMainCategory mainCategory = new ItemMainCategory();
			mainCategory.setRestaurantId(restaurantId);
			mainCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<ItemMainCategory> mainCategoryList = itemMainCategoryService.selectByParam(mainCategory);
			
			ItemCategory subCategory = new ItemCategory();
			subCategory.setRestaurantId(restaurantId);
			subCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<ItemCategory> subCategoryList = itemCategoryService.selectByParam(subCategory);
			
			resultMap.put("mainCategoryList", mainCategoryList);
			resultMap.put("subCategoryList", subCategoryList);
			
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}

	
	/**
	 * 根据餐厅revenueId获取菜单详情
	 * @return
	 */
	@RequestMapping(value="/getItem", method =RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getItem(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");
			
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
			Integer revenueId = jsonObject.getInt("revenueId");
			
			List<ItemDetail> itemList = itemDetailService.selectByRevenueId(restaurantId, revenueId, ItemConstant.ITEM_TYPE_SUB_ITEM, CommonStatusConstant.IS_ACTIVE_NORMAL);
	
			ItemSetMeal itemSetMeal = new ItemSetMeal();
			itemSetMeal.setRestaurantId(restaurantId);
			List<ItemSetMeal> itemSetMealList = itemSetMealService.selectByParam(itemSetMeal);
			
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			resultMap.put("itemList", itemList);
			resultMap.put("itemSetMealList", itemSetMealList);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}
	
	
	/**
	 * 根据餐厅id modifier，以及modifier和item的关系
	 * @return
	 */
	@RequestMapping(value="/getModifier", method =RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getModifier(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");
			
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
		
			Modifier modifier = new Modifier();
			modifier.setRestaurantId(restaurantId);
			modifier.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<Modifier> modifierList = modifierService.selectByParam(modifier);
	
			ItemModifier im = new ItemModifier();
			im.setRestaurantId(restaurantId);
			im.setType(0);
			List<ItemModifier> itemModifierList = itemModifierService.selectByParam(im);
			
			resultMap.put("modifierList", modifierList);
			resultMap.put("itemModifierList", itemModifierList);
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}
}
