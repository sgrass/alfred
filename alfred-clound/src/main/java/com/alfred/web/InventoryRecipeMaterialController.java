package com.alfred.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.ItemConstant;
import com.alfred.model.HappyHour;
import com.alfred.model.InventoryRawMaterial;
import com.alfred.model.InventoryRecipeMaterial;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemMainCategory;
import com.alfred.model.Restaurant;
import com.alfred.pagination.Pagination;
import com.alfred.service.InventoryRawMaterialService;
import com.alfred.service.InventoryRecipeMaterialService;
import com.alfred.service.ItemDetailService;
import com.alfred.service.ItemMainCategoryService;
import com.alfred.vo.ItemMainCategoryVO;

@Scope("prototype")
@Controller
@RequestMapping(value = "/recipeMaterial")
public class InventoryRecipeMaterialController {
	
	private static Log log = LogFactory.getLog(InventoryRecipeMaterialController.class);
	
	@Autowired
	@Qualifier("itemMainCategoryService")
	private ItemMainCategoryService itemMainCategoryService;
	
	@Autowired
	@Qualifier("itemDetailService")
	private ItemDetailService itemDetailService;
	
	@Autowired
	@Qualifier("inventoryRawMaterialService")
	private InventoryRawMaterialService inventoryRawMaterialService;
	
	@Autowired
	@Qualifier("inventoryRecipeMaterialService")
	private InventoryRecipeMaterialService inventoryRecipeMaterialService;
	
	@RequestMapping("/queryAll")
	public String queryAll(HttpServletRequest request, 
			@RequestParam(required = false) Integer itemMainCategoryId, @RequestParam(required = false) Integer currPage,
			@RequestParam(required = false) Integer itemCategoryId, Model model) {
		try {
			
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			
			ItemMainCategory itemMainCategory = new ItemMainCategory();
			itemMainCategory.setRestaurantId(restaurantId);
			List<ItemMainCategoryVO> categoryList = itemMainCategoryService.selectByRestaurant(restaurantId);
			
			ItemDetail itemDetail = new ItemDetail();
			itemDetail.setRestaurantId(restaurantId);
			itemDetail.setIsActive(CommonStatusConstant.IS_ACTIVE_DISABLE);
			itemDetail.setItemType(ItemConstant.ITEM_TYPE_TEMPLATE);
			if (itemMainCategoryId != null && itemMainCategoryId > 0) {
				itemDetail.setItemMainCategoryId(itemMainCategoryId);	
			}
			if (itemCategoryId != null && itemCategoryId > 0) {
				itemDetail.setItemCategoryId(itemCategoryId);
			}
			if (currPage == null) {
				currPage = 0;
			}
			Pagination page = new Pagination();
			page.setItems(itemDetailService.selectCountByParam(itemDetail));
			currPage = currPage < 1 ? 1 : currPage;
			page.setPage(currPage);
			List<ItemDetail> itemList = itemDetailService.selectPageByParam(itemDetail,page);
			
			InventoryRawMaterial inventoryRawMaterial = new InventoryRawMaterial();
			inventoryRawMaterial.setRestaurantId(restaurant.getId());
			inventoryRawMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<InventoryRawMaterial> materialList = inventoryRawMaterialService.selectByParam(inventoryRawMaterial);
			
			InventoryRecipeMaterial inventoryRecipeMaterial = new InventoryRecipeMaterial();
			inventoryRecipeMaterial.setRestaurantId(restaurantId);
			inventoryRecipeMaterial.setType(0);
			inventoryRecipeMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<InventoryRecipeMaterial> rmList = inventoryRecipeMaterialService.selectByParam(inventoryRecipeMaterial);
			
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("itemList", itemList);
			model.addAttribute("materialList", materialList);
			model.addAttribute("rmList", rmList);
			
			model.addAttribute("itemMainCategoryId",itemMainCategoryId);
			model.addAttribute("itemCategoryId",itemCategoryId);
			
			model.addAttribute("currPage", currPage);
			model.addAttribute("rowCount", page.getItems());
			model.addAttribute("startRow", page.getStartRow());
			model.addAttribute("endRow", page.getEndRow());
			model.addAttribute("pageSize", page.getPages());
			return "/pages/inventory/recipe_material_list.jsp";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute ItemDetail itemDetail, @RequestParam(required = false) Integer... checkedIds) {
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
					inventoryRecipeMaterial.setItemMainCategoryId(itemDetail.getItemMainCategoryId());
					inventoryRecipeMaterial.setItemCategoryId(itemDetail.getItemCategoryId());
					inventoryRecipeMaterial.setItemId(itemDetail.getId());
					inventoryRecipeMaterial.setMaterialId(Integer.parseInt(materialId));
					inventoryRecipeMaterial.setMaterialName(materialName);
					inventoryRecipeMaterial.setType(0);
					inventoryRecipeMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
					inventoryRecipeMaterial.setUseQty(new BigDecimal(useQty));
					inventoryRecipeMaterial.setUnitOfMeasurement(unitOfMeasurement);
					rmList.add(inventoryRecipeMaterial);
				}
			}
			
			inventoryRecipeMaterialService.insertRmList(rmList, itemDetail.getId(), restaurantId);
			
			return "redirect:/recipeMaterial/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
}
