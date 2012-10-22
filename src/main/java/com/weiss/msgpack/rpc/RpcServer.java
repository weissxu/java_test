package com.weiss.msgpack.rpc;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.msgpack.MessagePack;
import org.msgpack.rpc.Server;
import org.msgpack.rpc.loop.EventLoop;
import org.msgpack.rpc.reflect.Reflect;

public class RpcServer {

	// private static String rpcIp = "127.0.0.1";
	private static int rpcPort = 8080;

	static MessagePack msgpack = new MessagePack();
	static ReflectionMethodDispatcher dp = null;
	static Server server = new Server(EventLoop.defaultEventLoop());

	public static void main(String[] args) throws Exception, IllegalAccessException {

		register(CalculatorHandler.class, new Class<?>[] { User.class });

		start();
	}

	private static void start() throws Exception {
		server.serve(dp);
		EventLoop loop = EventLoop.start(msgpack);
		System.out.println(">>>>> RPC 服务器启动...");
		server.listen(rpcPort);
		loop.join();
	}

	private static void register(Class<?> clazz, Class<?>[] classes) throws InstantiationException, IllegalAccessException {
		if (classes != null) {
			for (int i = 0; i < classes.length; i++) {
				if (classes[i] != null) {
					msgpack.register(classes[i]);
				}
			}
		}

		List<Method> methods = new ArrayList<Method>();
		for (Method method : clazz.getDeclaredMethods()) {
			methods.add(method);
		}

		dp = new ReflectionMethodDispatcher(msgpack, new Reflect(msgpack), clazz.newInstance(), methods.toArray(new Method[0]));
	}
}
