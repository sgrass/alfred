package com.alfred.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class HttpClientUtil {
	
	private static Log log = LogFactory.getLog(HttpClientUtil.class);
	
	public static String reqHttp(String url, String param) {
		StringBuffer sb = new StringBuffer();
		try {
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			StringEntity reqEntity = new StringEntity(param);
			reqEntity.setContentType("application/x-www-form-urlencoded");
			//设置请求的参数
			httppost.setEntity(reqEntity);
			//执行
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				log.info("Response content length: " + entity.getContentLength());
			}
			// 显示结果
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), HTTP.UTF_8));
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} catch (Exception e) {
			log.error("HttpClient reqHttp error" + e);
			e.printStackTrace();
		} 
		return sb.toString();
	}
	
	public static String reqHttp(String url, NameValuePair[] nameValue) {
		try {
			List<NameValuePair> nameValueList = Arrays.asList(nameValue);
			
			DefaultHttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(url);
			
			httppost.setEntity(new UrlEncodedFormEntity(nameValueList,HTTP.UTF_8));

			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				log.info("Response content length: " + entity.getContentLength());
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), HTTP.UTF_8));
			
			return IOUtils.toString(reader);
		} catch (Exception e) {
			log.error("HttpClient reqHttp error" + e);
			e.printStackTrace();
		}
		return null; 
	}
	
	public static void main(String[] args) {
		HttpClientUtil.reqHttp("http://localhost:8082/push/sendRevenue", new NameValuePair[]{
				new BasicNameValuePair("revenueId", "123123")});
	}
}
