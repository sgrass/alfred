package com.alfred.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.HappyHour;
import com.alfred.model.HappyHourWeek;
import com.alfred.model.ItemMainCategory;
import com.alfred.model.Restaurant;
import com.alfred.service.HappyHourService;
import com.alfred.service.ItemDetailService;
import com.alfred.service.ItemMainCategoryService;
import com.alfred.util.DateUtil;
import com.alfred.util.PushMsgUtil;
import com.alfred.vo.HappyHourVO;
import com.alfred.vo.ItemDetailVO;

@Scope("prototype")
@Controller
@RequestMapping(value = "/happyHour")
public class HappyHourController {

	private static Log log = LogFactory.getLog(HappyHourController.class);

	@Autowired
	@Qualifier("happyHourService")
	private HappyHourService happyHourService;
	
	@Autowired
	@Qualifier("itemMainCategoryService")
	private ItemMainCategoryService itemMainCategoryService;
	
	@Autowired
	@Qualifier("itemDetailService")
	private ItemDetailService itemDetailService;
	

	@RequestMapping("/queryAll")
	public String queryAll(HttpServletRequest request, Model model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			
			String weeks[] = new String[]{"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
			
			List<HappyHourVO> happyHourList = happyHourService.selectHappyHourAll(restaurantId);
			
			ItemMainCategory itemMainCategory = new ItemMainCategory();
			itemMainCategory.setRestaurantId(restaurantId);
			itemMainCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
//		List<ItemMainCategory> mainCateList = itemMainCategoryService.selectByParam(itemMainCategory);
			
			Map<Integer, ItemMainCategory> mainCateMap = new HashMap<Integer, ItemMainCategory>();
			
			List<ItemDetailVO> itemList = itemDetailService.selectItemName(restaurantId);
			
			Map<String, List<ItemDetailVO>> itemMap = new HashMap<String, List<ItemDetailVO>>();
			
			for (ItemDetailVO idv : itemList) {
				if (itemMap.containsKey(idv.getItemMainCategoryId()+"-"+idv.getItemCategoryId())) {
					itemMap.get(idv.getItemMainCategoryId()+"-"+idv.getItemCategoryId()).add(idv);
				} else {
					List<ItemDetailVO> list = new ArrayList<ItemDetailVO>();
					list.add(idv);
					itemMap.put(idv.getItemMainCategoryId()+"-"+idv.getItemCategoryId(), list);
				}
				
				ItemMainCategory imc = new ItemMainCategory();
				imc.setId(idv.getItemMainCategoryId());
				imc.setMainCategoryName(idv.getItemMainCategoryName());
				mainCateMap.put(idv.getItemMainCategoryId(), imc);
			}
			
			
			model.addAttribute("itemMap", itemMap);
			model.addAttribute("mainCateMap", mainCateMap);
			model.addAttribute("happyHourList", happyHourList);
			model.addAttribute("weeks", weeks);
			return "/pages/happy/happy_hour_list.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	@RequestMapping("/insert")
	public String insert(HttpServletRequest request, @ModelAttribute HappyHour happyHour) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			
			List<HappyHourWeek> hwList = new ArrayList<HappyHourWeek>();
			
			for (int i=0; i<7; i++) {
				String  week=request.getParameter(i+"_week");
				String  startTime=request.getParameter(i+"_startTime");
			  String  endTime=request.getParameter(i+"_endTime");
			  String  isActive=request.getParameter(i+"_isActive");
			  
			  HappyHourWeek hw = new HappyHourWeek();
			  hw.setWeek(week);
			  hw.setStartTime(DateUtil.getDateParse(DateUtil.TIME_SHORT_PATTERN, startTime));
			  hw.setEndTime(DateUtil.getDateParse(DateUtil.TIME_SHORT_PATTERN, endTime));
			  if (StringUtils.isBlank(isActive)) {
			  	hw.setIsActive(CommonStatusConstant.IS_ACTIVE_DISABLE);
			  } else {
			  	hw.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			  }
			  hwList.add(hw);
			}
			
			happyHour.setRestaurantId(restaurantId);
			
			happyHourService.insert(happyHour, hwList);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.HAPPY_HOURS);
			return "redirect:/happyHour/queryAll";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	@RequestMapping("/updateById")
	public String updateById(HttpServletRequest request, @ModelAttribute HappyHour happyHour) {
		Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
		try {
			List<HappyHourWeek> hwList = new ArrayList<HappyHourWeek>();
			for (int i=0; i<7; i++) {
				String  week=request.getParameter(i+"_week");
				String  startTime=request.getParameter(i+"_startTime");
			  String  endTime=request.getParameter(i+"_endTime");
			  String  isActive=request.getParameter(i+"_isActive");
			  
			  HappyHourWeek hw = new HappyHourWeek();
			  hw.setWeek(week);
			  hw.setStartTime(DateUtil.getDateParse(DateUtil.TIME_SHORT_PATTERN, startTime));
			  hw.setEndTime(DateUtil.getDateParse(DateUtil.TIME_SHORT_PATTERN, endTime));
			  if (StringUtils.isBlank(isActive)) {
			  	hw.setIsActive(CommonStatusConstant.IS_ACTIVE_DISABLE);
			  } else {
			  	hw.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			  }
			  hwList.add(hw);
			}
			
			happyHourService.updateById(happyHour,hwList);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.HAPPY_HOURS);
			return "redirect:/happyHour/queryAll";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
	@RequestMapping("/deleteById")
	public String deleteById(HttpServletRequest request, @RequestParam Integer id) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			
			HappyHour hh = new HappyHour();
			hh.setId(id);
			hh.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
			happyHourService.updateById(hh);
			
			PushMsgUtil.sendRestaurant(restaurant.getId(), PushMsgUtil.HAPPY_HOURS);
			return "redirect:/happyHour/queryAll";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
	
}
