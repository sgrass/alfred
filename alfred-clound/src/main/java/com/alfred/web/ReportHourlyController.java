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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.ReportHourly;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.service.ReportHourlyService;
import com.alfred.service.RevenueCenterService;
@Scope("prototype")
@Controller("ReportHourlyController")
@RequestMapping(value = "/reportHourly")
public class ReportHourlyController {
	private static Log log = LogFactory.getLog(ReportHourlyController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("reportHourlyService")
	private ReportHourlyService reportHourlyService;
	
	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;	
	
	
	@RequestMapping("ajaxReportHourly")
	@ResponseBody
	public Map<String, Object> AjaxReportHourly(HttpServletRequest request, Model model,	
			@RequestParam(required = false) Integer revenueId,
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
			Restaurant restaurant = (Restaurant) request.getSession()
					.getAttribute("restaurant");
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
			List<ReportHourly> reportHourlyList = null;
			if (revenueId == 0) {
				reportHourlyList = reportHourlyService
						.queryReportHourlyRevenueAll(map);
				total = reportHourlyService.queryReportHourlyAllCount(map);

			} else {
				map.put("revenueId", revenueId);
				reportHourlyList = reportHourlyService.queryReportHourly(map);
				total = reportHourlyService.queryReportHourlyCount(map);
			}
			result.put("draw", draw);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("data", reportHourlyList);
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			log.error(this,e);
			return null;
		}
	}
	
	
	@RequestMapping("queryReportHourly")
	public String queryReportHourly(HttpServletRequest request, Model model,	
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  defaultTime,
			@RequestParam(required = false) String  selectLiText) {
		
		try {
			Restaurant restaurant = (Restaurant) request.getSession()
					.getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			//获取revenueCenter名称
			RevenueCenter revenueCenter = new RevenueCenter();
			revenueCenter.setRestaurantId(restaurantId);
			revenueCenter.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<RevenueCenter> revenueCenterList = revenueCenterService.selectByParam(revenueCenter);
		
			model.addAttribute("revenueCenterList", revenueCenterList);
			return "forward:/pages/reports/hourly.jsp";
		} catch (Exception e) {
          log.error(this,e);
          return null;
		}

	}
	
	@RequestMapping("exportHouryExcel")
	public void exportHouryExcel(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  revenueName) {
		
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
		
		List<ReportHourly> reportHourlyList=null;
		if(revenueId!=null&&revenueId!=0){
			map.put("reveuneId", revenueId);
			reportHourlyList=reportHourlyService.queryReportHourly(map);
			
		}else{
			reportHourlyList=reportHourlyService.queryReportHourlyRevenueAll(map);
		

		}
		
		//获取用户名称
		
		HSSFWorkbook wb=null;
		wb = reportHourlyService.exportReportHourlyExcel(reportHourlyList,startTime,endTime,revenueName);
		response.setContentType("application/vnd.ms-excel");  
		response.setHeader("Content-disposition", "attachment;filename=ReportHourly.xls");  
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
	
	@RequestMapping("exportHouryPdf")
	public void exportHouryPdf(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  revenueName) {
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
		
		List<ReportHourly> reportHourlyList=null;
		if(revenueId!=null&&revenueId!=0){
			map.put("reveuneId", revenueId);
			reportHourlyList=reportHourlyService.queryReportHourly(map);
		}else{
			reportHourlyList=reportHourlyService.queryReportHourlyRevenueAll(map);

		}

			try {
				reportHourlyService.exportReportHourlyPdf(response, reportHourlyList,startTime, endTime, revenueName);
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
