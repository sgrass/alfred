package com.alfred.util;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class PushMsgUtil {

	public final static String HAPPY_HOURS = "happyhour";

	public final static String PRINTER = "printer";

	public final static String ITEM = "item";

	public final static String MODIFIER = "modifier";

	public final static String USER = "user";

	public final static String RESTAURANT = "restaurant";

	public final static String PLACE_TABLE = "place_table";

	public final static String TAX = "tax";
	
	public static String sendRestaurant(Integer restId, String msgKey) {
//		String str = HttpClientUtil.reqHttp("http://localhost:8082/push/sendRestaurant", "restId="+restId+"&msgContent"+msgKey);
		String str = HttpClientUtil.reqHttp("http://localhost:8082/push/sendRestaurant", new NameValuePair[]{
				new BasicNameValuePair("restId", restId.toString()),new BasicNameValuePair("msgContent", msgKey)});
		return str;
	}

	public static String sendRevenue(Integer revenueId, String msgKey) {
		String str = HttpClientUtil.reqHttp("http://localhost:8082/push/sendRevenue", new NameValuePair[]{
				new BasicNameValuePair("revenueId", revenueId.toString()),new BasicNameValuePair("msgContent", msgKey)});
		return str;
	}
	
	public static void main(String[] args) {
//		System.out.println(sendRevenue(26, "aaa 阿斯顿"));
		System.out.println(sendRestaurant(19, "aaa 阿斯顿"));
	}
}
