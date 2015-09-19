package com.alfred.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.SettlementConstant;
import com.alfred.model.Restaurant;
import com.alfred.model.SettlementRestaurant;
import com.alfred.model.User;
import com.alfred.service.SettlementRestaurantService;

@Scope("prototype")
@Controller("SettlementRestaurantController")
@RequestMapping(value = "/settlementRestaurant")
public class SettlementRestaurantController {
	private static Log log = LogFactory.getLog(SettlementRestaurantController.class);
	@Autowired
	@Qualifier("settlementRestaurantService")
	private SettlementRestaurantService settlementRestaurantService;
	
	
	
	@RequestMapping("/settlementRestaurantIndex")
	public String forwardsettlementRestaurantIndex(Model model,HttpServletRequest request){
		//User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		
		String otherPayment="";
		String cash="";
		try {
			Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
			model.addAttribute("baseMedia", SettlementConstant.getBaseMedia());
			model.addAttribute("baseAdjustments", SettlementConstant.getBaseAdjustments());
			model.addAttribute("countryDefault",SettlementConstant.getBaseTemplate());
			//查询餐厅默认选中的值
		
			SettlementRestaurant set=new SettlementRestaurant();
			set.setRestaurantId(res.getId());
			String remarks="";
			String reasons="";
			List<SettlementRestaurant> t=settlementRestaurantService.selectByParam(set);
			
		    
			
			
			if (t == null|| t.size() <=0) {
				//
					for (Integer key : SettlementConstant.getBaseAdjustments().keySet()) {
						SettlementRestaurant s=new SettlementRestaurant();
						s.setRestaurantId(res.getId());
						s.setType(CommonStatusConstant.ADJUEST_TYPE);
						s.setAdjustmentsId(key);
						settlementRestaurantService.insert(s);
					}
					
				model.addAttribute("countryDefault",SettlementConstant.getBaseTemplate());
			} else {
				Map<Integer, Object> map = new HashMap<Integer, Object>();
				Map<Integer, Object> map1 = new HashMap<Integer, Object>();
			
				
				for(SettlementRestaurant tem:t){
					if(tem.getAdjustmentsId()==2000){
						reasons=tem.getRemarks();
						
					}else if(tem.getAdjustmentsId()==105){
						remarks=tem.getRemarks();
					}
					if (CommonStatusConstant.MEDIA_TYPE == tem.getType()){
						
						map.put(tem.getMediaId(), tem.getMediaId());

					}
					if(CommonStatusConstant.ADJUEST_TYPE == tem.getType()){
						
						map1.put(tem.getAdjustmentsId(), tem.getAdjustmentsId());
						
					}
					if(tem.getType()==10){
						otherPayment+=tem.getRemarks()+",";
					}
					if(tem.getType()==0){
						
						cash="checked";
					}
					
				}
				
				model.addAttribute("countryDefault",map);
				model.addAttribute("adjuestDefault",map1);
			}
			if(otherPayment.length()>1){
				if(otherPayment.substring(otherPayment.length()-1, otherPayment.length()).equals(",")){
					otherPayment.substring(0, otherPayment.length()-1);
				}	
			}
		
			model.addAttribute("remarks", remarks);
			model.addAttribute("reasons",reasons);
			model.addAttribute("otherPayment",otherPayment);
			model.addAttribute("cash", cash);

		} catch (Exception e) {
			log.error(this,e);
			// TODO: handle exception
		}
		return "forward:/pages/rtmanager/rt_settlement.jsp";
	}
	
	
	@RequestMapping("/addSettlementRestaurant")
	public String addSettlementRestaurant(Model model,HttpServletRequest request,@RequestParam(required=false) String[] media,@RequestParam(required=false) String[] adjustments,@RequestParam(required=false) String general){
		
		Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
		String remarks=request.getParameter("remarks");
		String reasons=request.getParameter("reasons");
		String otherPayment=request.getParameter("otherPayment");
       
		try {
			
			settlementRestaurantService.bachInsert(media, adjustments, reasons, remarks, res.getId(),general,otherPayment);
		
		} catch (Exception e) {
			log.error(this,e);
			// TODO: handle exception
		}
		return "redirect:/settlementRestaurant/settlementRestaurantIndex";
	}
	
	
	
	
	
}
