package com.alfred.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.Places;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.model.Tables;
import com.alfred.model.User;
import com.alfred.service.PlacesService;
import com.alfred.service.RevenueCenterService;
import com.alfred.service.TablesService;
import com.alfred.util.PushMsgUtil;
import com.alfred.vo.PlaceTable;

@Scope("prototype")
@Controller("PlaceTableController")
@RequestMapping(value = "/placeTable")
public class PlaceTableController {

	private static Log log = LogFactory.getLog(PlaceTableController.class);
	@Autowired
	@Qualifier("placesService")
	private PlacesService placesService;

	@Autowired
	@Qualifier("tablesService")
	private TablesService tablesService;

	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;

	private Map<String, Object> result = new HashMap<String, Object>();

	/**
	 * revenue 列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/revenueTabIndex")
	public String forwardrevenueTabIndex(HttpServletRequest request, Model model) {
		try {
			// 获取revenue tab 信息
			RevenueCenter revenueCenter = new RevenueCenter();
			List<RevenueCenter> revenueCenterList = null;
			// 获取餐厅的ID
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			// 设置餐厅下面revenueCenter
			revenueCenter.setRestaurantId(res.getId());
			// 获取places 信息
			int revenueId = 0;
			List<PlaceTable> placeTableList = null;
			String placeIdStr = "";
			revenueCenterList = revenueCenterService.selectByParam(revenueCenter);
			for (RevenueCenter r : revenueCenterList) {
				revenueId = r.getId();
				break;
			}
			placeTableList = placesService.selectByRevenueId(revenueId);
			if(placeTableList.size()>0){
				for (PlaceTable t : placeTableList) {
					placeIdStr += t.getId() + ",";
				}
			}
			
			model.addAttribute("revenueId", revenueId);
			model.addAttribute("revenueCenterList", revenueCenterList);
			model.addAttribute("placeTableList", placeTableList);
			model.addAttribute("placeIdStr", placeIdStr);
			if (revenueId == 0) {
				return "forward:/pages/rtmanager/rt_prompt.jsp";
			}
			return "forward:/pages/rtmanager/rt_placetable.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * revenue 列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/revenueTab")
	public String forwardrevenueTab(HttpServletRequest request, @RequestParam int revenueId, Model model) {
		try {
			// 获取revenue tab 信息
			RevenueCenter revenueCenter = new RevenueCenter();
			List<RevenueCenter> revenueCenterList = null;
			// 获取餐厅的ID
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");

			// 设置餐厅下面revenueCenter
			revenueCenter.setRestaurantId(res.getId());

			// 获取places 信息
			List<PlaceTable> placeTableList = null;
			String placeIdStr = "";
			revenueCenterList = revenueCenterService.selectByParam(revenueCenter);

			placeTableList = placesService.selectByRevenueId(revenueId);

			for (PlaceTable t : placeTableList) {
				placeIdStr += t.getId() + ",";
			}

			// tableList=tablesService.selectByParam(table);

			model.addAttribute("revenueId", revenueId);
			model.addAttribute("revenueCenterList", revenueCenterList);
			model.addAttribute("placeTableList", placeTableList);
			model.addAttribute("placeIdStr", placeIdStr);

			// model.addAttribute("placesList", placesList);
			// model.addAttribute("tableList", tableList);

			return "forward:/pages/rtmanager/rt_placetable.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 新增区域
	 * 
	 * @param request
	 * @param places
	 * @return
	 */
	@RequestMapping("/addPlace")
	@ResponseBody
	public Map<String, Object> addPlace(HttpServletRequest request, @ModelAttribute Places places) {
		try {
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");

			places.setRestaurantId(res.getId());
			// 新增区域数据
			int flag = placesService.insert(places);
			PushMsgUtil.sendRevenue(places.getRevenueId(), PushMsgUtil.PLACE_TABLE);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 修改place数据
	 * 
	 * @param places
	 * @return
	 */
	@RequestMapping("/updatePlaces")
	@ResponseBody
	public Map<String, Object> updatePlaces(@ModelAttribute Places places) {
		try {
			// 更新区域数据
			int flag = placesService.updateById(places);
			PushMsgUtil.sendRevenue(places.getRevenueId(), PushMsgUtil.PLACE_TABLE);

			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 删除places
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delPlaces")
	@ResponseBody
	public Map<String, Object> delPlaces(@RequestParam int id) {
		try {
			Places p=placesService.selectByPrimaryKey(id);
			int flag = placesService.deleteByPrimaryKey(id);
			PushMsgUtil.sendRevenue(p.getRevenueId(), PushMsgUtil.PLACE_TABLE);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 新增桌子
	 * 
	 * @param request
	 * @param places
	 * @return
	 */
	@RequestMapping("/addTables")
	@ResponseBody
	public Map<String, Object> addTables(HttpServletRequest request, @ModelAttribute Tables tables) {
		try {
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");

			tables.setRestaurantId(res.getId());
			
			PushMsgUtil.sendRevenue(tables.getRevenueId(), PushMsgUtil.PLACE_TABLE);
			// 新增桌子信息
			int flag = tablesService.insert(tables);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 修改表格数据
	 * 
	 * @param places
	 * @return
	 */
	@RequestMapping("/updateTables")
	@ResponseBody
	public Map<String, Object> updateTables(@ModelAttribute Tables tables) {
		try {
			// 更新表格数据
			int flag = tablesService.updateById(tables);
			PushMsgUtil.sendRevenue(tables.getRevenueId(), PushMsgUtil.PLACE_TABLE);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 删除tables
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delTables")
	@ResponseBody
	public Map<String, Object> delTables(@RequestParam int id) {
		try {
			// 删除表格
			Tables t=tablesService.selectByPrimaryKey(id);
			int flag = tablesService.deleteByPrimaryKey(id);
			PushMsgUtil.sendRevenue(t.getRevenueId(), PushMsgUtil.PLACE_TABLE);
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 验证PLACE
	 * 
	 * @param request
	 * @param placeName
	 * @param id
	 * @param revenueId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkPlace")
	@ResponseBody
	public Map<String, Object> checkPlace(HttpServletRequest request, @RequestParam String placeName,
			@RequestParam int id, @RequestParam int revenueId) {
		try {
			boolean flag = true;
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			Places p = new Places();
			p.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
			p.setPlaceName(placeName);
			p.setRestaurantId(res.getId());
			p.setRevenueId(revenueId);
			if (id != 0) {
				p.setId(id);
			}
			Places places = new Places();
			places = placesService.selectByPlace(p);
			if (places != null) {
				flag = false;
			}
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 验证Table
	 * 
	 * @param request
	 * @param tables
	 * @param id
	 * @param placesId
	 * @param revenueId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkTables")
	@ResponseBody
	public Map<String, Object> checkTables(HttpServletRequest request, @RequestParam String tableName,
			@RequestParam int id, @RequestParam int placesId, @RequestParam int revenueId) {
		try {
			boolean flag = true;
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			Tables t = new Tables();
			t.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
			t.setTableName(tableName);
			t.setRestaurantId(res.getId());
			t.setRevenueId(revenueId);
			t.setPlacesId(placesId);
			if (id != 0) {
				t.setId(id);
			}
			Tables table = new Tables();
			table = tablesService.selectByTable(t);
			if (table != null) {
				flag = false;
			}
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

}
