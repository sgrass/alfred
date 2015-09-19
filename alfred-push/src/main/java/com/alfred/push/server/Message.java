package com.alfred.push.server;

public class Message {

	private String to;

	private String msg;

	private String from;

	private int type;// 0:登录 1:发信息 -2心跳

	private Integer restId;
	
	private Integer revenueId;
	
	public Message() {
	}

	public Message(int type, String msg) {
		this.type = type;
		this.msg = msg;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	
	public Integer getRestId() {
		return restId;
	}

	
	public void setRestId(Integer restId) {
		this.restId = restId;
	}

	
	public Integer getRevenueId() {
		return revenueId;
	}

	
	public void setRevenueId(Integer revenueId) {
		this.revenueId = revenueId;
	}
}
