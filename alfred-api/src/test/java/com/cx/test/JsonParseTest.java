package com.cx.test;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import com.alfred.model.Order;
import com.alfred.model.OrderBill;
import com.alfred.model.OrderDetail;
import com.alfred.model.OrderDetailTax;
import com.alfred.model.OrderModifier;
import com.alfred.model.OrderSplit;
import com.alfred.util.JsonUtil;
import com.alfred.vo.OrderParseVO;

public class JsonParseTest {

	public static void main(String[] args) throws Exception {
//		String str = "{\"order\":{\"businessDate\":\"1402298074048\",\"createTime\":\"1402298074048\",\"discountAmount\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":12,\"id\":1,\"orderNo\":1,\"orderOriginId\":1,\"orderStatus\":10,\"persons\":9,\"restId\":19,\"revenueId\":26,\"sessionStatus\":0,\"subTotal\":\"120.85\",\"tableId\":35,\"taxAmount\":\"0.00\",\"total\":120.85,\"updateTime\":\"1402298074048\",\"userId\":1},\"orderDetailTaxs\":[]}";
		String str = "{\"order\":[{\"businessDate\":1402298074048,\"createTime\":1402298074048,\"discountAmount\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":12,\"id\":1,\"orderNo\":1,\"orderOriginId\":1,\"orderStatus\":10,\"persons\":9,\"restId\":19,\"revenueId\":26,\"sessionStatus\":0,\"subTotal\":\"120.85\",\"tableId\":35,\"taxAmount\":\"0.00\",\"total\":120.85,\"updateTime\":1402298074048,\"userId\":1},{\"businessDate\":1402298074048,\"createTime\":1402298074048,\"discountAmount\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":12,\"id\":1,\"orderNo\":1,\"orderOriginId\":1,\"orderStatus\":10,\"persons\":9,\"restId\":19,\"revenueId\":26,\"sessionStatus\":0,\"subTotal\":\"120.85\",\"tableId\":35,\"taxAmount\":\"0.00\",\"total\":120.85,\"updateTime\":1402298074048,\"userId\":1}]}";
		
//		String od = "{\"orderDetails\":[{\"createTime\":1402298077385,\"discountPrice\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":0,\"fromOrderDetailId\":0,\"id\":1,\"isFree\":0,\"itemId\":35,\"itemNum\":1,\"itemPrice\":\"15\",\"modifierPrice\":\"104.00\",\"orderDetailStatus\":10,\"orderId\":1,\"orderOriginId\":1,\"printStatus\":0,\"realPrice\":\"117.00\",\"reason\":\"\",\"taxPrice\":\"0.00\",\"updateTime\":1402298077385,\"userId\":1},{\"createTime\":1402298077691,\"discountPrice\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":0,\"fromOrderDetailId\":1,\"id\":2,\"isFree\":1,\"itemId\":39,\"itemNum\":5,\"itemPrice\":\"0.00\",\"modifierPrice\":\"0.00\",\"orderDetailStatus\":10,\"orderId\":1,\"orderOriginId\":1,\"printStatus\":0,\"realPrice\":\"0.00\",\"reason\":\"\",\"taxPrice\":\"0.00\",\"updateTime\":1402298077691,\"userId\":1},{\"createTime\":1402298092148,\"discountRate\":\"0.85\",\"discountType\":0,\"fromOrderDetailId\":0,\"id\":3,\"isFree\":0,\"itemId\":41,\"itemNum\":1,\"itemPrice\":\"25.68\",\"modifierPrice\":\"0.00\",\"orderDetailStatus\":10,\"orderId\":1,\"orderOriginId\":1,\"printStatus\":0,\"realPrice\":\"3.8520\",\"reason\":\"\",\"taxPrice\":\"0.00\",\"updateTime\":1402298092148,\"userId\":1},{\"createTime\":1402298092199,\"discountPrice\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":0,\"fromOrderDetailId\":3,\"id\":4,\"isFree\":1,\"itemId\":42,\"itemNum\":1,\"itemPrice\":\"0.00\",\"modifierPrice\":\"0.00\",\"orderDetailStatus\":10,\"orderId\":1,\"orderOriginId\":1,\"printStatus\":0,\"realPrice\":\"0.00\",\"reason\":\"\",\"taxPrice\":\"0.00\",\"updateTime\":1402298092199,\"userId\":1}],\"order\":{\"businessDate\":1402298092199,\"createTime\":1402298074048,\"discountAmount\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":12,\"id\":1,\"orderNo\":1,\"orderOriginId\":1,\"orderStatus\":10,\"persons\":9,\"restId\":19,\"revenueId\":26,\"sessionStatus\":0,\"subTotal\":\"120.85\",\"tableId\":35,\"taxAmount\":\"0.00\",\"total\":\"120.85\",\"updateTime\":1402298074048,\"userId\":1}}";
		String od = "{\"orderDetails\":[{\"createTime\":1402298077385,\"discountPrice\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":0,\"fromOrderDetailId\":0,\"id\":1,\"isFree\":0,\"itemId\":35,\"itemNum\":1,\"itemPrice\":\"15\",\"modifierPrice\":\"104.00\",\"orderDetailStatus\":10,\"orderId\":1,\"orderOriginId\":1,\"printStatus\":0,\"realPrice\":\"117.00\",\"reason\":\"\",\"taxPrice\":\"0.00\",\"updateTime\":1402298077385,\"userId\":1},{\"createTime\":1402298077691,\"discountPrice\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":0,\"fromOrderDetailId\":1,\"id\":2,\"isFree\":1,\"itemId\":39,\"itemNum\":5,\"itemPrice\":\"0.00\",\"modifierPrice\":\"0.00\",\"orderDetailStatus\":10,\"orderId\":1,\"orderOriginId\":1,\"printStatus\":0,\"realPrice\":\"0.00\",\"reason\":\"\",\"taxPrice\":\"0.00\",\"updateTime\":1402298077691,\"userId\":1},{\"createTime\":1402298092148,\"discountRate\":\"0.85\",\"discountType\":0,\"fromOrderDetailId\":0,\"id\":3,\"isFree\":0,\"itemId\":41,\"itemNum\":1,\"itemPrice\":\"25.68\",\"modifierPrice\":\"0.00\",\"orderDetailStatus\":10,\"orderId\":1,\"orderOriginId\":1,\"printStatus\":0,\"realPrice\":\"3.8520\",\"reason\":\"\",\"taxPrice\":\"0.00\",\"updateTime\":1402298092148,\"userId\":1},{\"createTime\":1402298092199,\"discountPrice\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":0,\"fromOrderDetailId\":3,\"id\":4,\"isFree\":1,\"itemId\":42,\"itemNum\":1,\"itemPrice\":\"0.00\",\"modifierPrice\":\"0.00\",\"orderDetailStatus\":10,\"orderId\":1,\"orderOriginId\":1,\"printStatus\":0,\"realPrice\":\"0.00\",\"reason\":\"\",\"taxPrice\":\"0.00\",\"updateTime\":1402298092199,\"userId\":1}],\"order\":{\"businessDate\":1402298077385,\"createTime\":1402298074048,\"discountAmount\":\"0.00\",\"discountRate\":\"0.00\",\"discountType\":12,\"id\":1,\"orderNo\":1,\"orderOriginId\":1,\"orderStatus\":10,\"persons\":9,\"restId\":19,\"revenueId\":26,\"sessionStatus\":0,\"subTotal\":\"120.85\",\"tableId\":35,\"taxAmount\":\"0.00\",\"total\":\"120.85\",\"updateTime\":1402298074048,\"userId\":1}}";
		
		
		
//		JSONObject jsonObject = JSONObject.fromObject(str);
//		
//		String orderJson = jsonObject.getString("order");
//		
//		Order order = JsonUtil.toBean(orderJson, Order.class, null);
//		
//		System.out.println("---------"+order.getBusinessDate());
//		System.out.println("---------"+order.getTotal());
		
		JSONObject jsonObject = JSONObject.fromObject(od);
		
		Map<String, Class> classMap = new HashMap<String, Class>();
    classMap.put("order", Order.class);
    classMap.put("orderBill", OrderBill.class);
    classMap.put("orderDetails", OrderDetail.class);
    classMap.put("orderSplits", OrderSplit.class);
    classMap.put("orderModifiers", OrderModifier.class);
    classMap.put("orderDetailTaxs", OrderDetailTax.class);
     
		OrderParseVO opv = JsonUtil.toBean(od, OrderParseVO.class, classMap);
		
		
		System.out.println(opv.getOrder().getBusinessDate());
		OrderDetail orderDetail = opv.getOrderDetails().get(0);
		System.out.println(orderDetail.getItemId());
		
	}

}
