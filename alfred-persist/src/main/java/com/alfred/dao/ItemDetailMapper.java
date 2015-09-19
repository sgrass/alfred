package com.alfred.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alfred.model.ItemDetail;
import com.alfred.vo.ItemDetailVO;
import com.alfred.vo.ItemSetMealVO;
import com.alfred.vo.MainMuenTree;

public interface ItemDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ItemDetail itemDetail);

    List<ItemDetail> selectByParam(ItemDetail itemDetail);
    
    int selectCountByParam(ItemDetail itemDetail);
    
    List<ItemDetail> selectPageByParam(Map<String, Object> map);

    ItemDetail selectByPrimaryKey(Integer id);

    int updateById(ItemDetail itemDetail);
    
    List<MainMuenTree> selectItemDetailTree(HashMap<String, Object> map);
    
    List<MainMuenTree> selectItemUpdateDetailTree(HashMap<String, Object> map);
    
    List<ItemDetail> selectByItemList(ItemDetail itemDetail);

    List<ItemDetailVO> selectItemName(Integer restaurantId);
    
    List<ItemDetail> selectByRevenueId(ItemDetail itemDetail);
    
    int insertRevenueCenterMenu(Integer id); 
    
    int updateByRevenueCenterId(ItemDetail itemDetail);
    
    int updateGroupId(ItemDetail itemDetail);
    
    int updateByMainCateId(ItemDetail itemDetail);
    
    int updateBySubCateId(ItemDetail itemDetail);
    
    int updateByItemTemplateId(ItemDetail itemDetail);
    
    List<ItemSetMealVO> selectSetMealPageByParam(Map<String, Object> map);
    
}