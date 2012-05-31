package com.weiss.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

public class TimeClient {
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
				return Channels.pipeline(new TimeClientHandler());
			}
		});

		bootstrap.setOption("tcpNoDelay", false);
		bootstrap.setOption("keepAlive", true);

		for (int i = 0; i < 10; i++) {
			bootstrap.connect(new InetSocketAddress(host, port));
			Thread.sleep(1000);
		}
		logger.info("timeclient stop..");
	}
}
