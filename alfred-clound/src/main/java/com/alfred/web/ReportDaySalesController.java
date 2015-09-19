package com.alfred.web;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
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
import org.xhtmlrenderer.pdf.ITextRenderer;
import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.ConfigConstant;
import com.alfred.model.ReportDaySales;
import com.alfred.model.ReportDayTax;
import com.alfred.model.ReportPluDayItem;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.model.RoundAmount;
import com.alfred.service.ReportDaySalesService;
import com.alfred.service.ReportDayTaxService;
import com.alfred.service.ReportPluDayItemService;
import com.alfred.service.RevenueCenterService;
import com.alfred.service.RoundAmountService;
import com.alfred.util.HttpClientUtil;
import com.alfred.vo.ReportDaySalesVO;
@Scope("prototype")
@Controller("ReportDaySalesController")
@RequestMapping(value = "/reportDaySales")
public class ReportDaySalesController {
	private static Log log = LogFactory.getLog(ReportDaySalesController.class);
	
	public static final String METHOD_POST = "POST"; 	
    public static final String CHARACTER_ENCODING = "UTF-8";     
	@Autowired
	@Qualifier("reportDaySalesService")
	private ReportDaySalesService reportDaySalesService;
	
	@Autowired
	@Qualifier("reportPluDayItemService")
	private ReportPluDayItemService reportPluDayItemService;
	
	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;
	
	@Autowired
	@Qualifier("reportDayTaxService")
	private ReportDayTaxService reportDayTaxService;
	
	@Autowired
	@Qualifier("roundAmountService")
	private RoundAmountService roundAmountService;
	
	private Map<String, Object> result = new HashMap<String, Object>();

