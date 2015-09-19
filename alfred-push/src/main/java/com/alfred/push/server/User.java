package com.alfred.push.server;

import io.netty.channel.Channel;

public class User {
	private Channel channel;
	private String address;
	private String id;
	public User(){}
	public User(Channel channel,String addr,String id){
		this.channel=channel;
		this.address=addr;
		this.id=id;
	}
	public User(Channel channel,String addr){
		this.channel=channel;
		this.address=addr;
	}
	public User(Channel channel){
		this.channel=channel;
		this.address=channel.remoteAddress().toString();
		this.id=String.format("0x%08x",channel.hashCode());
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
