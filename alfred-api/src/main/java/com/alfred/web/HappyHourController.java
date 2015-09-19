package com.alfred.web;

import java.util.HashMap;
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
import com.alfred.model.HappyHour;
import com.alfred.model.HappyHourWeek;
import com.alfred.model.ItemHappyHour;
import com.alfred.service.HappyHourService;
import com.alfred.service.HappyHourWeekService;
import com.alfred.service.ItemHappyHourService;
import com.alfred.util.ConfigHelper;
import com.alfred.util.Skip32Util;
@Scope("prototype")
@Controller
@RequestMapping(value = "/happyHour")
public class HappyHourController extends BaseController{
	@Autowired
	@Qualifier("happyHourService")
	private HappyHourService happyHourService;
	
	@Autowired
	@Qualifier("itemHappyHourService")
	private ItemHappyHourService itemHappyHourService;
	
	@Autowired
	@Qualifier("happyHourWeekService")
	private HappyHourWeekService happyHourWeekService;
	private static Log log = LogFactory.getLog(ItemDetailController.class);
	
	/**
	 * 根据餐厅id获取happyHour
	 * @return
	 */
	@RequestMapping(value="/getHappyHour", method =RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getHappyHour(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
			List<ItemHappyHour> itemHappyHourList=itemHappyHourService.selectByRestaurantId(restaurantId);
			
			HappyHour happyHour=new HappyHour();
			happyHour.setRestaurantId(restaurantId);
			happyHour.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<HappyHour> happyHourList=happyHourService.selectByParam(happyHour);
			
			HashMap<String, Integer> map=new HashMap<String, Integer>();
			map.put("restaurantId", restaurantId);
			map.put("isActive", CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<HappyHourWeek> happyHourWeekList=happyHourWeekService.selectWeekByRestaurantId(map);
			
			resultMap.put("itemHappyHourList", itemHappyHourList);
			resultMap.put("happyHourWeekList", happyHourWeekList);
			resultMap.put("happyHourList", happyHourList);
			
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}
	
	
	
	
	
	
	
	
	
}
