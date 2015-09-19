package com.alfred.constant;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alfred.util.ConfigHelper;

public class SysRestTemplate {
	
	private final static String REST_TEMPLATE_CONFIG=ConfigHelper.getString("sys.rest.template");
	
	public static Map<Integer, Integer> getRestConfigMap() {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		if (StringUtils.isBlank(REST_TEMPLATE_CONFIG)) {
			return null;
		}
		String[] rts = REST_TEMPLATE_CONFIG.split("\\|");
		for (String rt : rts) {
			int key = Integer.parseInt(rt.split(":")[0]);
			int value = Integer.parseInt(rt.split(":")[1]);
			map.put(key, value);
		}
		return map;
	}
}
