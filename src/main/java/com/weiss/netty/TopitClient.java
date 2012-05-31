package com.weiss.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class TopitClient {
	private static final Log logger = LogFactory.getLog(TimeClient.class);

	public static void main(String[] args) throws Exception {
		String host = "http://www.topit.me/item/1";
		int port = 80;

		ChannelFactory factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool());

		ClientBootstrap bootstrap = new ClientBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			@Override
			public ChannelPipeline getPipeline() {
				return Channels.pipeline(new SimpleChannelHandler() {
					@Override
					public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
						ChannelBuffer buf = (ChannelBuffer) e.getMessage();
						byte[] bytes = new byte[1024];
						buf.getBytes(0, bytes);
						System.out.println(new String(bytes));
						e.getChannel().close();
					}

					@Override
					public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
						e.getCause().printStackTrace();
						e.getChannel().close();
					}
				});
			}
		});

		bootstrap.setOption("tcpNoDelay", false);
		bootstrap.setOption("keepAlive", true);

		// for (int i = 0; i < 10; i++) {
		bootstrap.connect(new InetSocketAddress(host, port));
		// Thread.sleep(1000);
		// }
		logger.info("timeclient stop..");
	}
}
