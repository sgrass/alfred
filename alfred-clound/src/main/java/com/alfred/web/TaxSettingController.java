package com.alfred.web;

import java.math.BigDecimal;
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
import com.alfred.model.Restaurant;
import com.alfred.model.Tax;
import com.alfred.model.TaxCategory;
import com.alfred.model.User;
import com.alfred.service.TaxService;
import com.alfred.util.PushMsgUtil;
import com.alfred.vo.TaxCategoryVO;

@Scope("prototype")
@Controller("TaxSettingController")
@RequestMapping(value = "/taxSetting")
public class TaxSettingController {
	private static Log log = LogFactory.getLog(TaxSettingController.class);
	private Map<String, Object> result = new HashMap<String, Object>();

	@Autowired
	@Qualifier("taxService")
	private TaxService taxService;
	
	    /**
	     * 设置税收页面
	     * @param request
	     * @param model
	     * @return
	     */
		@RequestMapping("/rtTaxSettingIndex")
		public String forwardTaxSettingIndex(HttpServletRequest request ,Model model) {
			
			try {
				// 设置当前用户
				Subject currentUser = SecurityUtils.getSubject();
				User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
				Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
				Tax tax=new Tax();
				tax.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
				tax.setRestaurantId(res.getId());
				List<Tax> taxList=taxService.selectTaxList(tax);
				TaxCategoryVO taxc=new TaxCategoryVO();
				taxc.setRestaurantId(res.getId());
				taxc.setCompanyId(currentUsers.getCompanyId());
				List<TaxCategoryVO> taxCategoryList= taxService.selectByParam(taxc);
				model.addAttribute("taxList",taxList);
				model.addAttribute("taxCategoryLists",taxCategoryList);
				return "forward:/pages/rtmanager/rt_tax_setting.jsp";
				
			} catch (Exception e) {
				log.error(this, e);
				return null;
			}
			
			
		}
		

        /**
         * 删除税收
         * @param id
         * @return
         */
		@RequestMapping("/delTax")
		@ResponseBody
		public Map<String, Object> delTax(@RequestParam int id,HttpServletRequest request) {
			try {
				Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
				int flag = taxService.deleteByPrimaryKey(id);
				Tax tax=new Tax();
				tax.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
				tax.setRestaurantId(res.getId());
				List<Tax> taxList=taxService.selectTaxList(tax);
				result.put("taxList", taxList);
				PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.TAX);

				result.put("flag", flag);
				return result;
			
			} catch (Exception e) {
				log.error(this, e);
				return null;

			}
			
		}
		
		

        /**
         * 新增税收
         * @param tax
         * @return
         */
		@RequestMapping("/addTax")
		@ResponseBody
		public Map<String, Object> addTax(HttpServletRequest request ,@ModelAttribute Tax tax) {
			try {
			
				Subject currentUser = SecurityUtils.getSubject();
				User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
				Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
				tax.setTaxPercentage(tax.getTaxPercentage().divide(new BigDecimal("100")));
				tax.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
				tax.setCompanyId(currentUsers.getCompanyId());
				tax.setRestaurantId(res.getId());
				tax.setCreateTime(new Date());
				tax.setUpdateTime(new Date());
				int flag = taxService.insert(tax);
				Tax taxs=new Tax();
				taxs.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
				taxs.setRestaurantId(res.getId());
				List<Tax> taxList=taxService.selectTaxList(taxs);
				result.put("taxList", taxList);
				result.put("flag", flag);
				PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.TAX);

				return result;
			
			} catch (Exception e) {
				log.error(this, e);
				return null;

			}
			
		}
		
		
		/**
		 * 修改税收
		 * @param tax
		 * @return
		 */
		@RequestMapping("/updateTax")
		@ResponseBody
		public Map<String, Object> updateTax(HttpServletRequest request,@ModelAttribute Tax tax) {
			try {
				//设置要更新的餐馆信息
				tax.setUpdateTime(new Date());
				tax.setTaxPercentage(tax.getTaxPercentage().divide(new BigDecimal("100")));
				int flag = taxService.updateById(tax);
				Tax taxs=new Tax();
				taxs.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
				Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
				taxs.setRestaurantId(res.getId());
				List<Tax> taxList=taxService.selectTaxList(taxs);
				result.put("taxList", taxList);
				result.put("flag", flag);
				PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.TAX);

				return result;
			
			} catch (Exception e) {
				log.error(this, e);
				return null;

			}
			
		}
		
		/**
		 * 用户名验证是否重复
		 * 
		 * @param account_name
		 * @return
		 * @throws Exception
		 */
		@RequestMapping("/checkTaxName")
		@ResponseBody
	public Map<String, Object> checkTaxName(@RequestParam String taxName,
			@RequestParam int id) {
		try {
			boolean flag = true;
			result.put("flag", flag);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}
		
		
	/**
	 * 新增税收组合
	 * 
	 * @param request
	 * @param tax
	 * @return
	 */
	@RequestMapping("/addTaxCategory")
	@ResponseBody
	public Map<String, Object> addTaxCategory(HttpServletRequest request,
			@RequestParam String taxCategoryName, @RequestParam int tax1Id,
			@RequestParam int tax2Id, @RequestParam int tax2OnValue,
			@RequestParam int tax3Id, @RequestParam int tax3OnValue) {
		try {

			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			// 获取新增数据
			int flag = taxService.insertTaxCategory(taxCategoryName,currentUsers.getCompanyId(), res.getId(), tax1Id, tax2Id,tax2OnValue, tax3Id, tax3OnValue);
			result.put("flag", flag);
			PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.TAX);

			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;

		}
	
	}
		
		
	//
	
	/**
	 * 新增税收组合
	 * 
	 * @param request
	 * @param tax
	 * @return
	 */
	@RequestMapping("/updateTaxCategory")
	@ResponseBody
	public Map<String, Object> updateTaxCategory(HttpServletRequest request,
			@RequestParam int taxGroupId,@RequestParam String taxCategoryName, @RequestParam int tax1Id,
			@RequestParam int tax2Id, @RequestParam int tax2OnValue,
			@RequestParam int tax3Id, @RequestParam int tax3OnValue) {
		try {

			Subject currentUser = SecurityUtils.getSubject();
			User currentUsers = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			// 获取新增数据
			int flag = taxService.updateTaxCategory(taxGroupId, taxCategoryName, currentUsers.getCompanyId(), res.getId(), tax1Id, tax2Id, tax2OnValue, tax3Id, tax3OnValue);
			result.put("flag", flag);
			PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.TAX);
			return result;
		} catch (Exception e) {
			log.error(this, e);
            return null;
		}
		
	}
		
		
		

    /**
     * 删除税收
     * @param id
     * @return
     */
	@RequestMapping("/delTaxCategory")
	@ResponseBody
	public Map<String, Object> delTaxCategory(HttpServletRequest request,@RequestParam int id) {
		TaxCategory taxCategory=new TaxCategory();
		taxCategory.setId(id);
		taxCategory.setStatus(CommonStatusConstant.IS_ACTIVE_DELETE);
		try {
			int flag = taxService.updateByCategoryId(taxCategory);
			Restaurant res = (Restaurant) request.getSession().getAttribute("restaurant");
			result.put("flag", flag);
			PushMsgUtil.sendRestaurant(res.getId(), PushMsgUtil.TAX);

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
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("companyId", currentUsers.getCompanyId());
		map.put("restaurantId", res.getId());
		map.put("taxId", id);
		map.put("status", CommonStatusConstant.IS_ACTIVE_NORMAL);
		int count=taxService.getIsDelCount(map);
		boolean flag = false;
		
		if(count>0){
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