	@RequestMapping("queryPdf")
	public String queryPdf(HttpServletRequest request, Model model,
			@RequestParam(required = false) String revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  revenueName,
			@RequestParam(required = false) String restaurantName,
			@RequestParam(required = false) String restaurantId){
		try {
			Date date = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
			SimpleDateFormat showFmt = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Date dateEndTime = c.getTime();
			c.add(Calendar.DAY_OF_YEAR, -6);
			Date dateStartTime = c.getTime();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restaurantId", restaurantId);
			if (revenueId != null && !revenueId.equals("0")) {
				map.put("revenueId", revenueId);
			}
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
			String create = showFmt.format(date);
			List<ReportDaySalesVO> vo = reportDaySalesService.querySalesPdf(map);
			List<ReportDayTax> taxList = reportDayTaxService.queryTaxGroup(map);
			List<ReportPluDayItem> list = reportPluDayItemService.querySalesMainCategory(map);
			RoundAmount round = roundAmountService.queryRoundValue(map);
			model.addAttribute("ReportDaySales", vo.get(0));
			model.addAttribute("ReportPluDayItemList", list);
			model.addAttribute("taxList", taxList);
			model.addAttribute("round", round);
			model.addAttribute("unit", "$");
			model.addAttribute("restaurantName", restaurantName);
			model.addAttribute("startTime", startTime);
			model.addAttribute("endTime", endTime);
			model.addAttribute("revenueName", revenueName);
			model.addAttribute("create", create);
			return "forward:/pages/template/sales_report_template.jsp";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
		
	}
	
	
	@RequestMapping("queryReportDaySales")
	public String queryOrderDetail(HttpServletRequest request, Model model) {
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			//获取revenueCenter名称
			RevenueCenter revenueCenter = new RevenueCenter();
			revenueCenter.setRestaurantId(restaurantId);
			revenueCenter.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<RevenueCenter> revenueCenterList = revenueCenterService
					.selectByParam(revenueCenter);
			model.addAttribute("revenueCenterList", revenueCenterList);
			return "forward:/pages/reports/sales.jsp";
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	
	}
	
	
	@RequestMapping("ajaxReportDaySalesJson")
	@ResponseBody
	public Map<String, Object> ajaxReportDaySalesJson(HttpServletRequest request, Model model,	
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
			List<ReportDaySales> reportDaySalesList = null;
			if (revenueId == 0) {
				reportDaySalesList = reportDaySalesService.querySalesRevenueAll(map);
				total = reportDaySalesService.getSalesAllCount(map);

			} else {
				map.put("revenueId", revenueId);
				reportDaySalesList = reportDaySalesService.selectByParamSales(map);
				total = reportDaySalesService.getSalesCount(map);
			}
			result.put("draw", draw);
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("data", reportDaySalesList);
			return result;
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	
	
	
	
	@RequestMapping("exportOrderDetailExcel")
	public void exportOrderDetailExcel(HttpServletRequest request, HttpServletResponse response, Model model,
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  defaultTime,
			@RequestParam(required = false) String revenueName) {
		
		try {
			
		Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
		Integer restaurantId = restaurant.getId();
		
		HashMap<String, Object> map=new HashMap<String, Object>();
		map.put("restaurantId",restaurantId);	
		if(revenueId!=null&&revenueId!=0){
			map.put("revenueId", revenueId);
		}
		if(!("".equals(startTime))&&startTime!=null){
			map.put("startTime", startTime);
		}
		if(!("".equals(endTime))&&endTime!=null){
			map.put("endTime", endTime);
		}
		
	
		
		List<ReportDaySalesVO> reportDaySalesList = null;
		reportDaySalesList = reportDaySalesService.selectByParamSalesExcel(map);
		//获取用户名称
		HSSFWorkbook wb=null;
		wb = reportDaySalesService.export(reportDaySalesList, startTime, endTime, revenueName);
	
		response.setContentType("application/vnd.ms-excel");  
		response.setHeader("Content-disposition", "attachment;filename=ReportDaySales.xls");  
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
	

	@RequestMapping("exportOrderDetailPdf")
	public void exportOrderDetailExcelPdf1(HttpServletResponse response,HttpServletRequest request,
			@RequestParam(required = false) Integer revenueId,
			@RequestParam(required = false) String startTime,
			@RequestParam(required = false) String endTime,
			@RequestParam(required = false) String  revenueName
			)  {
		// FontFactory.registerDirectories();
		
		try {
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			String restaurantName = restaurant.getRestaurantName();
			response.setContentType("application/pdf");
			response.setHeader("Content-disposition","attachment;filename=ExportOrderDetail.pdf");
			OutputStream ouputStream = null;
			ouputStream = response.getOutputStream();
			String url = ConfigConstant.ROOT_PATH;
			url += "/reportDaySales/queryPdf";
			String content = "";
			content += "revenueId=" + revenueId;
			content += "&startTime=" + startTime;
			content += "&endTime=" + endTime;
			content += "&revenueName=" + revenueName;
			content += "&restaurantId=" + restaurantId;
			content += "&restaurantName="
					+ URLEncoder.encode(restaurantName, CHARACTER_ENCODING);
			String str = HttpClientUtil.reqHttp(url, content);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(str);
			renderer.layout();
			renderer.createPDF(ouputStream);
			ouputStream.close();
		} catch (Exception e) {
			log.error(this,e);
			
		}
  
	

	}
	
	@RequestMapping("getReportDaySales")
	@ResponseBody
	public Map<String, Object> getReportDaySales(HttpServletRequest request, Model model,	
			@RequestParam(required = false) Integer revenueId,@RequestParam(required = false) String businessDate) {
		try {
			ReportDaySalesVO reportDaySalesList = null;
			Restaurant restaurant = (Restaurant) request.getSession()
					.getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("restaurantId", restaurantId);
			map.put("businessDate", businessDate.replaceAll("-", "/"));
			if (revenueId != null && revenueId != 0) {
				map.put("revenueId", revenueId);
			}
			if (revenueId != null && revenueId != 0) {
				map.put("reveuneId", revenueId);
				List<ReportDaySalesVO> li = reportDaySalesService.selectByParamSalesExcel(map);

			} else {
				List<ReportDaySalesVO> li = reportDaySalesService
						.querySalesPdf(map);
				if (li != null &&li.size() > 0 ) {
					reportDaySalesList = li.get(0);
				}
			}
			List<ReportDayTax> reportDayTaxList = reportDayTaxService
					.queryTaxGroup(map);
			result.put("reportDayTaxList", reportDayTaxList);
			result.put("reportDayTax", reportDaySalesList);
			return result;
		} catch (Exception e) {
			log.error(this,e);
			return null;
		}
	}
	
	
}
