package com.cx.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;

public class UrlConnection {

	public static void main(String[] args) throws Exception {
		args = new String[]{"aa"};
		StringBuffer sb = new StringBuffer();
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost("http://192.168.0.14:8081/login/verify/"+args[0]);
		httpclient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,2000);//连接时间
//		httpclient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,2000);//数据传输时间
		
		StringEntity reqEntity = new StringEntity(args[0]+"--{empId:222,password:'111111',restaurantKey:'hgaxcx'} \r\nEOF\r\n");
		reqEntity.setContentType("text/plain;charset=UTF-8");
		httppost.setEntity(reqEntity);
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
		System.out.println(args[0]+"client result:"+IOUtils.toString(reader));
		reader.close();
		
	}

}
