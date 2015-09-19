package com.alfred.web;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.springframework.web.multipart.MultipartFile;

import com.alfred.model.ItemDetail;
import com.alfred.model.Restaurant;
import com.alfred.model.User;
import com.alfred.service.ItemDetailService;
import com.alfred.util.CacheMap;
import com.alfred.vo.ItemDetailTree;
import com.alfred.vo.ItemTree;
import com.alfred.vo.MainMuenTree;

@Scope("prototype")
@Controller
@RequestMapping(value = "importExcel")
public class ImportExcelItemController {
	private static Log log = LogFactory.getLog(ImportExcelItemController.class);

	@Autowired
	@Qualifier("itemDetailService")
	private ItemDetailService itemDetailService;

	private Map<String, Object> result = new HashMap<String, Object>();

	@RequestMapping("/excelitems")
	public String forwarDexcelitems(Model model) {
		// 加载manager主页信息
		return "forward:/pages/item/import_excel_items.jsp";
	}

	/**
	 * excel to object
	 * 
	 * @param model
	 * @param req
	 * @param file
	 * @return
	 */
	@RequestMapping("/importItems")
	public String importItems(Model model, HttpServletRequest req, @RequestParam(value = "excelFile") MultipartFile file) {

		try {
			Map<String, Map<String, Map<String, Object>>> mainMap = new HashMap<String, Map<String, Map<String, Object>>>();

			if (file.getSize() > 0) {
				String fileName = file.getOriginalFilename();
				// 获取文件后缀
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				InputStream excelStream = file.getInputStream();

				if ((".xlsx".equals(suffix))) {
					XSSFWorkbook xssfWorkbook = new XSSFWorkbook(excelStream);
					XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
					// 循环行Row
					for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
						XSSFRow xssfRow = xssfSheet.getRow(rowNum);
						if (xssfRow == null) {
							continue;
						}
						XSSFCell cellMain = xssfRow.getCell(0);
						XSSFCell cellSub = xssfRow.getCell(1);
						XSSFCell cellItem = xssfRow.getCell(2);
						XSSFCell cellItemDesc = xssfRow.getCell(3);
						XSSFCell cellItemPrice = xssfRow.getCell(4);
						XSSFCell cellItemIsPack = xssfRow.getCell(5);
						XSSFCell cellItemIsTake = xssfRow.getCell(6);
						if (!mainMap.containsKey(getValue(cellMain))) {
							Map<String, Map<String, Object>> subMap = new HashMap<String, Map<String, Object>>();
							mainMap.put(getValue(cellMain), subMap);
						}
						if (!mainMap.get(getValue(cellMain)).containsKey(getValue(cellSub))) {
							Map<String, Object> itemMap = new HashMap<String, Object>();
							mainMap.get(getValue(cellMain)).put(getValue(cellSub), itemMap);
						}
						if (!mainMap.get(getValue(cellMain)).get(getValue(cellSub)).containsKey(getValue(cellSub))) {
							mainMap.get(getValue(cellMain)).get(getValue(cellSub))
									.put(getValue(cellSub), new ArrayList<ItemDetail>());
						}
						ItemDetail itemDetail = new ItemDetail();
						itemDetail.setItemName(getValue(cellItem));
						itemDetail.setItemDesc(getValue(cellItemDesc));
						itemDetail.setPrice(new BigDecimal(getValue(cellItemPrice)));
						int isPack = 0;
						if (getValue(cellItemIsPack).equals("yse")) {
							isPack = 1;
						}
						itemDetail.setIsPack(isPack);
						int isTakeout = 0;
						if (getValue(cellItemIsTake).equals("yse")) {
							isTakeout = 1;
						}
						itemDetail.setIsTakeout(isTakeout);
						((ArrayList) mainMap.get(getValue(cellMain)).get(getValue(cellSub)).get(getValue(cellSub))).add(itemDetail);
					}

				} else if (".xls".equals(suffix)) {
					int i = 0;
					HSSFWorkbook hwb = new HSSFWorkbook(excelStream);
					HSSFSheet sheet = hwb.getSheetAt(0);
					HSSFRow row = null;
					for (i = 1; i < sheet.getLastRowNum(); i++) {
						row = sheet.getRow(i);
						if (row == null) {
							continue;
						}

						HSSFCell cellMain = row.getCell(0);
						HSSFCell cellSub = row.getCell(1);
						HSSFCell cellItem = row.getCell(2);
						HSSFCell cellItemDesc = row.getCell(3);
						HSSFCell cellItemPrice = row.getCell(4);
						HSSFCell cellItemIsPack = row.getCell(5);
						HSSFCell cellItemIsTake = row.getCell(6);
						if (!mainMap.containsKey(getCellValue(cellMain))) {
							Map<String, Map<String, Object>> subMap = new HashMap<String, Map<String, Object>>();
							mainMap.put(getCellValue(cellMain), subMap);
						}
						if (!mainMap.get(getCellValue(cellMain)).containsKey(getCellValue(cellSub))) {
							Map<String, Object> itemMap = new HashMap<String, Object>();
							mainMap.get(getCellValue(cellMain)).put(getCellValue(cellSub), itemMap);
						}
						if (!mainMap.get(getCellValue(cellMain)).get(getCellValue(cellSub)).containsKey(getCellValue(cellSub))) {
							mainMap.get(getCellValue(cellMain)).get(getCellValue(cellSub))
									.put(getCellValue(cellSub), new ArrayList<ItemDetail>());
						}

						ItemDetail itemDetail = new ItemDetail();
						itemDetail.setItemName(getCellValue(cellItem));
						itemDetail.setItemDesc(getCellValue(cellItemDesc));
						itemDetail.setPrice(new BigDecimal(getCellValue(cellItemPrice)));
						int isPack = 0;
						if (getCellValue(cellItemIsPack).equals("yse")) {
							isPack = 1;
						}
						itemDetail.setIsPack(isPack);
						int isTakeout = 0;
						if (getCellValue(cellItemIsTake).equals("yse")) {
							isTakeout = 1;
						}
						itemDetail.setIsTakeout(isTakeout);
						((ArrayList) mainMap.get(getCellValue(cellMain)).get(getCellValue(cellSub)).get(getCellValue(cellSub)))
								.add(itemDetail);

					}
				} else {

					return "forward:/pages/404.jsp";
				}

			}
			CacheMap.getCacheMapInstance().put("_getMainMap_", mainMap);
			return "forward:/pages/item/import_excel_items.jsp";
		} catch (IOException e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * 2007
	 * 
	 * @param xssfCell
	 * @return
	 */
	private String getValue(XSSFCell xssfCell) {
		String strCell = "";
		if (xssfCell != null) {
			switch (xssfCell.getCellType()) {
			case XSSFCell.CELL_TYPE_STRING:
				strCell = xssfCell.getStringCellValue();
				break;
			case XSSFCell.CELL_TYPE_NUMERIC:
				strCell = String.valueOf(xssfCell.getNumericCellValue());
				break;
			case XSSFCell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			case XSSFCell.CELL_TYPE_BOOLEAN:
				strCell = String.valueOf(xssfCell.getBooleanCellValue());
				break;
			default:
				strCell = "";
				break;
			}
		}
		return strCell;
	}

	/**
	 * 2003
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellValue(Cell cell) {// 判断单元格cell的类型并且做出相应的转换
		String strCell = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				strCell = cell.getStringCellValue();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				strCell = String.valueOf(cell.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_BLANK:
				strCell = "";
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				strCell = String.valueOf(cell.getBooleanCellValue());
				break;
			default:
				strCell = "";
				break;
			}
		}
		return strCell;
	}

	/**
	 * excel to tree
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/loadTreeData")
	@ResponseBody
	public Map<String, Object> loadTreeData(HttpServletRequest request) {
		List<MainMuenTree> mtl = new ArrayList<MainMuenTree>();
		Map<String, Map<String, Map<String, Object>>> mainMap = new HashMap<String, Map<String, Map<String, Object>>>();
		mainMap = (Map<String, Map<String, Map<String, Object>>>) CacheMap.getCacheMapInstance().get("_getMainMap_");
		try {

			for (Entry<String, Map<String, Map<String, Object>>> entry : mainMap.entrySet()) {
				MainMuenTree tree = new MainMuenTree();
				List<ItemTree> litree = new ArrayList<ItemTree>();
				tree.setId(entry.getKey());
				tree.setLabel(entry.getKey());
				Map<String, Map<String, Object>> subMap = entry.getValue();
				for (Entry<String, Map<String, Object>> subEntry : subMap.entrySet()) {
					List<ItemDetailTree> itdtree = new ArrayList<ItemDetailTree>();
					ItemTree item = new ItemTree();
					item.setId(subEntry.getKey());
					item.setLabel(subEntry.getKey());

					for (Entry<String, Object> itemMap : subEntry.getValue().entrySet()) {
						List<ItemDetail> list = (List<ItemDetail>) itemMap.getValue();
						for (ItemDetail id : list) {
							ItemDetailTree itd = new ItemDetailTree();
							itd.setLabel(id.getItemName());
							itdtree.add(itd);
						}
						item.setChildren(itdtree);
						litree.add(item);
					}

				}

				tree.setChildren(litree);
				mtl.add(tree);
			}

			result.put("mtlJson", mtl);
			return result;
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

	/**
	 * excel items 持久化
	 * 
	 * @param req
	 * @return
	 */
	@RequestMapping("/insert")
	public String insert(HttpServletRequest req) {

		try {
			Map<String, Map<String, Map<String, Object>>> mainMap = new HashMap<String, Map<String, Map<String, Object>>>();
			mainMap = (Map<String, Map<String, Map<String, Object>>>) CacheMap.getCacheMapInstance().get("_getMainMap_");

			Restaurant restaurant = (Restaurant) req.getSession().getAttribute("restaurant");
			Integer restaurantId = restaurant.getId();
			Subject currentUser = SecurityUtils.getSubject();
			User user = (User) currentUser.getSession().getAttribute("currentUser");
			itemDetailService.insertExcelItems(mainMap, user.getId(), restaurantId);
			return "redirect:/item/queryAll";
		} catch (Exception e) {
			log.error(this, e);
			return null;
		}
	}

}
