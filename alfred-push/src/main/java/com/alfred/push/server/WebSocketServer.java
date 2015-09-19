package com.alfred.push.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * A HTTP server which serves Web Socket requests at:
 * 
 * http://localhost:/websocket
 * 
 * Open your browser at http://localhost:/, then the demo page will be loaded
 * and a Web Socket connection will be made automatically.
 * 
 * This server illustrates support for the different web socket specification
 * versions and will work with:
 * 
 * <ul>
 * <li>Safari + (draft-ietf-hybi-thewebsocketprotocol-)
 * <li>Chrome - (draft-ietf-hybi-thewebsocketprotocol-)
 * <li>Chrome + (draft-ietf-hybi-thewebsocketprotocol-)
 * <li>Chrome + (RFC aka draft-ietf-hybi-thewebsocketprotocol-)
 * <li>Firefox + (draft-ietf-hybi-thewebsocketprotocol-)
 * <li>Firefox + (RFC aka draft-ietf-hybi-thewebsocketprotocol-)
 * </ul>
 */
public class WebSocketServer {

	private final int port;

	public WebSocketServer(int port) {
		this.port = port;
	}

	public void test() throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		ServerChannelGroup channels = new ServerChannelGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new WebSocketServerInitializer(channels));
			Channel ch = b.bind(port).sync().channel();
			System.out.println("Web socket server started at port " + port + '.');
			System.out.println("Open your browser and navigate to http://localhost:" + port + '/');
			ch.closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 8081;
		}
		new WebSocketServer(port).test();
	}
}
