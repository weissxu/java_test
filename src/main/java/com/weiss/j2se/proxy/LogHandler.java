package com.weiss.j2se.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {
	private Object targetObject;

	public Object newProxyObject(Object targetObject) {
		this.targetObject = targetObject;

		return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		System.out.println(proxy.getClass());
		Object ret = null;
		if ("addUser".equals(method.getName())) {
			System.out.println("-------begin time:" + System.currentTimeMillis());
			ret = method.invoke(targetObject, args);
			System.out.println("-------end time:" + System.currentTimeMillis());
		} else
			ret = method.invoke(targetObject, args);
		return ret;
	}

}
