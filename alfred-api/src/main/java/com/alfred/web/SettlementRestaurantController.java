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
import com.alfred.constant.SettlementConstant;
import com.alfred.http.ResultCode;
import com.alfred.model.ItemCategory;
import com.alfred.model.ItemMainCategory;
import com.alfred.model.SettlementRestaurant;
import com.alfred.service.SettlementRestaurantService;
import com.alfred.util.ConfigHelper;
import com.alfred.util.Skip32Util;

@Scope("prototype")
@Controller
@RequestMapping(value = "/settlement")
public class SettlementRestaurantController  extends BaseController {
	
	private static Log log = LogFactory.getLog(SettlementRestaurantController.class);
	@Autowired
	@Qualifier("settlementRestaurantService")
	private SettlementRestaurantService settlementRestaurantService;
	
	
	/**
	 * 根据餐厅id获取银行卡信息
	 * @return
	 */
	@RequestMapping(value="/getSettlement", method =RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getSettlement(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");
			
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
			SettlementRestaurant set=new SettlementRestaurant();
			set.setRestaurantId(restaurantId);
			List<SettlementRestaurant> settlementList=settlementRestaurantService.selectByParam(set);
			
			resultMap.put("cash", CommonStatusConstant.CASH_TYPE);
			resultMap.put("baseMedia", SettlementConstant.getBaseMedia());
			resultMap.put("baseAdjustments", SettlementConstant.getBaseAdjustments());
			resultMap.put("settlementList", settlementList);
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}

	
	
	
	
	

}
