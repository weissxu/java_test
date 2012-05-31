package com.weiss.action;

public class Interceptor1 implements Interceptor {

	
	public void intercept(ActionInterceptor interceptor) {
		System.out.println("start1:");
		interceptor.invoke();
		System.out.println("end1:");
	}

}
