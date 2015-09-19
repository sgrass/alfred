package com.alfred.http;

/**
 * 
 * @author grass
 *
 */
public class ResultCode {
	
	public static final String resultKey = "resultCode";
	
	
	/**
	 * 成功
	 */
	public static final int SUCCESS = 1;
	/**
	 * 未知错误
	 */
	public static final int UNKNOW_ERROR = -9999;
	/**
	 * 参数为空
	 */
	public static final int CLIENT_PARAM_EMPTY = -1001;
	/**
	 * 参数解析异常
	 */
	public static final int PARSE_JSON_ERROR = -1002;
	/**
	 * 参数数据有误
	 */
	public static final int JSON_DATA_ERROR = -1003;
	
	
	/**
	 * 插入出错
	 */
	public static final int INSERT_ERROR = -1004;
	/**
	 * 查询为空
	 */
	public static final int QUERY_EMPTY = -1005;
	/**
	 * 更新失败
	 */
	public static final int UPDATE_FAILED = -1006;
	
	
	/**
	 * 餐厅key有误
	 */
	public static final int RESTAURANT_EMPTY = -2001;
	/**
	 * 员工不存在
	 */
	public static final int USER_EMPTY = -2002;
	/**
	 * 密码不正确
	 */
	public static final int USER_PASSWORD_ERROR = -2003;
	/**
	 * userKey不对或过期
	 */
	public static final int USER_KEY_ERROR = -2004;
	/**
	 * 已登录状态已存在
	 */
	public static final int USER_LOGIN_EXIST = -2005;
	
	/**
	 * 用户权限不足
	 */
	public static final int USER_NO_PERMISS = -3001;
	
}
