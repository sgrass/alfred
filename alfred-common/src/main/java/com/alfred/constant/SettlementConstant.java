package com.alfred.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alfred.util.ConfigHelper;

public class SettlementConstant {

	private final static String BASE_MEDIA=ConfigHelper.getString("base.media");
	
	private final static String BASE_ADJUSTMENTS=ConfigHelper.getString("base.adjustments");
	
	private final static String SINGAPOR_MEDIA_TEMPLATE=ConfigHelper.getString("country.singapore.meida");
	
	
	public static Map<Integer, String> getBaseMedia() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (StringUtils.isBlank(BASE_MEDIA)) {
			return null;
		}
		String[] bms = BASE_MEDIA.split("\\|");
		for (String bm : bms) {
			int key = Integer.parseInt(bm.split(":")[0]);
			String value = bm.split(":")[1];
			map.put(key, value);
		}
		return map;
	}
	
	public static Map<Integer, String> getBaseAdjustments() {
		Map<Integer, String> map = new HashMap<Integer, String>();
		if (StringUtils.isBlank(BASE_ADJUSTMENTS)) {
			return null;
		}
		String[] bms = BASE_ADJUSTMENTS.split("\\|");
		for (String bm : bms) {
			int key = Integer.parseInt(bm.split(":")[0]);
			String value = bm.split(":")[1];
			map.put(key, value);
		}
		return map;
	}
	
	
	
	public static  Map<Integer, Object> getBaseTemplate() {
		Map<Integer, Object> map = new HashMap<Integer, Object>();
		if (StringUtils.isBlank(SINGAPOR_MEDIA_TEMPLATE)) {
			return null;
		}
		String[] bms = SINGAPOR_MEDIA_TEMPLATE.split("\\|");
		for (String bm : bms) {
			map.put(Integer.parseInt(bm), bm);
		}
		return map;
	}
	
}
