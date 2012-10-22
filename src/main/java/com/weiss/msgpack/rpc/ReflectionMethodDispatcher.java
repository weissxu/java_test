package com.weiss.msgpack.rpc;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.msgpack.MessagePack;
import org.msgpack.rpc.Request;
import org.msgpack.rpc.dispatcher.Dispatcher;
import org.msgpack.rpc.reflect.Invoker;
import org.msgpack.rpc.reflect.InvokerBuilder;
import org.msgpack.rpc.reflect.Reflect;
import org.msgpack.rpc.reflect.ReflectionInvokerBuilder;

/**
 * 
 * 类名: ReflectionMethodDispatcher 类描述: TODO(简单描述)
 * 
 * @version：(当前版本)
 * @since (从哪个版本引入)
 */
public class ReflectionMethodDispatcher implements Dispatcher {

	protected Map<String, Map<String, Invoker>> methodsMap = new HashMap<String, Map<String, Invoker>>();
	protected Map<String, Object> targetMap = new HashMap<String, Object>();
	protected Map<String, Reflect> reflectMap = new HashMap<String, Reflect>();
	protected Map<String, MessagePack> msgpackMap = new HashMap<String, MessagePack>();

	public ReflectionMethodDispatcher(MessagePack msgPack, Reflect reflect, Object target, Method[] methods) {
		targetMap.put(target.getClass().getName(), target);
		reflectMap.put(target.getClass().getName(), reflect);
		msgpackMap.put(target.getClass().getName(), msgPack);
		InvokerBuilder builder = new ReflectionInvokerBuilder(msgPack);// ReflectionInvokerBuilder.getInstance();
		if (!methodsMap.containsKey(target.getClass().getName())) {
			Map<String, Invoker> invokeMap = new HashMap<String, Invoker>();
			for (Method method : methods) {
				invokeMap.put(method.getName(), builder.buildInvoker(method));
			}
			methodsMap.put(target.getClass().getName(), invokeMap);
		}
	}

	public void register(MessagePack msgPack, Reflect reflect, Object target, Method[] methods) {
		targetMap.put(target.getClass().getName(), target);
		reflectMap.put(target.getClass().getName(), reflect);
		msgpackMap.put(target.getClass().getName(), msgPack);
		InvokerBuilder builder = new ReflectionInvokerBuilder(msgPack);
		if (!methodsMap.containsKey(target.getClass().getName())) {
			Map<String, Invoker> invokeMap = new HashMap<String, Invoker>();
			for (Method method : methods) {
				invokeMap.put(method.getName(), builder.buildInvoker(method));
			}
			methodsMap.put(target.getClass().getName(), invokeMap);
		}
	}

	@Override
	public void dispatch(Request request) throws Exception {
		String methodName = request.getMethodName();
		String metNameMod = methodName;
		String className = null;
		int idx = methodName.indexOf(":");
		if (idx != -1) {
			className = methodName.substring(0, idx);
			metNameMod = methodName.substring(idx + 1);
		}
		String targetClassName = null;
		Invoker ivk = null;
		if (className != null) {
			Map<String, Invoker> map = methodsMap.get(methodName.substring(0, idx));
			if (map == null) {
				// FIXME
				throw new IOException(".CallError.NoMethodError");
			}
			ivk = map.get(metNameMod);
		} else {
			Set<Map.Entry<String, Map<String, Invoker>>> entrySet = methodsMap.entrySet();
			for (Entry<String, Map<String, Invoker>> entry : entrySet) {
				if (entry.getValue().containsKey(metNameMod)) {
					targetClassName = entry.getKey();
					ivk = entry.getValue().get(metNameMod);
					break;
				}
			}
		}

		if (ivk == null) {
			// FIXME
			throw new IOException(".CallError.NoMethodError");
		}
		ivk.invoke(targetMap.get(targetClassName), request);
	}
}
