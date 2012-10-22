package com.weiss.msgpack.rpc;

import org.msgpack.MessagePack;
import org.msgpack.rpc.Client;
import org.msgpack.rpc.loop.EventLoop;
import org.msgpack.type.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcClient {

	private static Logger logger = LoggerFactory.getLogger(RpcClient.class);
	private static Calculator iface;
	public static Client client;
	public static MessagePack pack = new MessagePack();
	public static EventLoop loop;
	private static String rpcIp = "127.0.0.1";
	private static int rpcPort = 8080;

	public static void main(String[] args) throws Exception {
		pack.register(User.class);

		loop = EventLoop.start(pack);
		client = new Client(rpcIp, rpcPort, loop);
		iface = client.proxy(Calculator.class);

		logger.info("10,5  add: " + iface.add(10, 5));
		logger.info("10,5  subtract: " + iface.subtract(10, 5));
		logger.info("10,5  multiply: " + iface.multiply(10, 5));
		logger.info("10,5  divide: " + iface.divide(10, 5));
		logger.info("get user: " + iface.getUser("weiss"));

		Value value = client.callApply("com.weiss.msgpack.rpc.CalculatorHandler:getUser", new Object[] { "weiss" });
		User user = pack.convert(value, User.class);
		logger.info("call user: " + user.toString());

		client.close();
	}
}
