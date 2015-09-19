package com.alfred.util;

import java.util.concurrent.ConcurrentHashMap;

public class CacheMap {
	
	private static CacheMap cm = new CacheMap();
	
	private final long defaultExpireTime = 12 * 60 * 60 * 1000;
	
	private ConcurrentHashMap<String,Long> expMap = new ConcurrentHashMap<String,Long>();
	
	private ConcurrentHashMap<String, Object> cacheMap = new ConcurrentHashMap<String, Object>();

	private CacheMap() {};
	
	public static CacheMap getCacheMapInstance(){
		return cm;
	}
	
	public Object get(Object key) {
		if(cacheMap.containsKey(key)){
			Long expireTime = expMap.get(key);
			long currentTime = System.currentTimeMillis();
			if(currentTime > expireTime){
				remove(key);
				return null;
			}
			return cacheMap.get(key);
		}else{
			return null;
		}
	}
	
	public Object put(String key, Object value) {
		return put(key, value,defaultExpireTime);
	}

	/**
	 * 
	 * @param key 键
	 * @param value 值
	 * @param expireTime 值的有效期，单位毫秒
	 * @return
	 */
	private Object put(String key, Object value,long expireTime) {
		long currentTime = System.currentTimeMillis();
		expMap.put(key, currentTime + defaultExpireTime);
		return cacheMap.put(key, value);
	}

	public Object remove(Object key) {
		expMap.remove(key);
		return cacheMap.remove(key);
	}
	
	public void clearMap() {
		expMap.clear();
		cacheMap.clear();
	}
	
	public int getCacheMapSize() {
		return cacheMap.size();
	}
	
	
	public boolean containsKey(Object key) {
		return cacheMap.containsKey(key);
	}
	
	public boolean containsValue(Object value) {
		return cacheMap.containsValue(value);
	}
	
}
