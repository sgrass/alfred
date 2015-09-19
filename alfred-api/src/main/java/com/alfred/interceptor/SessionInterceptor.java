package com.alfred.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alfred.http.ResultCode;
import com.alfred.util.CacheMap;
import com.alfred.util.JsonUtil;

public class SessionInterceptor implements HandlerInterceptor {

	private static Log log = LogFactory.getLog(SessionInterceptor.class);

	private final String[] excludes = { "loginVerify","logout" };
	
	private Map<String, Object> resultMap = new HashMap<String, Object>();

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
	        throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
	        throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURL().toString();
		log.info(url);
		String rq = url.substring(url.lastIndexOf("/") + 1, url.length());
		if (!exist(rq)) {
			try {
				String jsonStr = JsonUtil.getJsonStrFromRequest(request);
				if (StringUtils.isBlank(jsonStr)) {
					resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
					response.getWriter().println(JsonUtil.toJsonStr(resultMap));
					return false;
				}
				
				JSONObject jsonObject = JSONObject.fromObject(jsonStr);
				String userKey = jsonObject.getString("userKey");
				String restaurantKey = jsonObject.getString("restaurantKey");
				
				if (StringUtils.isBlank(userKey) || StringUtils.isBlank(restaurantKey)) {
					resultMap.put(ResultCode.resultKey, ResultCode.CLIENT_PARAM_EMPTY);
					response.getWriter().println(JsonUtil.toJsonStr(resultMap));
					return false;
				}
				
				log.info(CacheMap.getCacheMapInstance().containsKey(userKey)+"------"+CacheMap.getCacheMapInstance().get(userKey));
				if (!CacheMap.getCacheMapInstance().containsKey(userKey)) {
					resultMap.put(ResultCode.resultKey, ResultCode.USER_KEY_ERROR);
					response.getWriter().println(JsonUtil.toJsonStr(resultMap));
					return false;
				}
				request.setAttribute("jsonStr", jsonStr);
			} catch (Exception e) {
				log.error(this,e);
				resultMap.put(ResultCode.resultKey, ResultCode.PARSE_JSON_ERROR);
				response.getWriter().println(JsonUtil.toJsonStr(resultMap));
				return false;
			}
		}
		return true;
	}

	private boolean exist(String rq) {
		for (String s : excludes) {
			if (s.equals(rq)) {
				return true;
			}
		}
		return false;
	}
}
