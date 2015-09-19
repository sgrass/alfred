package com.alfred.web;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.UserConstant;
import com.alfred.model.Order;
import com.alfred.model.PaymentSettlement;
import com.alfred.model.ReportPluDayItem;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.model.User;
import com.alfred.service.OrderService;
import com.alfred.service.PaymentSettlementService;
import com.alfred.service.ReportPluDayItemService;
import com.alfred.service.RestaurantService;
import com.alfred.vo.OrderCharts;
import com.alfred.vo.PieVo;
import com.alfred.vo.SessionDouble;
import com.alfred.vo.SessionOrder;

@Scope("prototype")
@Controller("DashboardController")
@RequestMapping(value = "/dashboard")
public class DashboardController {
	private static Log log = LogFactory.getLog(DashboardController.class);

	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restaurantService;

	@Autowired
	@Qualifier("orderService")
	private OrderService orderService;

	@Autowired
	@Qualifier("paymentSettlementService")
	private PaymentSettlementService paymentSettlementService;

	@Autowired
	@Qualifier("reportPluDayItemService")
	private ReportPluDayItemService reportPluDayItemService;

	private Map<String, Object> result = new HashMap<String, Object>();
	public final static String ITEM_COLOR1 = "#E67A77";
	public final static String ITEM_COLOR2 = "#D9DD81";
	public final static String ITEM_COLOR3 = "#79D1CF";
	public final static String ITEM_COLOR4 = "#aec785";
	public final static String ITEM_COLOR5 = "#a48ad4";
	public final static String ITEM_COLOR_OTHER = "#767676";

