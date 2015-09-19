package com.cx.test;

import java.io.UnsupportedEncodingException;

public class Temp {

	public static boolean isChinese(char c) throws UnsupportedEncodingException {
		return String.valueOf(c).getBytes("UTF-8").length > 1;
	}

	public static String substring(String orignal, int count) throws UnsupportedEncodingException {
		if (orignal != null && !"".equals(orignal)) {
			orignal = new String(orignal.getBytes(), "UTF-8");
			if (count > 0 && count < orignal.getBytes("UTF-8").length) {
				StringBuffer buff = new StringBuffer();
				char c;
				for (int i = 0; i < count; i++) {
					c = orignal.charAt(i);
					buff.append(c);
					if (isChinese(c)) {
						--count;
					}
				}
				return buff.toString();
			}
		}
		return orignal;
	}
	
	public static String split(String orignal, int splitNum) throws UnsupportedEncodingException {
		int size = orignal.getBytes("UTF-8").length;
		int avg = size/splitNum;
		int cIndex = 0;
		char c[] =orignal.toCharArray();
		StringBuffer buff = new StringBuffer();
		
		for (int i=0; i<splitNum; i++) {
			if (i > 0)
			avg = size/splitNum+cIndex;

			for (int j=cIndex; j<avg; j++) {
				if (isChinese(c[j])) {
//					--avg;
					avg-=2;
				}
				buff.append(c[j]);
				++cIndex;
				if (cIndex == c.length) break;
			}
			avg = size/splitNum;
			buff.append(",");
		}
		return buff.toString();
	}

	public static void main(String[] args) {
		String s = "a阿JAVA是as斯顿";
		try {
//			System.out.println(substring(s, 3));
			
			System.out.println(split(s,2));
			System.out.println(split(s,3));
			System.out.println(split(s,4));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
