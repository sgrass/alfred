package com.alfred.push.server;

import io.netty.channel.Channel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;

public class ServerChannelGroup {

	private Map<String, User> userMap = PushMap.getUserMap();

	public ServerChannelGroup() {
	}

	public User getById(String id) {
		return userMap.get(id);
	}

	public String addChannel(Channel channel) {
		String id = String.format("0x%08x", channel.hashCode());
		return addChannel(id, channel);
	}

	public String addChannel(String id, Channel channel) {
		if (!userMap.containsKey(id)) {
			userMap.put(id, new User(channel));
			return id;
		}
		return null;
	}

	public Map<String, User> getAll() {
		return userMap;
	}

	public String onlineUsers(Channel channel) {
		String id = String.format("0x%08x", channel.hashCode());
		return onlineUsers(id);
	}

	public String onlineUsers(String id) {
		Map<String, User> temp = new HashMap<String, User>();
		List<User> online = new ArrayList<User>();
		temp.putAll(userMap);
		temp.remove(id);
		Set<String> ks = temp.keySet();
		for (String k : ks) {
			online.add(temp.get(k));
		}
		return JSONArray.fromObject(online).toString();
	}

	public void removeChannel(Channel channel) {
		String id = String.format("0x%08x", channel.hashCode());
		removeChannel(id);
	}

	public void removeChannel(String id) {
		userMap.remove(id);
	}
}
