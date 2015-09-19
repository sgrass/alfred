package com.alfred.web;

import java.math.BigDecimal;
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

import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.InventoryRawMaterial;
import com.alfred.model.InventoryRecipeMaterial;
import com.alfred.model.Modifier;
import com.alfred.model.Restaurant;
import com.alfred.pagination.Pagination;
import com.alfred.service.InventoryRawMaterialService;
import com.alfred.service.InventoryRecipeMaterialService;
import com.alfred.service.ModifierService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/modifierMaterial")
public class InventoryModifierMaterialController {
	
	private static Log log = LogFactory.getLog(InventoryModifierMaterialController.class);
	
	@Autowired
	@Qualifier("modifierService")
	private ModifierService modifierService;
	
	@Autowired
	@Qualifier("inventoryRawMaterialService")
	private InventoryRawMaterialService inventoryRawMaterialService;
	
	@Autowired
	@Qualifier("inventoryRecipeMaterialService")
	private InventoryRecipeMaterialService inventoryRecipeMaterialService;
	
	@RequestMapping("/queryAll")
	public String queryAll(HttpServletRequest request, @RequestParam(required = false) Integer currPage,
			@RequestParam(required = false) Integer modifierId, Model model) {
		try {
			
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			
			Modifier modifierCategory = new Modifier();
			modifierCategory.setRestaurantId(restaurantId);
			modifierCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			modifierCategory.setType(0);
			List<Modifier> modifierCateList = modifierService.selectByParam(modifierCategory);
			
			Modifier modifier = new Modifier();
			modifier.setRestaurantId(restaurantId);
			modifier.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			modifier.setType(1);
			modifier.setCategoryId(modifierId);
			if (currPage == null) {
				currPage = 0;
			}
			Pagination page = new Pagination();
			page.setItems(modifierService.selectCountByParam(modifier));
			currPage = currPage < 1 ? 1 : currPage;
			page.setPage(currPage);
			List<Modifier> modifierList = modifierService.selectPageByParam(modifier,page);
			
			
			
			InventoryRawMaterial inventoryRawMaterial = new InventoryRawMaterial();
			inventoryRawMaterial.setRestaurantId(restaurant.getId());
			inventoryRawMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<InventoryRawMaterial> materialList = inventoryRawMaterialService.selectByParam(inventoryRawMaterial);
			
			InventoryRecipeMaterial inventoryRecipeMaterial = new InventoryRecipeMaterial();
			inventoryRecipeMaterial.setRestaurantId(restaurantId);
			inventoryRecipeMaterial.setType(1);
			inventoryRecipeMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<InventoryRecipeMaterial> rmList = inventoryRecipeMaterialService.selectByParam(inventoryRecipeMaterial);
			
			model.addAttribute("materialList", materialList);
			model.addAttribute("rmList", rmList);
			
			model.addAttribute("modifierCateList", modifierCateList);
			model.addAttribute("modifierList", modifierList);
			model.addAttribute("modifierId", modifierId);
			
			
			model.addAttribute("currPage", currPage);
			model.addAttribute("rowCount", page.getItems());
			model.addAttribute("startRow", page.getStartRow());
			model.addAttribute("endRow", page.getEndRow());
			model.addAttribute("pageSize", page.getPages());
			return "/pages/inventory/modifier_material_list.jsp";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @RequestParam Integer modifierId, @RequestParam(required = false) Integer... checkedIds) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			
			List<InventoryRecipeMaterial> rmList = new ArrayList<InventoryRecipeMaterial>();
			
			if(checkedIds != null && checkedIds.length > 0) {
				for (Integer checkedId : checkedIds) {
					String materialId = request.getParameter("materialId"+checkedId);
					String materialName = request.getParameter("materialName"+checkedId);
					String useQty = request.getParameter("useQty"+checkedId);
					String unitOfMeasurement = request.getParameter("unitOfMeasurement"+checkedId);
					
					InventoryRecipeMaterial inventoryRecipeMaterial = new InventoryRecipeMaterial();
					inventoryRecipeMaterial.setRestaurantId(restaurantId);
					inventoryRecipeMaterial.setModifierId(modifierId);
					inventoryRecipeMaterial.setMaterialId(Integer.parseInt(materialId));
					inventoryRecipeMaterial.setMaterialName(materialName);
					inventoryRecipeMaterial.setType(1);
					inventoryRecipeMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
					inventoryRecipeMaterial.setUseQty(new BigDecimal(useQty));
					inventoryRecipeMaterial.setUnitOfMeasurement(unitOfMeasurement);
					rmList.add(inventoryRecipeMaterial);
				}
			}
			
			inventoryRecipeMaterialService.insertMmList(rmList, modifierId, restaurantId);
			
			return "redirect:/modifierMaterial/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
}
