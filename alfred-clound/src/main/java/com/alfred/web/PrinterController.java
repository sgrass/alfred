package com.alfred.web;

import java.util.ArrayList;
import java.util.Date;
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
import com.alfred.model.Printer;
import com.alfred.model.PrinterGroup;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.PrinterGroupService;
import com.alfred.service.PrinterService;
import com.alfred.util.PushMsgUtil;
import com.alfred.vo.PrinterGroupVO;

@Scope("prototype")
@Controller("PrinterController")
@RequestMapping(value = "/printer")
public class PrinterController {

	private static Log log = LogFactory.getLog(PrinterController.class);

	@Autowired
	@Qualifier("printerService")
	private PrinterService printerService;
	
	@Autowired
	@Qualifier("printerGroupService")
	private PrinterGroupService printerGroupService;	

	private Map<String, Object> result = new HashMap<String, Object>();

	/**
	 * 打印机列表
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/rtPrinter")
	public String forwardPrinter(HttpServletRequest request, Model model) {

		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			// printer
			Printer printer = new Printer();
			// 设置公司名称
			printer.setCompanyId(currentUsers.getCompanyId());
			// 设置餐厅Id
			printer.setRestaurantId(res.getId());
			printer.setType(CommonStatusConstant.IS_ACTIVE_NORMAL);
			map.put("companyId", currentUsers.getCompanyId());
			map.put("restaurantId", res.getId());
			map.put("type", CommonStatusConstant.IS_ACTIVE_DISABLE);
			List<Printer> printerList = new ArrayList<Printer>();
			List<PrinterGroupVO> printerGroupVOList = null;
			// 查询当前公司所有的打印机
			printerList = printerService.selectByParam(printer);
			printerGroupVOList = printerService.selectPrinterGroup(map);
			model.addAttribute("printerList", printerList);
			model.addAttribute("printerGroupVOList", printerGroupVOList);
			return "forward:/pages/rtmanager/rt_print.jsp";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}

	}

	/**
	 * 新增打印机
	 * 
	 * @param printer
	 * @return
	 */
	@RequestMapping("/addPrinter")
	@ResponseBody
	public Map<String, Object> addPrinter(HttpServletRequest request, @ModelAttribute Printer printer) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			printer.setCompanyId(currentUsers.getCompanyId());
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			// printer
			Printer pl = new Printer();
			pl.setCompanyId(currentUsers.getCompanyId());
			pl.setRestaurantId(res.getId());
			pl.setType(CommonStatusConstant.IS_ACTIVE_NORMAL);

			// 设置用户状态
			printer.setCreateTime(new Date());
			printer.setUpdateTime(new Date());
			printer.setType(CommonStatusConstant.IS_ACTIVE_NORMAL);
			printer.setRestaurantId(res.getId());

			// 更新时
			int flag = printerService.insert(printer);
			List<Printer> printerList = printerService.selectByParam(pl);
			
			result.put("printerList", printerList);
			result.put("flag", flag);
			
			
			PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.PRINTER);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 修改打印机
	 * 
	 * @param printer
	 * @return
	 */
	@RequestMapping("/updatePrinter")
	@ResponseBody
	public Map<String, Object> updatePrinter(HttpServletRequest request, @ModelAttribute Printer printer) {

		try {
			// printer.setUpdateTime(new Date());
			// printer
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			Printer pl = new Printer();
			pl.setCompanyId(currentUsers.getCompanyId());
			pl.setRestaurantId(res.getId());
			pl.setType(CommonStatusConstant.IS_ACTIVE_NORMAL);
			int flag = printerService.updateById(printer);
			List<Printer> printerList = printerService.selectByParam(pl);

			result.put("flag", flag);
			result.put("printerList", printerList);
			
			PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.PRINTER);
			
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 删除打印机
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delPrinter")
	@ResponseBody
	public Map<String, Object> delPrinter(HttpServletRequest request, @RequestParam int id) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			int flag = printerGroupService.deletePtint(id,currentUsers.getCompanyId(),res.getId());
			
			Printer pl = new Printer();
			pl.setCompanyId(currentUsers.getCompanyId());
			pl.setRestaurantId(res.getId());
			pl.setType(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<Printer> printerList = printerService.selectByParam(pl);
			result.put("flag", flag);
			result.put("printerList", printerList);
			PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.PRINTER);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 新增时验证打印机名称是否存在
	 * 
	 * @param printerName
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/checkPrinter")
	@ResponseBody
	public Map<String, Object> checkPrinter(HttpServletRequest request, @RequestParam String printerName,
			@RequestParam int id) {

		try {
			boolean flag = true;
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			Printer printer = printerService.selectByPrinter(printerName, id, res.getId());
			if (printer != null) {
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
	 * 验证打印机是否可以删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/checkCount")
	@ResponseBody
public Map<String, Object> checkCount(HttpServletRequest request,@RequestParam int id) {
	try {
		Subject currentUser = SecurityUtils.getSubject();
		User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
		Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");

		PrinterGroup pg =new PrinterGroup();
		pg.setCompanyId(currentUsers.getCompanyId());
		pg.setRestaurantId(res.getId());
		pg.setPrinterId(id);
		List<PrinterGroup> pl=printerGroupService.selectByParam(pg);
		boolean flag = false;
		
		if(pl.size()>0){
			flag=true;
		}
		result.put("flag", flag);
		
		return result;
	} catch (Exception e) {
		log.error(this, e);
		return null;
	}
}
	
	
	
	
	
	

}
