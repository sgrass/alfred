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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.InventorySupplier;
import com.alfred.model.Restaurant;
import com.alfred.service.InventorySupplierService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/supplier")
public class InventorySupplierController {
	
	private static Log log = LogFactory.getLog(InventorySupplierController.class);
	
	@Autowired
	@Qualifier("inventorySupplierService")
	private InventorySupplierService inventorySupplierService;
	@RequestMapping("/queryAll")
	public String queryAll(HttpServletRequest request, @ModelAttribute InventorySupplier inventorySupplier, Model model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			inventorySupplier.setRestaurantId(restaurant.getId());
			inventorySupplier.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<InventorySupplier> supplierList = inventorySupplierService.selectByParam(inventorySupplier);
			model.addAttribute("supplierList", supplierList);
			return "/pages/inventory/supplier_list.jsp";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute InventorySupplier inventorySupplier, Model model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			inventorySupplier.setRestaurantId(restaurant.getId());
			inventorySupplier.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			inventorySupplierService.insert(inventorySupplier);
			return  "redirect:/supplier/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/updateById")
	public String updateById(HttpServletRequest request, @ModelAttribute InventorySupplier inventorySupplier, Model model) {
		try {
			inventorySupplier.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			inventorySupplierService.updateById(inventorySupplier);
			return "redirect:/supplier/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/deleteById")
	public String deleteById(@RequestParam Integer id, Model model) {
		try {
			InventorySupplier inventorySupplier = new InventorySupplier();
			inventorySupplier.setId(id);
			inventorySupplier.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
			inventorySupplierService.updateById(inventorySupplier);
			return  "redirect:/supplier/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
}
