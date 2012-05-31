package com.weiss.j2se;

public abstract class AbstractClass1 {
	
	//hadoop test data
	protected static final String METHOD_1="op1";
	protected static final String METHOD_2="op2";
	public  void method(String methodName){
		if(METHOD_1.equals(methodName)){
			method1(methodName);
		}
		if(METHOD_2.equals(methodName)){
			method2(methodName);
		}
	}
	public abstract void method1(String methodName);
	public abstract void method2(String methodName);
	protected void method3(){
		System.out.print("hello");
	}
}
