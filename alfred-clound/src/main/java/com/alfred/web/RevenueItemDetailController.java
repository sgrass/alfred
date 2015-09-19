package com.alfred.web;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.multipart.MultipartFile;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.model.HappyHour;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemMainCategory;
import com.alfred.model.Printer;
import com.alfred.model.Restaurant;
import com.alfred.model.RevenueCenter;
import com.alfred.model.TaxCategory;
import com.alfred.model.User;
import com.alfred.pagination.Pagination;
import com.alfred.service.HappyHourService;
import com.alfred.service.ItemDetailService;
import com.alfred.service.ItemMainCategoryService;
import com.alfred.service.PrinterService;
import com.alfred.service.RevenueCenterService;
import com.alfred.service.TaxService;
import com.alfred.util.ConfigHelper;
import com.alfred.vo.ItemMainCategoryVO;

@Scope("prototype")
@Controller
@RequestMapping(value = "reveuneItem")
public class RevenueItemDetailController {

	private static Log log = LogFactory.getLog(RevenueItemDetailController.class);

	@Autowired
	@Qualifier("itemMainCategoryService")
	private ItemMainCategoryService itemMainCategoryService;

	@Autowired
	@Qualifier("itemDetailService")
	private ItemDetailService itemDetailService;

	@Autowired
	@Qualifier("printerService")
	private PrinterService printerService;

	@Autowired
	@Qualifier("happyHourService")
	private HappyHourService happyHourService;

	@Autowired
	@Qualifier("revenueCenterService")
	private RevenueCenterService revenueCenterService;

	@Autowired
	@Qualifier("taxService")
	private TaxService taxService;

	private final static String uploadPath = ConfigHelper.getString("upload.img.item.path");

	private final static String separator = "/";

	@RequestMapping("/queryRevenueItem")
	public String queryRevenueItem(HttpServletRequest request, @RequestParam(required = false) Integer currPage,
			@RequestParam(required = false) Integer itemMainCategoryId,
			@RequestParam(required = false) Integer itemCategoryId, @RequestParam(required = false) Integer revenueId,
			Model model) {

		try {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute("currentUser");
			Restaurant restaurant = (Restaurant) request.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			RevenueCenter rc = new RevenueCenter();
			rc.setRestaurantId(restaurantId);
			List<RevenueCenter> revenueList = revenueCenterService.selectByParam(rc);
			if (revenueList == null || revenueList.size() <= 0) {
				return "/pages/rtmanager/rt_prompt.jsp";
			}
			ItemMainCategory itemMainCategory = new ItemMainCategory();
			itemMainCategory.setRestaurantId(restaurantId);
			List<ItemMainCategoryVO> categoryList = itemMainCategoryService.selectByRestaurant(restaurantId);
			List<Printer> printerList = printerService.selectByRestId(user.getCompanyId(), restaurantId);
			HappyHour happyHour = new HappyHour();
			happyHour.setRestaurantId(restaurantId);
			happyHour.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
			List<HappyHour> happyHourList = happyHourService.selectByParam(happyHour);
			TaxCategory tax = new TaxCategory();
			tax.setRestaurantId(restaurantId);
			tax.setStatus(CommonStatusConstant.IS_ACTIVE_NORMAL);
			tax.setCompanyId(user.getCompanyId());
			List<TaxCategory> taxList = taxService.selectByParam(tax);
			ItemDetail itemDetail = new ItemDetail();
			itemDetail.setRestaurantId(restaurantId);
			itemDetail.setIsActive(0);
			if (revenueId == null) {
				revenueId = revenueList.get(0).getId();
				itemDetail.setRevenueId(revenueId);
			} else {
				itemDetail.setRevenueId(revenueId);
			}
			if (currPage == null) {
				currPage = 0;
			}
			Pagination page = new Pagination();
			page.setItems(itemDetailService.selectCountByParam(itemDetail));
			currPage = currPage < 1 ? 1 : currPage;
			page.setPage(currPage);
			List<ItemDetail> itemList = itemDetailService.selectPageByParam(itemDetail, page);
			model.addAttribute("categoryList", categoryList);
			model.addAttribute("printerList", printerList);
			model.addAttribute("revenueList", revenueList);
			model.addAttribute("happyHourList", happyHourList);
			model.addAttribute("taxList", taxList);
			model.addAttribute("itemList", itemList);
			model.addAttribute("revenueId", revenueId);
			model.addAttribute("currPage", currPage);
			model.addAttribute("rowCount", page.getItems());
			model.addAttribute("startRow", page.getStartRow());
			model.addAttribute("endRow", page.getEndRow());
			model.addAttribute("pageSize", page.getPages());
			return "/pages/item/revenue_item_list.jsp";
		} catch (Exception e) {
			log.error(this,e);
			// TODO: handle exception
			return null;
		}
	}

