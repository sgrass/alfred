package com.alfred.push.server;

import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.timeout.IdleStateHandler;

public class WebSocketServerInitializer extends ChannelInitializer<SocketChannel> {

	ServerChannelGroup channels;

	public WebSocketServerInitializer(ServerChannelGroup channels) {
		this.channels = channels;
	}

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("ping", new IdleStateHandler(60, 15, 30, TimeUnit.SECONDS));
		pipeline.addLast("codec-http", new HttpServerCodec());
		pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
		pipeline.addLast("handler", new WebSocketServerHandler(this.channels));
	}
}