	@RequestMapping("/index")
	public String forwardAccount(HttpServletRequest request, @RequestParam int resId, Model model) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute("currentUser");
			if (UserConstant.USER_TYPE_CORP_MANAGER == user.getType()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setCompanyId(user.getCompanyId());
				restaurant.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
				List<Restaurant> list = restaurantService.selectByStatus(restaurant);
				request.getSession().setAttribute("restaurantList", list);
				// 后台用户查询当前用户下的餐厅 并默认一个餐厅
			}
			Restaurant restaurant = restaurantService.selectByPrimaryKey(resId);
			request.getSession().setAttribute("restaurant", restaurant);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restaurantId", restaurant.getId());
			Order order = new Order();
			order.setRestId(restaurant.getId());
			OrderCharts orderObject = orderService.queryOrderToday(map);
			List<Order> sessionList = orderService.selectByParamSession(order);
			SessionOrder sessionSales = new SessionOrder();
			SessionDouble sessionD = new SessionDouble();
			Double dsum = (double) 0;
			if (sessionList.size() > 0) {
				for (Order o : sessionList) {
					if (o.getSessionStatus() == 1) {
						sessionSales.setFirsePercent(o.getTotal());
					} else if (o.getSessionStatus() == 2) {
						sessionSales.setSecoundPercent(o.getTotal());
					} else if (o.getSessionStatus() == 3) {
						sessionSales.setThirdPercent(o.getTotal());
					}
					dsum += o.getTotal().doubleValue();
				}

				sessionD.setTotal(dsum);
				sessionD.setFirsePercent(sessionSales.getFirsePercent() == null ? 0 : (sessionSales.getFirsePercent().doubleValue() / dsum) * 100);
				sessionD.setSecoundPercent(sessionSales.getSecoundPercent() == null ? 0 : (sessionSales.getSecoundPercent().doubleValue() / dsum) * 100);
				sessionD.setThirdPercent(sessionSales.getThirdPercent() == null ? 0 : (sessionSales.getThirdPercent().doubleValue() / dsum) * 100);

			}
			PaymentSettlement paymentSettlement = new PaymentSettlement();
			paymentSettlement.setRestaurantId(restaurant.getId());
			List<PaymentSettlement> paymentSettlementList = paymentSettlementService.queryPlaySettment(paymentSettlement);
			Double first = (double) 0;
			Double secound = (double) 0;
			Double third = (double) 0;
			Double playCount = (double) 0;
			SessionDouble play = new SessionDouble();
			if (paymentSettlementList.size() > 0) {
				for (PaymentSettlement p : paymentSettlementList) {
					if (p.getPaymentTypeId() < 100 && p.getPaymentTypeId() > 0) {
						first += p.getTotalAmount().doubleValue();
					} else if (p.getPaymentTypeId() < 200 && p.getPaymentTypeId() > 100) {
						secound += p.getTotalAmount().doubleValue();
					} else if (p.getPaymentTypeId() < 300 && p.getPaymentTypeId() > 200) {
						third += p.getTotalAmount().doubleValue();
					}
				}
				playCount = first + secound + third;
				play.setTotal(playCount);
				play.setFirsePercent((first / playCount) * 100);
				play.setSecoundPercent((secound / playCount) * 100);
				play.setThirdPercent((third / playCount) * 100);
			}
			model.addAttribute("order", orderObject);
			model.addAttribute("sessionSales", sessionD);
			model.addAttribute("play", play);
			return "forward:/pages/dashboard/index.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}

	}

	@RequestMapping("/dashboardOrder")
	public String forwardOrder(HttpServletRequest request, Model model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();

			Order order = new Order();
			order.setRestId(restaurantId);

			List<Order> sessionList = orderService.selectByParamSession(order);
			SessionDouble sessionD = new SessionDouble();
			if (sessionList.size() > 0) {
				SessionOrder sessionSales = new SessionOrder();
				for (Order o : sessionList) {
					if (o.getSessionStatus() == 1) {
						sessionSales.setFirsePercent(o.getTotal());
					} else if (o.getSessionStatus() == 2) {
						sessionSales.setSecoundPercent(o.getTotal());
					} else if (o.getSessionStatus() == 3) {
						sessionSales.setThirdPercent(o.getTotal());
					}
				}
				Double dsum = (double) 0;
				dsum = sessionSales.getFirsePercent().doubleValue() + sessionSales.getSecoundPercent().doubleValue() + sessionSales.getThirdPercent().doubleValue();
				sessionD.setTotal(dsum);
				sessionD.setFirsePercent((sessionSales.getFirsePercent().doubleValue() / dsum) * 100);
				sessionD.setSecoundPercent((sessionSales.getSecoundPercent().doubleValue() / dsum) * 100);
				sessionD.setThirdPercent((sessionSales.getThirdPercent().doubleValue() / dsum) * 100);

			}

			PaymentSettlement paymentSettlement = new PaymentSettlement();

			paymentSettlement.setRestaurantId(restaurantId);

			List<PaymentSettlement> paymentSettlementList = paymentSettlementService.queryPlaySettment(paymentSettlement);
			Double first = (double) 0;
			Double secound = (double) 0;
			Double third = (double) 0;
			Double playCount = (double) 0;
			for (PaymentSettlement p : paymentSettlementList) {
				if (p.getPaymentTypeId() < 100 && p.getPaymentTypeId() > 0) {
					first += p.getTotalAmount().doubleValue();
				} else if (p.getPaymentTypeId() < 200 && p.getPaymentTypeId() > 100) {
					secound += p.getTotalAmount().doubleValue();
				} else if (p.getPaymentTypeId() < 300 && p.getPaymentTypeId() > 200) {
					third += p.getTotalAmount().doubleValue();
				}
			}
			playCount = first + secound + third;
			SessionDouble play = new SessionDouble();
			play.setTotal(playCount);
			play.setFirsePercent((first / playCount) * 100);
			play.setSecoundPercent((secound / playCount) * 100);
			play.setThirdPercent((third / playCount) * 100);

			HashMap<String, Object> map = new HashMap<String, Object>();

			map.put("restaurantId", restaurantId);
			OrderCharts orderObject = orderService.queryOrderToday(map);

			model.addAttribute("order", orderObject);
			model.addAttribute("sessionSales", sessionD);
			model.addAttribute("play", play);

			return "forward:/pages/dashboard/index.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}

	}

	/**
	 * 日销售走势图数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajaxDataChart")
	@ResponseBody
	public Map<String, Object> ajaxDataChart(HttpServletRequest request,
			@RequestParam(required = false) String startTime, @RequestParam(required = false) String endTime) {

		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();

			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restaurantId", restaurantId);
			map.put("startTime", startTime);
			map.put("endTime", endTime);

			List<OrderCharts> orderList = orderService.selectByParamCharts(map);
			List<RevenueCenter> revenueCenterList = orderService.queryRevenue(map);
			result.put("revenueCenterList", revenueCenterList);
			result.put("orderList", orderList);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * itemCategory Item 饼图 列表 数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ajaxDataPie")
	@ResponseBody
	public Map<String, Object> ajaxDataPie(HttpServletRequest request) {
		try {
			// 数据封装
			List<PieVo> itemCategory = new ArrayList<PieVo>();
			List<PieVo> items = new ArrayList<PieVo>();
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			ReportPluDayItem rep = new ReportPluDayItem();
			rep.setRestaurantId(restaurantId);
			List<ReportPluDayItem> itemCategoryList = reportPluDayItemService.queryItemCategory(rep);
			List<ReportPluDayItem> itemList = reportPluDayItemService.queryItem(rep);
			int itemListCount = reportPluDayItemService.queryItemCount(rep);
			// 获取总数量
			if (itemCategoryList.size() > 0) {
				int i = 0;
				Double counts = (double) 0;
				for (ReportPluDayItem item : itemCategoryList) {
					PieVo vo = new PieVo();
					counts += item.getItemCount().doubleValue();
					vo.setValue(item.getItemCount().doubleValue());
					vo.setName(item.getItemCategoryName());
					if (i == 0) {
						vo.setColor(String.valueOf(ITEM_COLOR1));
					} else if (i == 1) {
						vo.setColor(String.valueOf(ITEM_COLOR2));
					} else if (i == 2) {
						vo.setColor(String.valueOf(ITEM_COLOR3));
					} else if (i == 3) {
						vo.setColor(String.valueOf(ITEM_COLOR4));
					} else if (i == 4) {
						vo.setColor(String.valueOf(ITEM_COLOR5));
					}
					itemCategory.add(vo);
					i++;
				}
				PieVo vo = new PieVo();
				vo.setValue((itemListCount - counts));
				vo.setColor(ITEM_COLOR_OTHER);
				itemCategory.add(vo);
			}
			if (itemList.size() > 0) {
				int i = 0;
				Double counts = (double) 0;
				for (ReportPluDayItem item : itemList) {
					PieVo vo = new PieVo();
					vo.setValue(item.getItemCount().doubleValue());
					vo.setName(item.getItemName());
					if (i == 0) {
						vo.setColor(String.valueOf(ITEM_COLOR1));
					} else if (i == 1) {
						vo.setColor(String.valueOf(ITEM_COLOR2));
					} else if (i == 2) {
						vo.setColor(String.valueOf(ITEM_COLOR3));
					} else if (i == 3) {
						vo.setColor(String.valueOf(ITEM_COLOR4));
					} else if (i == 4) {
						vo.setColor(String.valueOf(ITEM_COLOR5));
					}
					items.add(vo);
					i++;
				}
				PieVo vo = new PieVo();
				vo.setValue((itemListCount - counts));
				vo.setColor(ITEM_COLOR_OTHER);
				items.add(vo);
			}
			result.put("items", items);
			result.put("itemCategory", itemCategory);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

}
