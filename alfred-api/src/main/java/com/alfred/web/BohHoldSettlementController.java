package com.alfred.web;

import java.util.Date;
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

import com.alfred.constant.BohConstant;
import com.alfred.http.ResultCode;
import com.alfred.model.BohHoldSettlement;
import com.alfred.service.BohHoldSettlementService;
import com.alfred.util.ConfigHelper;
import com.alfred.util.JsonUtil;
import com.alfred.util.Skip32Util;

@Scope("prototype")
@Controller
@RequestMapping(value = "/boh")
public class BohHoldSettlementController extends BaseController {
	private static Log log = LogFactory.getLog(BohHoldSettlementController.class);

	@Autowired
	@Qualifier("bohHoldSettlementService")
	private BohHoldSettlementService bohHoldSettlementService;

	@RequestMapping(value = "/getBohHoldUnpaid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getBohHoldUnpaid(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");

			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}

			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String restaurantKey = jsonObject.getString("restaurantKey");

			Integer restaurantId = (int) Skip32Util.skip32decrypt(restaurantKey, ConfigHelper.getString("skip32.key"));

			BohHoldSettlement param = new BohHoldSettlement();
			param.setRestaurantId(restaurantId);
			param.setStatus(BohConstant.BOH_UNPAID);
			List<BohHoldSettlement> bohUnpaidList = bohHoldSettlementService.selectByParam(param);

			resultMap.put("bohUnpaidList", bohUnpaidList);
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this, e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}

	@RequestMapping(value = "/updateBohHoldPaid", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> updateBohHoldPaid(HttpServletRequest request) {
		try {
			String jsonStr = (String) request.getAttribute("jsonStr");

			if (StringUtils.isBlank(jsonStr)) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			String bohStr = jsonObject.getString("bohHoldSettlement");
			BohHoldSettlement reqParam = JsonUtil.toBean(bohStr, BohHoldSettlement.class, null);
			if (reqParam == null) {
				resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
				return resultMap;
			}

			BohHoldSettlement boh = bohHoldSettlementService.selectByPrimaryKey(reqParam.getId());
			if (boh == null) {
				resultMap.put(ResultCode.resultKey, ResultCode.QUERY_EMPTY);
				return resultMap;
			}

			if (!reqParam.getRestaurantId().equals(boh.getRestaurantId()) || !reqParam.getRevenueId().equals(boh.getRevenueId())
					|| !reqParam.getPaymentId().equals(boh.getPaymentId()) || !reqParam.getOrderId().equals(boh.getOrderId())
					|| reqParam.getPaymentType() == null || reqParam.getPaymentType() <= 0) {

				resultMap.put(ResultCode.resultKey, ResultCode.JSON_DATA_ERROR);
				return resultMap;
			}

			boh.setPaidDate(new Date());
			boh.setStatus(BohConstant.BOH_PAID);
			boh.setPaymentType(reqParam.getPaymentType());
			boh.setSysUpdateTime(new Date());
			int resultRow = bohHoldSettlementService.updatePaid(boh);
			if (resultRow < 0) {
				resultMap.put(ResultCode.resultKey, ResultCode.UPDATE_FAILED);
				return resultMap;
			}
			resultMap.put(ResultCode.resultKey, ResultCode.SUCCESS);
			return resultMap;
		} catch (Exception e) {
			log.error(this, e);
			resultMap.put(ResultCode.resultKey, ResultCode.UNKNOW_ERROR);
			return resultMap;
		}
	}

}
