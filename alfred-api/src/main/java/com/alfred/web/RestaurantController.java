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
import com.alfred.http.ResultCode;
import com.alfred.model.Places;
import com.alfred.model.Printer;
import com.alfred.model.PrinterGroup;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.model.RoundRule;
import com.alfred.model.Tables;
import com.alfred.service.PlacesService;
import com.alfred.service.PrinterGroupService;
import com.alfred.service.PrinterService;
import com.alfred.service.RestaurantService;
import com.alfred.service.RevenueCenterService;
import com.alfred.service.RoundRuleService;
import com.alfred.service.TablesService;
import com.alfred.util.ConfigHelper;
import com.alfred.util.Skip32Util;


@Scope("prototype")
@Controller
@RequestMapping(value = "/restaurant")
public class RestaurantController extends BaseController{
	
	private static Log log = LogFactory.getLog(RestaurantController.class);
	
	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restaurantService;
	
	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;
	
	@Autowired
	@Qualifier("placesService")
	private PlacesService placesService;
	
	@Autowired
	@Qualifier("tablesService")
	private TablesService tablesService;
	
	@Autowired
	@Qualifier("printerService")
	private PrinterService printerService;
	
	
	@Autowired
	@Qualifier("printerGroupService")
	private PrinterGroupService printerGroupService;
	
	@Autowired
	@Qualifier("roundRuleService")
	private RoundRuleService roundRuleService;
	
	/**
	 * 获取餐厅信息
	 * 1、根据餐厅id查询餐厅信息
	 * 2、根据餐厅id查询printer
	 * 3、根据餐厅id查询revenueCenter
	 */
	@RequestMapping(value="/getRestaurantInfo", method =RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRestaurantInfo(HttpServletRequest request) {
		
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");
			
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
			
			Restaurant restaurant = restaurantService.selectByPrimaryKey(restaurantId);
			
			RevenueCenter revenue = new RevenueCenter();
			revenue.setRestaurantId(restaurantId);
			revenue.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<RevenueCenter> revenueList = revenueCenterService.selectByParam(revenue);
			
			Printer printer = new Printer();
			printer.setCompanyId(restaurant.getCompanyId());
			printer.setRestaurantId(restaurantId);
			List<Printer> printerList = printerService.selectByParam(printer);
			PrinterGroup pg = new PrinterGroup();
			pg.setCompanyId(restaurant.getCompanyId());
			pg.setRestaurantId(restaurantId);
			List<PrinterGroup> printerGroupList = printerGroupService.selectByParam(pg);
			
			
			RoundRule roundRule = roundRuleService.selectByCountry(restaurant.getCountry());
			
			resultMap.put("restaurant", restaurant);
			resultMap.put("roundRule", roundRule);
			resultMap.put("revenueList", revenueList);
			resultMap.put("printerList", printerList);
			resultMap.put("printerGroupList", printerGroupList);
			
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}
	

	/**
	 * 1、根据餐厅id查询place
	 * 2、根据餐厅id查询table
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/getPlaceInfo", method =RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getRevenueById(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");
			Integer revenueId = jsonObject.getInt("revenueId");
			
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
			
			Places places = new Places();
			places.setRestaurantId(restaurantId);
			places.setRevenueId(revenueId);
			places.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<Places> placeList = placesService.selectByParam(places);
			
			Tables tables = new Tables();
			tables.setRestaurantId(restaurantId);
			tables.setRevenueId(revenueId);
			tables.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<Tables> tableList = tablesService.selectByParam(tables);
			
			resultMap.put("placeList", placeList);
			resultMap.put("tableList", tableList);
			
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}
	
	
	
	

}