	@RequestMapping("/insert")
	public String insert(HttpServletRequest req, @RequestParam(value = "imgFile") MultipartFile file,
			ItemDetail itemDetail) {
		try {
			Restaurant restaurant = (Restaurant) req.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();

			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute("currentUser");

			if (file.getSize() > 0) {
				String fileName = file.getOriginalFilename();

				// 获取文件后缀
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				// File filePath = new
				// File(req.getSession().getServletContext().getRealPath("/")+"/"+"upload/img/item");
				File filePath = new File(uploadPath);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				String newFileName = UUID.randomUUID().toString() + suffix;
				String newFilePath = filePath.getAbsolutePath() + separator + newFileName;
				this.copyFile(file.getInputStream(), newFilePath);

				itemDetail.setImgUrl2(newFileName);
			}
			itemDetail.setUserId(user.getId());
			itemDetail.setRestaurantId(restaurantId);
			itemDetailService.insert(itemDetail);
		} catch (Exception e) {
			String newPath = uploadPath + separator + itemDetail.getImgUrl();
			File f = new File(newPath);
			if (f.exists()) {
				f.delete();
			}
			log.error(this, e);
			return "pages/error.jsp";
		}
		return "redirect:/reveuneItem/queryRevenueItem";
	}

	@RequestMapping("/updateById")
	public String updateById(HttpServletRequest req, @RequestParam(value = "imgFile") MultipartFile file,
			ItemDetail itemDetail) {
		int revenueId = 0;
		try {
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute("currentUser");
			if (itemDetail.getRevenueId() != 0) {
				revenueId = itemDetail.getRevenueId();

			}
			;
			String imgUrl = "";
			if (file.getSize() > 0) {
				String fileName = file.getOriginalFilename();

				String suffix = fileName.substring(fileName.lastIndexOf("."));

				// File filePath = new
				// File(req.getSession().getServletContext().getRealPath("/")+"/"+"upload/img/item");
				File filePath = new File(uploadPath);
				if (!filePath.exists()) {
					filePath.mkdirs();
				}
				String newFileName = UUID.randomUUID().toString() + suffix;
				String newFilePath = filePath.getAbsolutePath() + separator + newFileName;
				imgUrl = filePath.getAbsolutePath() + separator + subPath(itemDetail.getImgUrl());

				itemDetail.setImgUrl2(newFileName);
				itemDetail.setUserId(user.getId());
				this.copyFile(file.getInputStream(), newFilePath);
			} else {
				itemDetail.setImgUrl(null);
			}
			itemDetailService.updateById(itemDetail);
		} catch (Exception e) {
			String newPath = uploadPath + separator + itemDetail.getImgUrl();
			File f = new File(newPath);
			if (f.exists()) {
				f.delete();
			}
			log.error(this, e);
			return "pages/error.jsp";
		}
		return "redirect:/reveuneItem/queryRevenueItem?revenueId=" + revenueId;
	}

	@RequestMapping("/deleteById")
	public String deleteById(@RequestParam Integer id) {
		try {
			ItemDetail itemDetail = new ItemDetail();
			itemDetail.setId(id);
			itemDetail.setIsActive(-1);
			itemDetailService.updateById(itemDetail);
			return "redirect:/reveuneItem/queryRevenueItem";
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
	}

	@SuppressWarnings("unused")
	private void copyFile(InputStream in, String fileName) {
		
		
		try {
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
		} catch (Exception e) {
			// TODO: handle exception
			log.error(this,e);
			
		}
	}

	/**
	 * 截取路径，去掉前面部分
	 * 
	 * @param path
	 * @return
	 */
	private String subPath(String path) {
		return path.substring(path.lastIndexOf("/") + 1, path.length());
	}
}
