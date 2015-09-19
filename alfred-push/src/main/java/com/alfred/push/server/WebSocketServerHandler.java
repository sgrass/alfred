package com.alfred.push.server;

import static io.netty.handler.codec.http.HttpHeaders.isKeepAlive;
import static io.netty.handler.codec.http.HttpHeaders.setContentLength;
import static io.netty.handler.codec.http.HttpHeaders.Names.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaders.Names.HOST;
import static io.netty.handler.codec.http.HttpMethod.GET;
import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpResponseStatus.FORBIDDEN;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;

import com.alfred.util.JsonUtil;

/**
 * Handles handshakes and messages
 */
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
	private static Log log = LogFactory.getLog(WebSocketServerHandler.class);

	private static final String WEBSOCKET_PATH = "/websocket";

	private WebSocketServerHandshaker handshaker;

	private ServerChannelGroup channels;

	public WebSocketServerHandler(ServerChannelGroup channels) {
		this.channels = channels;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg)
			throws Exception {
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		} else if (msg instanceof WebSocketFrame) {
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	private void handleHttpRequest(ChannelHandlerContext ctx,
			FullHttpRequest req) throws Exception {
		// Handle a bad request.
		// String id = channels.addChannel(ctx.channel());
		// if (id != null) {
		// Map<String, User> all = channels.getAll();
		// Set<String> keys = all.keySet();
		// for (String k : keys) {
		// // all.get(k).getChannel().write(channels.onlineUsers(k));
		// }
		// }
		if (!req.getDecoderResult().isSuccess()) {
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1,
					BAD_REQUEST));
			return;
		}

		// Allow only GET methods.
		if (req.getMethod() != GET) {
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1,
					FORBIDDEN));
			return;
		}

		// Send the demo page and favicon.ico
		if ("/".equals(req.getUri())) {
			ByteBuf content = WebSocketServerIndexPage
					.getContent(getWebSocketLocation(req));
			FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1, OK,
					content);
			res.headers().set(CONTENT_TYPE, "text/html; charset=UTF-8");
			setContentLength(res, content.readableBytes());
			sendHttpResponse(ctx, req, res);
			return;
		}
		if ("/favicon.ico".equals(req.getUri())) {
			FullHttpResponse res = new DefaultFullHttpResponse(HTTP_1_1,
					NOT_FOUND);
			sendHttpResponse(ctx, req, res);
			return;
		}

		// Handshake
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
				getWebSocketLocation(req), null, false);
		handshaker = wsFactory.newHandshaker(req);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx
					.channel());
		} else {
			handshaker.handshake(ctx.channel(), req);
		}
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx,
			WebSocketFrame frame) {

		// Check for closing frame
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(),
					(CloseWebSocketFrame) frame.retain());
			return;
		}
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(
					new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(String.format(
					"%s frame types not supported", frame.getClass().getName()));
		}

		// Send the uppercase string back.
		String request = ((TextWebSocketFrame) frame).text();
		Message msg = null;
		try {
			msg = JsonUtil.toBean(request, Message.class,null);
		} catch (Exception e) {
			log.error(this,e);
		}
		
		log.info(String.format("%s received %s", ctx.channel(), request));
		
		if (msg.getType() == 0 || msg.getType() == 2) {
			
			PushMap.getRevenueMap().put(msg.getRevenueId(), String.format("0x%08x",ctx.channel().hashCode()));
			
			if (PushMap.getRestaurantMap().containsKey(msg.getRestId())) {
				Map<Integer, String> revenueIdMap = PushMap.getRestaurantMap().get(msg.getRestId());
				revenueIdMap.put(msg.getRevenueId(), null);
				PushMap.getRestaurantMap().put(msg.getRestId(), revenueIdMap);
			} else {
				Map<Integer, String> revenueIdMap = new HashMap<Integer, String>();
				revenueIdMap.put(msg.getRevenueId(), null);
				PushMap.getRestaurantMap().put(msg.getRestId(), revenueIdMap);
			}
			
			Map<String, User> all = channels.getAll();
			Set<String> keys = all.keySet();
			Map<String, Object> temp = new HashMap<String, Object>();
			temp.put("type", 0);
			Channel c = null;
			for (String k : keys) {
				c = all.get(k).getChannel();
				temp.put("users", channels.onlineUsers(k));
				temp.put("from", c.remoteAddress().toString());
				try {
					c.writeAndFlush(new TextWebSocketFrame(JsonUtil.toJsonStr(temp).toString()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (msg.getType() == 1) {
			User u = channels.getById(msg.getTo());
			if (u != null) {
				u.getChannel().writeAndFlush(
						new TextWebSocketFrame(JSONObject.fromObject(msg).toString()));
			} else {
				ctx.channel().writeAndFlush(new TextWebSocketFrame(JSONObject.fromObject(new Message(-1,"不在线")).toString()));
			}
		} else if(msg.getType()==-2){
			System.out.println("一次心跳完成。");
		}
	}

	private static void sendHttpResponse(ChannelHandlerContext ctx,
			FullHttpRequest req, FullHttpResponse res) {
		// Generate an error page if response getStatus code is not OK ().
		if (res.getStatus().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(),
					CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
			setContentLength(res, res.content().readableBytes());
		}

		// Send the response and close the connection if necessary.
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!isKeepAlive(req) || res.getStatus().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	private static String getWebSocketLocation(FullHttpRequest req) {
		return "ws://" + req.headers().get(HOST) + WEBSOCKET_PATH;
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object msg)
			throws Exception {
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		channels.removeChannel(ctx.channel());
		super.channelUnregistered(ctx);
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		super.channelRegistered(ctx);
		channels.addChannel(ctx.channel());
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			if (event.state().equals(IdleState.READER_IDLE)) {
				System.out.println("READER_IDLE");
				// 超时关闭channel
				ctx.close();
			} else if (event.state().equals(IdleState.WRITER_IDLE)) {
				System.out.println("WRITER_IDLE");
			} else if (event.state().equals(IdleState.ALL_IDLE)) {
				System.out.println("ALL_IDLE");
				// 发送心跳
				ctx.channel().writeAndFlush(new TextWebSocketFrame(JSONObject.fromObject(new Message(-2,"ping")).toString()));
			}
		}
		super.userEventTriggered(ctx, evt);
	}

}
