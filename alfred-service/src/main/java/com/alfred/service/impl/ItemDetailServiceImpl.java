package com.alfred.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alfred.constant.CommonStatusConstant;
import com.alfred.constant.ItemConstant;
import com.alfred.dao.ItemCategoryMapper;
import com.alfred.dao.ItemDetailMapper;
import com.alfred.dao.ItemMainCategoryMapper;
import com.alfred.model.ItemCategory;
import com.alfred.model.ItemDetail;
import com.alfred.model.ItemMainCategory;
import com.alfred.pagination.Pagination;
import com.alfred.service.ItemDetailService;
import com.alfred.util.MapUtil;
import com.alfred.vo.ItemDetailVO;
import com.alfred.vo.MainMuenTree;

@Transactional
@Service("itemDetailService")
public class ItemDetailServiceImpl implements ItemDetailService {
	private static Log log = LogFactory.getLog(ItemDetailServiceImpl.class);

	@Autowired
	@Qualifier("itemDetailMapper")
	private ItemDetailMapper itemDetailMapper = null;
	
	
	@Autowired
	@Qualifier("itemCategoryMapper")
	private ItemCategoryMapper itemCategoryMapper = null;
	
	
	
	@Autowired
	@Qualifier("itemMainCategoryMapper")
	private ItemMainCategoryMapper itemMainCategoryMapper = null;
	
	
	
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(ItemDetail itemDetail) {
		itemDetail.setCreateTime(new Date());
		itemDetail.setUpdateTime(new Date());
		return itemDetailMapper.insert(itemDetail);
	}

	@Override
	public List<ItemDetail> selectByParam(ItemDetail itemDetail) {
		return itemDetailMapper.selectByParam(itemDetail);
	}

