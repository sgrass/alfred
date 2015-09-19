package com.alfred.web;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.InventoryMaterialStock;
import com.alfred.model.InventoryRawMaterial;
import com.alfred.model.InventorySupplier;
import com.alfred.model.Restaurant;
import com.alfred.service.InventoryMaterialStockService;
import com.alfred.service.InventoryRawMaterialService;
import com.alfred.service.InventorySupplierService;


@Scope("prototype")
@Controller
@RequestMapping(value = "/materialStock")
public class InventoryMaterialStockController {
	
	private static Log log = LogFactory.getLog(InventoryMaterialStockController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("inventoryMaterialStockService")
	private InventoryMaterialStockService inventoryMaterialStockService;
	
	@Autowired
	@Qualifier("inventoryRawMaterialService")
	private InventoryRawMaterialService inventoryRawMaterialService;
	
	@Autowired
	@Qualifier("inventorySupplierService")
	private InventorySupplierService inventorySupplierService;
	
	
	@RequestMapping("/queryAll")
	public String queryAll(HttpServletRequest request, @ModelAttribute InventoryMaterialStock inventoryMaterialStock, ModelMap model) {
		try {
			
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			inventoryMaterialStock.setRestaurantId(restaurant.getId());
			
			List<InventoryMaterialStock> inventoryMaterialStockList = inventoryMaterialStockService.selectByParam(inventoryMaterialStock);
			
			InventoryRawMaterial inventoryRawMaterial=new InventoryRawMaterial();
			inventoryRawMaterial.setRestaurantId(restaurant.getId());
			inventoryRawMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			
			List<InventoryRawMaterial> materialList = inventoryRawMaterialService.selectByParam(inventoryRawMaterial);
			InventorySupplier inventorySupplier=new InventorySupplier();
			inventorySupplier.setRestaurantId(restaurant.getId());
			inventorySupplier.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			
			List<InventorySupplier> supplierList = inventorySupplierService.selectByParam(inventorySupplier);
			model.put("inventoryMaterialStockList", inventoryMaterialStockList);
			model.put("materialList", materialList);
			model.addAttribute("supplierList", supplierList);
			return "/pages/inventory/material_stock_list.jsp";
		
		
		
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute InventoryMaterialStock inventoryMaterialStock, Model model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			inventoryMaterialStock.setRestaurantId(restaurant.getId());			
			inventoryMaterialStockService.insert(inventoryMaterialStock);
			return "redirect:/materialStock/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/updateById")
	public String updateById(HttpServletRequest request, @ModelAttribute InventoryMaterialStock inventoryMaterialStock, Model model) {
		try {
			inventoryMaterialStockService.updateById(inventoryMaterialStock);
			return "redirect:/materialStock/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/deleteById")
	public String deleteById(@RequestParam Integer id, Model model) {
		try {
			InventoryMaterialStock inventoryMaterialStock = new InventoryMaterialStock();
			inventoryMaterialStock.setId(id);
			inventoryMaterialStockService.deleteById(inventoryMaterialStock);
			
			return "redirect:/materialStock/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	
	@RequestMapping("ajaxReportMaterialReceipt")
	@ResponseBody
	public Map<String, Object> AjaxReportMaterialReceipt(HttpServletRequest request, Model model,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime
			) {
		
		try {
			String start = request.getParameter("iDisplayStart");
			String end = request.getParameter("iDisplayLength");
			String draw = request.getParameter("sEcho");
			int startInt = Integer.parseInt(start);
			int endInt = Integer.parseInt(end);
			int total = 0;
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Date dateEndTime = c.getTime();
			c.add(Calendar.DAY_OF_YEAR, -6);
			Date dateStartTime = c.getTime();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restaurantId", restaurantId);
			map.put("start", startInt);
			map.put("end", endInt);
			if (!("".equals(startTime)) && startTime != null) {
				map.put("startTime", startTime);
			} else {
				startTime = formatter.format(dateStartTime);
				map.put("startTime", startTime);
			}
			if (!("".equals(endTime)) && endTime != null) {
				map.put("endTime", endTime);
			} else {
				endTime = formatter.format(dateEndTime);
				map.put("endTime", endTime);
			}
			List<InventoryMaterialStock> inventoryMaterialStockList = null;
			inventoryMaterialStockList = inventoryMaterialStockService.selectByParamReoprt(map);
		    total = inventoryMaterialStockService.selectByParamReoprtCount(map);
		
			result.put("draw", draw);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("data", inventoryMaterialStockList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("queryMaterialReceipt")
	public String queryMaterialReceipt(HttpServletRequest request, Model model,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  defaultTime) {
		try {
			return "forward:/pages/inventory/receipt_reoprt.jsp";
		} catch (Exception e) {
          log.error(this,e);
          return null;
		}

	}
	
	@RequestMapping("exportReceiptExcel")
	public void exportReceiptExcel(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		
		try {
			
		Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
		Integer restaurantId = restaurant.getId();
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("restaurantId",restaurantId);	
	
		if(!("".equals(startTime))&&startTime!=null){
			map.put("startTime", startTime);
		}
		if(!("".equals(endTime))&&endTime!=null){
			map.put("endTime", endTime);
		}
		
		List<InventoryMaterialStock> inventoryMaterialStockList = null;
		inventoryMaterialStockList = inventoryMaterialStockService.selectByParamReoprt(map);
		
		//获取用户名称
		
		HSSFWorkbook wb=null;
		wb = inventoryMaterialStockService.exportReportExcel(inventoryMaterialStockList,startTime,endTime);
		response.setContentType("application/vnd.ms-excel");  
		response.setHeader("Content-disposition", "attachment;filename=ReoprtReceipt.xls");  
		OutputStream ouputStream;
		ouputStream = response.getOutputStream();
	    wb.write(ouputStream);  
	    ouputStream.flush();  
	    ouputStream.close();  
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(this,e);
		}  
	}
	
	@RequestMapping("exportReceiptPdf")
	public void exportReceiptPdf(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		try {
			
		Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
		Integer restaurantId = restaurant.getId();
	
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("restaurantId",restaurantId);	
	
		if(!("".equals(startTime))&&startTime!=null){
			map.put("startTime", startTime);
		}
		if(!("".equals(endTime))&&endTime!=null){
			map.put("endTime", endTime);
		}
		
	
		List<InventoryMaterialStock> inventoryMaterialStockList = null;
		inventoryMaterialStockList = inventoryMaterialStockService.selectByParamReoprt(map);
		
	
			try {
				inventoryMaterialStockService.exportReportPdf(response, inventoryMaterialStockList,startTime,endTime);
			} catch (Throwable e) {
				// TODO Auto-generated catch block
				log.error(this,e);
			}
		} catch (Exception e) {
			log.error(this,e);
			// TODO: handle exception
		}  
	}
	
}
