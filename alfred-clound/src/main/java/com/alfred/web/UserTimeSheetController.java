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

import com.alfred.constant.UserConstant;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.model.UserTimeSheet;
import com.alfred.service.UserService;
import com.alfred.service.UserTimeSheetService;
@Scope("prototype")
@Controller("UserTimeSheetController")
@RequestMapping(value = "/userTimeSheet")
public class UserTimeSheetController {

	private static Log log = LogFactory.getLog(UserTimeSheetController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("userTimeSheetService")
	private UserTimeSheetService userTimeSheetService;	
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@RequestMapping("/queryUserTimeSheet")
	public String queryUserTimeSheet(HttpServletRequest request, Model model) {
		
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			User u = new User();
			u.setCompanyId(user.getCompanyId());
			u.setType(UserConstant.USER_TYPE_CLOUD);
			u.setStatus(UserConstant.USER_STATUS_NORMAL);
			u.setEmpId(restaurantId);
			List<User> userList = userService.selectByEmp(u);
			model.addAttribute("userList", userList);
			return "forward:/pages/reports/user_time_sheet.jsp";
		} catch (Exception e) {
			// TODO: handle exception
			log.error(this,e);
			return null;
		}
		
	
	}
	
	
	
	@RequestMapping("/ajaxUserTimeSheetJson")
	@ResponseBody
	public Map<String, Object> ajaxUserTimeSheetJson(HttpServletRequest request, Model model,	
			@RequestParam(required = false) Integer userId,
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
			map.put("restaurantId", restaurantId);
			if (userId != null && userId != 0) {
				map.put("userId", userId);
			}
			map.put("start", startInt);
			map.put("end", endInt);
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
			total = userTimeSheetService.getUserTimeCount(map);
			List<UserTimeSheet> userTimeSheetList = userTimeSheetService.queryUserTimeList(map);
			result.put("draw", draw);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("data", userTimeSheetList);
			return result;
		} catch (Exception e) {
			log.error(this,e);
			return null;

		}
	
	}
	
	
	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) Integer userId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String userName,
			@RequestParam(required = false) String endTime
			) {
		try {
		Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
		Integer restaurantId = restaurant.getId();
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("restaurantId",restaurantId);	
		if(userId!=null&&userId!=0){
			map.put("userId", userId);
		}
	
		if(!("".equals(startTime))&&startTime!=null){
			map.put("startTime", startTime);
		}
		if(!("".equals(endTime))&&endTime!=null){
			map.put("endTime", endTime);
		}
		List<UserTimeSheet> userTimeSheetList= userTimeSheetService.queryUserTimeList(map);
		//获取用户名称
		HSSFWorkbook wb = userTimeSheetService.exportExcel(userTimeSheetList, startTime, endTime,userName);  
		response.setContentType("application/vnd.ms-excel");  
		response.setHeader("Content-disposition", "attachment;filename=UserTimeSheet.xls");  
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
	
	
	@RequestMapping("exportPdf")
	public void exportPdf(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) Integer userId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String userName
			){
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
		List<UserTimeSheet> userTimeSheetList= userTimeSheetService.queryUserTimeList(map);
        userTimeSheetService.exportPdf(response, userTimeSheetList, startTime, endTime, userName);
			
		} catch (Exception e) {
			log.error(this,e);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			log.error(this,e);
		}  

	}
		
}
