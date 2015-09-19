package com.alfred.push.server;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class PushMap {

	private static Map<String, User> userMap = new ConcurrentHashMap<String, User>();

	private static Map<Integer, Map<Integer, String>> restaurantMap = new ConcurrentHashMap<Integer, Map<Integer, String>>();

	private static Map<Integer, String> revenueMap = new ConcurrentHashMap<Integer, String>();

	
	public static Map<String, User> getUserMap() {
		return userMap;
	}

	
	public static Map<Integer, Map<Integer, String>> getRestaurantMap() {
		return restaurantMap;
	}

	
	public static Map<Integer, String> getRevenueMap() {
		return revenueMap;
	}

}
