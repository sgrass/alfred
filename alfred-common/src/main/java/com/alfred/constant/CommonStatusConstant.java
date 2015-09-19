package com.alfred.constant;

import com.alfred.util.ConfigHelper;

public class CommonStatusConstant {
	
	
	
	/**
	 * 禁用
	 */
	public final static int IS_ACTIVE_DISABLE = 0;

	
	/**
	 * 正常
	 */
	public final static int IS_ACTIVE_NORMAL = 1;
	
	
	/**
	 * 删除
	 */
	public final static int  IS_ACTIVE_DELETE = -1;
	
	/*
	 * 用户权限的等级
	 */
	public final static int  IS_PERMISSION_LEVEL = 3;
	
	
	
	/**
	 * SETTLEMENT 
	 */
	public final static int  CASH_TYPE = 0;
	public final static int  MEDIA_TYPE = 1;
	public final static int  ADJUEST_TYPE = 2;
	public final static int  OTHER_TYPE = 10;
	
	public final static String CASCAD_REST_TYPE=ConfigHelper.getString("cascading.category.restaurant.type");
	

	/**
	 * api接收数据类型 订单数据
	 */
	public final static int  RECEIVE_TYPE_ORDER = 10;
	/**
	 * api接收数据类型 报表数据
	 */
	public final static int  RECEIVE_TYPE_REPORT = 20;
	/**
	 * api接收数据类型 按小时统计报表数据
	 */
	public final static int  RECEIVE_TYPE_REPORT_HOURLY = 21;
	
	/**
	 * api已接收数据 未处理
	 */
	public final static int  NO_PROCESS = 0;
	/**
	 * api已接收数据 已处理
	 */
	public final static int  PROCESSED = 1;
	/**
	 * api已接受数据 处理失败
	 */
	public final static int  PROCESS_ERROR = -1;

}
