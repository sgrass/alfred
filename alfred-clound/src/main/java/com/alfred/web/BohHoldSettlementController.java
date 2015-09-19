package com.alfred.web;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.service.BohHoldSettlementService;
import com.alfred.service.RevenueCenterService;
import com.alfred.util.DateUtil;
import com.alfred.vo.BohHoldSettlementVO;
@Scope("prototype")
@Controller
@RequestMapping(value = "/report")
public class BohHoldSettlementController {

	private static Log log = LogFactory.getLog(BohHoldSettlementController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;	
	
	@Autowired
	@Qualifier("bohHoldSettlementService")
	private BohHoldSettlementService bohHoldSettlementService;

	@RequestMapping("/bohSettlement")
	public String queryCategory(HttpServletRequest request, Model model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			RevenueCenter revenueCenter=new RevenueCenter();
			revenueCenter.setRestaurantId(restaurantId);
			revenueCenter.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<RevenueCenter> revenueCenterList=revenueCenterService.selectByParam(revenueCenter);
			model.addAttribute("revenueCenterList", revenueCenterList);
			return "/pages/reports/boh_hold_settlement.jsp";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	
	
	
	@RequestMapping("/ajaxBohSettlementJson")
	@ResponseBody
	public Map<String, Object> ajaxBohSettlementJson(HttpServletRequest request, 
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			    Date curDate=new Date();
			if (StringUtils.isBlank(startTime)) {
				startTime = DateUtil.getDate(curDate, -6, DateUtil.TIME_BETTEW_FMT)+ " 00:00:00";
			} else {
				startTime+=" 00:00:00";
			}
			if (StringUtils.isBlank(endTime)) {
				endTime = DateUtil.getDate(curDate, 0, DateUtil.TIME_BETTEW_FMT)+" 23:59:59";
			} else { 
				endTime+=" 23:59:59";
			}
			
			String start=request.getParameter("iDisplayStart");
			String end=request.getParameter("iDisplayLength");
			String draw=request.getParameter("sEcho");
			int startInt=Integer.parseInt(start);
			int endInt=Integer.parseInt(end);
			int total=0;	

			List<BohHoldSettlementVO> bohHoldList = bohHoldSettlementService.selectByDaysDue(restaurantId, revenueId, startTime, endTime,startInt,endInt);
			total=bohHoldSettlementService.getDaysDueCount(restaurantId, revenueId, startTime, endTime);

			result.put("draw", draw);
			result.put("recordsTotal",total);
			result.put("recordsFiltered", total);
			result.put("data", bohHoldList);
			return result;
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}

	
	@RequestMapping("/exportBohSett")
	public void exportBohSett(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  defaultTime,@RequestParam(required = false) String  revenueName,Model model) {
		
		OutputStream outs = null;
		
		try {
			if (StringUtils.isBlank(startTime)) {
				startTime = null;
			} else {
				startTime+=" 00:00:00";
			}
			if (StringUtils.isBlank(endTime)) {
				endTime = null;
			} else {
				endTime+=" 23:59:59";
			}
			
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			
			HSSFWorkbook wb = bohHoldSettlementService.listToExcel(restaurantId, revenueId, startTime, endTime,revenueName);
			
			response.setContentType("application/csv;charset=GBK");
			response.setContentType("application/x-download"); 
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=BohHoldReport.xls");
			
			outs = response.getOutputStream();
			wb.write(outs);
			
		} catch(Exception e) {
			log.error(this, e);
		} finally {
			try {
				outs.flush();
				outs.close();
			} catch (Exception e2) {
				log.error(this, e2);
			}
		}
	}
	
	
	

	@RequestMapping("/exportBohSettPdf")
	public void exportBohSettPdf(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  defaultTime,@RequestParam(required = false) String  revenueName,Model model) throws Throwable {
		
		try {
			if (StringUtils.isBlank(startTime)) {
				startTime = null;
			} else {
				startTime+=" 00:00:00";
			}
			if (StringUtils.isBlank(endTime)) {
				endTime = null;
			} else {
				endTime+=" 23:59:59";
			}
			
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			
			 bohHoldSettlementService.listToPdf(response, restaurantId, revenueId, startTime, endTime, revenueName);
			
			

		} catch(Exception e) {
			log.error(this, e);
		}
	}
	
	
	//exportTransactionPdf
	
	
	
	
	
}