	@Override
	public ItemDetail selectByPrimaryKey(Integer id) {
		return itemDetailMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateById(ItemDetail itemDetail) {
		itemDetail.setUpdateTime(new Date());
		return itemDetailMapper.updateById(itemDetail);
	}

	@Override
	public List<MainMuenTree> selectItemDetailTree(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		List<MainMuenTree> li=itemDetailMapper.selectItemDetailTree(map);
		return li;
	}

	@Override
	public List<MainMuenTree> selectItemUpdateDetailTree(
			HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		List<MainMuenTree> li=itemDetailMapper.selectItemUpdateDetailTree(map);
		return li;
	}

	@Override
	public int insertRevenueCenterMenu(String[] itemDetailIds, int revenueId,String[] removeItemDetailId,int restaurantId) {
		// TODO Auto-generated method stub
		//逻辑操作

		if(removeItemDetailId!=null && removeItemDetailId.length>0){
			for(String remStr:removeItemDetailId){
				
				if(remStr!=""){
					ItemDetail itemDetail=new ItemDetail();
					itemDetail.setItemTemplateId(Integer.parseInt(remStr));
					itemDetail.setRevenueId(revenueId);
					itemDetail.setRestaurantId(restaurantId);
					itemDetail.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
					itemDetailMapper.updateByRevenueCenterId(itemDetail);
				}
			}
		}
		
		for(String s:itemDetailIds){
			if(s!=""&&s!=null){
			int id=Integer.parseInt(s);
			//判断数据库里面是否存在数据
			ItemDetail t=queryRevenueId(0,id, revenueId) ;
			if(t==null){
				//如果数据库里面没有词条记录就新增数据
				ItemDetail temp=queryRevenueId(id,0, 0) ;
				temp.setId(null);
				temp.setItemTemplateId(id);
				temp.setRevenueId(revenueId);
				temp.setCreateTime(new Date());
				temp.setItemType(ItemConstant.ITEM_TYPE_SUB_ITEM);
				temp.setUpdateTime(new Date());
				
				if (null != temp.getImgUrl() && !"".equals(temp.getImgUrl())) {
					temp.setImgUrl2(temp.getImgUrl().substring(temp.getImgUrl().lastIndexOf("/")+1));
				}
				itemDetailMapper.insert(temp);
			}
			}
		}
		return 0;
	}

	@Override
	public ItemDetail queryRevenueId(int id,int itemTemplateId, int revenueId) {
		// TODO Auto-generated method90; stub
		ItemDetail i =new  ItemDetail();
		if(id!=0){
			i.setId(id);
		}
	
		if(revenueId!=0){
			i.setRevenueId(revenueId);
		}
		if(itemTemplateId!=0){
			i.setItemTemplateId(itemTemplateId);
			
		}
		i.setIsActive(0);
		List<ItemDetail>  li=itemDetailMapper.selectByParam(i);
		
		if(li!=null&&li.size()>0){
			return li.get(0);
		}
		
		return null;
	}

	@Override
	public int insertExcelItems(
			Map<String, Map<String, Map<String, Object>>> mainMap, int userId,
			int restaurantId) {
		
			for (Entry<String, Map<String, Map<String, Object>>> entry : mainMap.entrySet()) {
				ItemMainCategory itemMainCategory=new ItemMainCategory();
				itemMainCategory.setCreateTime(new Date());
				itemMainCategory.setUpdateTime(new Date());
				itemMainCategory.setUserId(userId);
				itemMainCategory.setRestaurantId(restaurantId);
				itemMainCategory.setMainCategoryName(entry.getKey());
				itemMainCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
				itemMainCategoryMapper.insert(itemMainCategory);
				
				int itemMainCategoryId=itemMainCategory.getId();
			
			Map<String, Map<String, Object>> subMap = entry.getValue();
				for (Entry<String, Map<String, Object>> subEntry : subMap.entrySet()) {
					ItemCategory itemCategory=new ItemCategory();
					itemCategory.setCreateTime(new Date());
					itemCategory.setUpdateTime(new Date());
					itemCategory.setUserId(userId);
					itemCategory.setRestaurantId(restaurantId);
					itemCategory.setItemCategoryName(subEntry.getKey());
					itemCategory.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
					itemCategory.setItemMainCategoryId(itemMainCategoryId);
			
					itemCategoryMapper.insert(itemCategory);
					
					int itemCategoryId=itemCategory.getId();
					
					for (Entry<String, Object> itemMap : subEntry.getValue().entrySet()) {
						List<ItemDetail> list = (List<ItemDetail>) itemMap.getValue();
						for (ItemDetail id : list) {
						
							ItemDetail itemDetail =new ItemDetail();
							itemDetail.setCreateTime(new Date());
							itemDetail.setUpdateTime(new Date());
							itemDetail.setUserId(userId);
							itemDetail.setIsActive(CommonStatusConstant.IS_ACTIVE_NORMAL);
							itemDetail.setRestaurantId(restaurantId);
							itemDetail.setPrice(id.getPrice());
							itemDetail.setItemName(id.getItemName());
							itemDetail.setItemDesc(id.getItemDesc());
							itemDetail.setIsPack(id.getIsPack());
							itemDetail.setIsTakeout(id.getIsTakeout());
							itemDetail.setItemCategoryId(itemCategoryId);
							itemDetail.setItemMainCategoryId(itemMainCategoryId);
							itemDetail.setItemType(ItemConstant.ITEM_TYPE_TEMPLATE);
							itemDetailMapper.insert(itemDetail);
						}
					}
			
				}
		}
		
		return 0;
	}

	@Override
	public List<ItemDetailVO> selectItemName(Integer restaurantId) {
		return itemDetailMapper.selectItemName(restaurantId);
	}

	@Override
	public int selectCountByParam(ItemDetail itemDetail) {
		return itemDetailMapper.selectCountByParam(itemDetail);
	}

	@Override
	public List<ItemDetail> selectPageByParam(ItemDetail itemDetail, Pagination page) {
		Map<String, Object> map = null;;
		map = MapUtil.toMap(itemDetail);
		map.put("startRow", page.getStartRow() > 0 ? page.getStartRow() - 1 : 0);
		map.put("perPageSize", page.getItemsPerPage());
		return itemDetailMapper.selectPageByParam(map);
	}

	@Override
	public int deleteCascadById(Integer restaurantId, Integer itemId, Integer userId) throws Exception {
		int resultRow = 0;
		ItemDetail templateItem = new ItemDetail();
		templateItem.setId(itemId);
		templateItem.setUserId(userId);
		templateItem.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		resultRow += this.updateById(templateItem);
		
		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setItemTemplateId(itemId);
		itemDetail.setRestaurantId(restaurantId);
		itemDetail.setIsActive(CommonStatusConstant.IS_ACTIVE_DELETE);
		itemDetail.setUserId(userId);
		itemDetail.setUpdateTime(new Date());
		resultRow += itemDetailMapper.updateByItemTemplateId(itemDetail);
		
		return resultRow;
	}

	@Override
	public List<ItemDetail> selectByRevenueId(Integer restaurantId, Integer revenueId, Integer itemType, Integer isActive)
			throws Exception {
		ItemDetail itemDetail = new ItemDetail();
		itemDetail.setRestaurantId(restaurantId);
		itemDetail.setRevenueId(revenueId);
		itemDetail.setIsActive(isActive);
		itemDetail.setItemType(itemType);
		
		return itemDetailMapper.selectByRevenueId(itemDetail);
	}

}
