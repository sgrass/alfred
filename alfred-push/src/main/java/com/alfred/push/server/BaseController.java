package com.alfred.push.server;

import java.util.HashMap;
import java.util.Map;

public class BaseController {
	
	protected Map<String, Object> resultMap = new HashMap<String, Object>();

	public Map<String, Object> getResultMap() {
		return resultMap;
	}

	public void setResultMap(Map<String, Object> resultMap) {
		this.resultMap = resultMap;
	}

}
