package com.alfred.constant;

import com.alfred.util.ConfigHelper;

public class ConfigConstant {
	
	/**
	 * 项目根路径
	 */
	public final static String ROOT_PATH=ConfigHelper.getString("root.path");
	
	/**
	 * 静态文件路径
	 */
	public final static String STATIC_PATH=ConfigHelper.getString("root.static.path");
	
}
