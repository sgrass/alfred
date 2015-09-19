package com.alfred.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alfred.model.Restaurant;
import com.alfred.service.RestaurantService;
import com.alfred.util.ConfigHelper;


@Scope("prototype")
@Controller("ProfileController")
@RequestMapping(value = "/profile")
public class ProfileController {
	
	private static Log log = LogFactory.getLog(ProfileController.class);
	private final static String uploadPath = ConfigHelper.getString("upload.img.logo.path");
	private final static String separator = "/";
	@Autowired
	@Qualifier("restaurantService")
	private RestaurantService restaurantService;
	
	//private Map<String, Object> result = new HashMap<String, Object>();
	
	@RequestMapping("/profileIndex")
	public String forwardprofileIndex(Model model,HttpServletRequest request){
		//User user = (User) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
		//int companyId=user.getCompanyId();
		try {
			//Company company = companyService.selectByPrimaryKey(companyId);
			Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
			
			Restaurant now=restaurantService.selectByPrimaryKey(res.getId());
			model.addAttribute("restaurant", now);
			return "forward:/pages/rtmanager/profile.jsp";
		} catch (Exception e) {
			log.error(this,e);
		    return null;
		}

	}
	
	
	/**
	 * 更新餐厅
	 * @param restaurant
	 * @return
	 */
	@RequestMapping("/updateRestaurant")

	public String updateRestaurant(HttpServletRequest request,@RequestParam (required = false) String checkboxstatus, @RequestParam(value = "imgFile") MultipartFile file,@ModelAttribute Restaurant restaurant) {
		try {
		
			//更新时间
			restaurant.setUpdateTime(new Date());
			if(checkboxstatus!=null){
				
			if(checkboxstatus.equals("1")){
				restaurant.setQrPayment(1);	
			}else if(checkboxstatus.equals("0")){
				restaurant.setQrPayment(0);	
			}
			}else{
				restaurant.setQrPayment(0);	
			}
			if (file.getSize() > 0) {
				String fileName = file.getOriginalFilename();

				String suffix = fileName.substring(fileName.lastIndexOf("."));
				File filePath = new File(uploadPath);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				String newFileName = UUID.randomUUID().toString() + suffix;
				String newFilePath = filePath.getAbsolutePath() + separator + newFileName;
				//logoUrl = filePath.getAbsolutePath() + separator + subPath(restaurant.getLogoUrl());
				
				restaurant.setLogoUrl2(newFileName);
				
				this.copyFile(file.getInputStream(), newFilePath);
			} else {
				restaurant.setLogoUrl(null);
			}
			
			restaurant.setRestaurantPrint(restaurant.getRestaurantPrint().replaceAll("</br>", "\r\n"));
			restaurant.setAddressPrint(restaurant.getAddressPrint().replaceAll("</br>", "\r\n"));
            
			restaurantService.updateById(restaurant);
			
		    Restaurant restaurants = restaurantService.selectByPrimaryKey(restaurant.getId());
			request.getSession().setAttribute("restaurant", restaurants);
			return "redirect:/profile/profileIndex";
		} catch (Exception e) {
			String newPath = uploadPath + separator + restaurant.getLogoUrl();
			File f = new File(newPath);
			if (f.exists()) {
				f.delete();
			}
			log.error(this, e);
			return null;
		}
	
	}
	
	
	@SuppressWarnings("unused")
	private void copyFile(InputStream in, String fileName) throws Exception {
		FileOutputStream fs = new FileOutputStream(fileName);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = in.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		in.close();
	}
	
	
	private String subPath(String path) {
		return path.substring(path.lastIndexOf("/") + 1, path.length());
	}
	
	//
	
	//ajaxdelimage
	@RequestMapping("/ajaxDelImage")
	@ResponseBody
	private String ajaxDelImage(String path,HttpServletRequest request) {
		Restaurant  res=(Restaurant) request.getSession().getAttribute("restaurant");
		Restaurant restaurant=new Restaurant();
		restaurant.setId(res.getId());
		restaurant.setLogoUrl(null);
		try {
			restaurantService.updateImgById(restaurant);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String newPath = uploadPath + separator + subPath(path);
		System.out.println();
		File f = new File(newPath);
		
		if (f.exists()) {
			f.delete();
		}
		return "0";
	}
	
	
	
	

}
