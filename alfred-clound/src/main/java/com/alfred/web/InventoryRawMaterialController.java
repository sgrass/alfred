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
import com.alfred.model.InventoryRawMaterial;
import com.alfred.model.Restaurant;
import com.alfred.service.InventoryRawMaterialService;
import com.alfred.service.InventoryRecipeMaterialService;
import com.alfred.vo.InventoryRawMaterialVO;


@Scope("prototype")
@Controller
@RequestMapping(value = "/material")
public class InventoryRawMaterialController {
	
	private static Log log = LogFactory.getLog(InventoryRawMaterialController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("inventoryRawMaterialService")
	private InventoryRawMaterialService inventoryRawMaterialService;
	
	@Autowired
	@Qualifier("inventoryRecipeMaterialService")
	private InventoryRecipeMaterialService inventoryRecipeMaterialService;
	
	
	@RequestMapping("/queryAll")
	public String queryAll(HttpServletRequest request, @ModelAttribute InventoryRawMaterial inventoryRawMaterial, ModelMap model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			inventoryRawMaterial.setRestaurantId(restaurant.getId());
			inventoryRawMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<InventoryRawMaterial> materialList = inventoryRawMaterialService.selectByParam(inventoryRawMaterial);
			
			model.put("materialList", materialList);
			return "/pages/inventory/material_list.jsp";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute InventoryRawMaterial inventoryRawMaterial, Model model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			inventoryRawMaterial.setRestaurantId(restaurant.getId());
			inventoryRawMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			
			inventoryRawMaterialService.insert(inventoryRawMaterial);
			
			return "redirect:/material/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/updateById")
	public String updateById(HttpServletRequest request, @ModelAttribute InventoryRawMaterial inventoryRawMaterial, Model model) {
		try {
			inventoryRawMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			inventoryRawMaterialService.updateById(inventoryRawMaterial);
			return "redirect:/material/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("/deleteById")
	public String deleteById(@RequestParam Integer id, Model model) {
		try {
			InventoryRawMaterial inventoryRawMaterial = new InventoryRawMaterial();
			inventoryRawMaterial.setId(id);
			inventoryRawMaterial.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
			inventoryRawMaterialService.updateById(inventoryRawMaterial);
			return "redirect:/material/queryAll";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
    /**
     * 验证供应商是否使用
     * @param request
     * @param id
     * @return
     */
	@RequestMapping("/checkCount")
	@ResponseBody
	public Map<String, Object> checkCount(HttpServletRequest request,
			@RequestParam int id) {
		try {
			Restaurant res = (Restaurant) request.getSession().getAttribute(
					"restaurant");

			int count = inventoryRecipeMaterialService.queryUseredMaterial(id,
					res.getId());
			boolean flag = false;

			if (count > 0) {
				flag = true;
			}
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	
	
	
	@RequestMapping("ajaxReportMaterial")
	@ResponseBody
	public Map<String, Object> AjaxReportMaterial(HttpServletRequest request, Model model,
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
			List<InventoryRawMaterialVO> inventoryRawMaterialVOList = null;
			inventoryRawMaterialVOList = inventoryRawMaterialService.selectByParamReoprt(map);
		    total = inventoryRawMaterialService.selectByParamReoprtCount(map);
		
			result.put("draw", draw);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("data", inventoryRawMaterialVOList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(this,e);
			return null;
		}
	}
	
	@RequestMapping("queryMaterial")
	public String queryMaterial(HttpServletRequest request, Model model,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  defaultTime) {
		try {
			return "forward:/pages/inventory/material_reoprt.jsp";
		} catch (Exception e) {
          log.error(this,e);
          return null;
		}

	}
	
	@RequestMapping("exportMaterialExcel")
	public void exportMaterialExcel(HttpServletRequest request, HttpServletResponse response, Model model,
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
		
		List<InventoryRawMaterialVO> inventoryRawMaterialVOList = null;
		inventoryRawMaterialVOList = inventoryRawMaterialService.selectByParamReoprt(map);
	
		
		//获取用户名称
		
		HSSFWorkbook wb=null;
		wb = inventoryRawMaterialService.exportReportExcel(inventoryRawMaterialVOList,startTime,endTime);
		response.setContentType("application/vnd.ms-excel");  
		response.setHeader("Content-disposition", "attachment;filename=InventoryRawMaterialReport.xls");  
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
	
	@RequestMapping("exportMaterialPdf")
	public void exportMaterialPdf(HttpServletRequest request, HttpServletResponse response, Model model,
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
		
	
		List<InventoryRawMaterialVO> inventoryRawMaterialVOList = null;
		inventoryRawMaterialVOList = inventoryRawMaterialService.selectByParamReoprt(map);
	
			try {
				inventoryRawMaterialService.exportReportPdf(response, inventoryRawMaterialVOList,startTime,endTime);
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
