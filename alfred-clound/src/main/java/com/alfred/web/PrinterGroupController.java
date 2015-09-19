package com.alfred.web;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.PrinterGroupService;
import com.alfred.service.PrinterService;
import com.alfred.util.PushMsgUtil;

@Scope("prototype")
@Controller("PrinterGroupController")
@RequestMapping(value = "/printerGroup")
public class PrinterGroupController {
	private static Log log = LogFactory.getLog(BohHoldSettlementController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("printerGroupService")
	private PrinterGroupService printerGroupService;	
	
	@Autowired
	@Qualifier("printerService")
	private PrinterService printerService;	
	
	/**
	 * 新增打印机组
	 * @param request
	 * @param printerId
	 * @param groupName
	 * @return
	 */
	@RequestMapping("/addPrinterGroup")
	@ResponseBody
	public Map<String, Object> addPrinterGroup(HttpServletRequest request , @RequestParam(required = false) String[] printerId, @RequestParam(required = false) String groupName) {
		try {
	
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
			String [] pid=request.getParameterValues("printerId[]");

			int flag = printerGroupService.insert(pid, groupName,res.getId(),currentUsers.getCompanyId());
			result.put("flag", flag);
			PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.PRINTER);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	
	}

	
		
	/**
	 * 修改打印机组
	 * @param request
	 * @param id
	 * @param printerId
	 * @param groupName
	 * @return
	 */
	@RequestMapping("/updatePrinterGroup")
	@ResponseBody
	public Map<String, Object> updatePrinterGroup(HttpServletRequest request ,@RequestParam(required = false) Integer id,
			@RequestParam(required = false) String[] printerId, @RequestParam(required = false) String groupName) {
		
		try {
			
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
			String [] pid=request.getParameterValues("printerId[]");
			
			
			int flag = printerGroupService.updateById(id,pid, groupName,res.getId(),currentUsers.getCompanyId());
			result.put("flag", flag);
			PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.PRINTER);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
		
	}

	
    /**
     * 删除打印机组
     * @param request
     * @param id
     * @return
     */
	@RequestMapping("/delPrinterGroup")
	@ResponseBody
	public Map<String, Object> delPrinterGroup(HttpServletRequest request,@RequestParam int id) {
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
			int flag = printerGroupService.deleteByPrimaryKey(id,res.getId(),currentUsers.getCompanyId(),currentUsers.getId());
			result.put("flag", flag);
			PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.PRINTER);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}
		
	}
	
}
