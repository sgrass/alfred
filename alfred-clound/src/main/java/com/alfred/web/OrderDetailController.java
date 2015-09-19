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
import com.alfred.service.OrderDetailService;
import com.alfred.service.RevenueCenterService;
import com.alfred.service.UserService;
import com.alfred.vo.OrderReoprtVO;

@Scope("prototype")
@Controller
@RequestMapping(value = "orderDetail")
public class OrderDetailController {
	private static Log log = LogFactory.getLog(OrderDetailController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("orderDetailService")
	private OrderDetailService orderDetailService;

	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@RequestMapping("queryOrderDetail")
	public String queryOrderDetail(HttpServletRequest request, Model model) {
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
			List<User> userList = userService.selectByEmp(u);
			model.addAttribute("userList", userList);
			model.addAttribute("revenueCenterList", revenueCenterList);
			return "forward:/pages/reports/orderlist.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}

	}

	@RequestMapping("ajaxOrderDetailJson")
	@ResponseBody
	public Map<String, Object> ajaxOrderDetailJson(HttpServletRequest request, Model model,
			@RequestParam(required = false) Integer userId, @RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {
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
			map.put("restaurantId", restaurantId);
			map.put("start", startInt);
			map.put("end", endInt);
			if (userId != null && userId != 0) {
				map.put("userId", userId);
			}
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
			List<OrderReoprtVO> orderReoprtVOList = orderDetailService.queryOrderInfo(map);
			total = orderDetailService.getOrderInfoCount(map);
			result.put("draw", draw);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("data", orderReoprtVOList);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}

	}

	@RequestMapping("exportOrderList")
	public void exportOrderList(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) Integer userId, @RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime, @RequestParam(required = false) String userName,
			@RequestParam(required = false) String endTime, @RequestParam(required = false) String revenueName) {

		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restaurantId", restaurantId);
			if (userId != null && userId != 0) {
				map.put("userId", userId);
			}
			if (revenueId != null && revenueId != 0) {
				map.put("revenueId", revenueId);
			}
			if (!("".equals(startTime)) && startTime != null) {
				map.put("startTime", startTime);
			}
			if (!("".equals(endTime)) && endTime != null) {
				map.put("endTime", endTime);
			}
			List<OrderReoprtVO> orderReoprtVOList = orderDetailService.queryOrderInfoExcel(map);
			// 获取用户名称
			HSSFWorkbook wb = orderDetailService.export(orderReoprtVOList, startTime, endTime, revenueName, userName);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=OrderList.xls");
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
	public void exportOrderListPdf(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) Integer userId, @RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime,
			@RequestParam(required = false) String userName, @RequestParam(required = false) String revenueName) {

		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restaurantId", restaurantId);
			if (revenueId != null && revenueId != 0) {
				map.put("reveuneId", revenueId);
			}
			if (!("".equals(startTime)) && startTime != null) {
				map.put("startTime", startTime);
			}
			if (!("".equals(endTime)) && endTime != null) {
				map.put("endTime", endTime);
			}
			List<OrderReoprtVO> orderReoprtVOList = orderDetailService.queryOrderInfoExcel(map);
			orderDetailService.orderInfoPdf(response, orderReoprtVOList, startTime, endTime, revenueName, userName);
		} catch (Exception e) {
			log.error(this, e);
		} catch (Throwable e) {
			log.error(this, e);
		}
	}

}
