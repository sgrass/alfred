package com.alfred.push.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

@Component
public class PushServer {
	
	private final int port = 8085;
	
	@Scheduled(fixedRate=5000)
	public void run() throws InterruptedException {
		try {
			System.out.println("--------------push server start----------------");
			EventLoopGroup bossGroup = new NioEventLoopGroup(1);
			EventLoopGroup workerGroup = new NioEventLoopGroup();
			ServerChannelGroup channels = new ServerChannelGroup();
			try {
				ServerBootstrap b = new ServerBootstrap();
				b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
						.childHandler(new WebSocketServerInitializer(channels));
				Channel ch = b.bind(port).sync().channel();
				System.out.println("Web socket server started at port " + port + '.');
				System.out.println("Open your browser and navigate to http://localhost:" + port + '/');
				ch.closeFuture().sync();
			} finally {
				bossGroup.shutdownGracefully();
				workerGroup.shutdownGracefully();
			}
		} catch (Exception e) {
		}
	}

	
}
