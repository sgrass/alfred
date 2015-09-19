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
import com.alfred.model.Tax;
import com.alfred.model.TaxCategory;
import com.alfred.service.TaxService;
import com.alfred.util.ConfigHelper;
import com.alfred.util.Skip32Util;

@Scope("prototype")
@Controller
@RequestMapping(value = "/tax")
public class TaxController extends BaseController{
	private static Log log = LogFactory.getLog(TaxController.class);

	@Autowired
	@Qualifier("taxService")
	private TaxService taxService;
	

	/**
	 * 获取餐厅税收数据
	 * @return
	 */
	@RequestMapping(value="/getTax", method =RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getTax(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");
			
			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");
		
			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));
	        //税收名称列表
			Tax tax=new Tax();
			tax.setRestaurantId(restaurantId);
			tax.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			
			//税收规则列表
			TaxCategory taxCategory=new TaxCategory();
			taxCategory.setRestaurantId(restaurantId);
			taxCategory.setStatus(CommonStatusConstant.IS_ACTIVE_DISABLE);
			
			
			List<Tax> taxList=taxService.selectByParam(tax);
			
			List<TaxCategory> taxCategoryList=taxService.selectByRestaurantId(taxCategory);
			
			resultMap.put("taxList", taxList);
			resultMap.put("taxCategoryList", taxCategoryList);
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this,e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}
	
	
	
	

}
