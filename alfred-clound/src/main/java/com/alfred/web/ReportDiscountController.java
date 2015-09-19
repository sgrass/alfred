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
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.UserConstant;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.model.User;
import com.alfred.service.OrderService;
import com.alfred.service.RevenueCenterService;
import com.alfred.vo.DiscountVO;

@Scope("prototype")
@Controller("ReportDiscountController")
@RequestMapping(value = "/reportDiscount")
public class ReportDiscountController {
	
	
	private static Log log = LogFactory.getLog(ReportDiscountController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;
	
	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;
	
	@RequestMapping("queryDiscountReport")
	public String queryDiscountReport(HttpServletRequest request, Model model) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			// 获取revenueCenter名称
			RevenueCenter revenueCenter = new RevenueCenter();
			revenueCenter.setRestaurantId(restaurantId);
			revenueCenter.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<RevenueCenter> revenueCenterList = revenueCenterService.selectByParam(revenueCenter);
			User u = new User();
			u.setCompanyId(user.getCompanyId());
			u.setType(UserConstant.USER_TYPE_CLOUD);
			u.setStatus(UserConstant.USER_STATUS_NORMAL);
			u.setEmpId(restaurantId);
			model.addAttribute("revenueCenterList", revenueCenterList);
			return "forward:/pages/reports/discount_report.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	
	
	}
	
	
	@RequestMapping("ajaxOrderJson")
	@ResponseBody
	public Map<String, Object> ajaxOrderJson(HttpServletRequest request, Model model,
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			String start = request.getParameter("iDisplayStart");
			String end = request.getParameter("iDisplayLength");
			String draw = request.getParameter("sEcho");
			int startInt = Integer.parseInt(start);
			int endInt = Integer.parseInt(end);
			int total = 0;
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restId", restaurantId);
			map.put("start", startInt);
			map.put("end", endInt);
			
			if (revenueId != null && revenueId != 0) {
				map.put("revenueId", revenueId);
			}
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Date dateEndTime = c.getTime();
			c.add(Calendar.DAY_OF_YEAR, -6);
			Date dateStartTime = c.getTime();
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
			List<DiscountVO> orderReoprtDiscountVOList = orderService.queryReportDiscount(map);
			total = 0;//orderService.queryReportDiscount(map);
			result.put("draw", draw);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("data", orderReoprtDiscountVOList);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}

	}

	@RequestMapping("exportOrderList")
	public void exportOrderList(HttpServletRequest request,
			HttpServletResponse response, Model model,
	        @RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime, 
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String revenueName) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restId", restaurantId);
			
			if (revenueId != null && revenueId != 0) {
				map.put("revenueId", revenueId);
			}
			if (!("".equals(startTime)) && startTime != null) {
				map.put("startTime", startTime);
			}
			if (!("".equals(endTime)) && endTime != null) {
				map.put("endTime", endTime);
			}
			List<DiscountVO> orderDiscountVOList = orderService.queryReportDiscount(map);
			// 获取用户名称
			HSSFWorkbook wb = orderService.exportReportDiscountExcel(orderDiscountVOList, startTime, endTime, revenueName);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=ReportDiscount.xls");
			OutputStream ouputStream;
			ouputStream = response.getOutputStream();
			wb.write(ouputStream);
			ouputStream.flush();
			ouputStream.close();
		} catch (Exception e) {
			log.error(this, e);
		}

	}

	@RequestMapping("exportOrderListPdf")
	public void exportOrderListPdf(HttpServletRequest request,
			HttpServletResponse response, Model model,
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String revenueName) {

		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restId", restaurantId);
			if (revenueId != null && revenueId != 0) {
				map.put("revenueId", revenueId);
			}
			if (!("".equals(startTime)) && startTime != null) {
				map.put("startTime", startTime);
			}
			if (!("".equals(endTime)) && endTime != null) {
				map.put("endTime", endTime);
			}
			List<DiscountVO> orderDiscountVOList = orderService.queryReportDiscount(map);
			orderService.exportReportDiscountPdf(response, orderDiscountVOList, startTime, endTime, revenueName);
		} catch (Exception e) {
			log.error(this, e);
		} catch (Throwable e) {
			log.error(this, e);
		}
	}
	
	

}
