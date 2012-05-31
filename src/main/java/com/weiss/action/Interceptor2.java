package com.weiss.action;

public class Interceptor2 implements Interceptor {

	
	public void intercept(ActionInterceptor interceptor) {
		System.out.println("start2:");
		interceptor.invoke();
		System.out.println("end2:");
	}

}
