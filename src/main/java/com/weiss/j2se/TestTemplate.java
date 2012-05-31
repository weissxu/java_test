package com.weiss.j2se;

abstract class AbstractClass{
	protected static final String METHOD_1="op1";
	protected static final String METHOD_2="op2";
	public final void method(String methodName){
		if(METHOD_1.equals(methodName)){
			method1(methodName);
		}
		if(METHOD_2.equals(methodName)){
			method2(methodName);
		}
	}
	public abstract void method1(String methodName);
	public abstract void method2(String methodName);
}
class ConcreteClass extends AbstractClass{

	
	public void method1(String methodName) {
		if(METHOD_1.equals("op1"))
			System.out.println("method1---------");
	}
	
	public void method2(String methodName) {
		System.out.println("----------method2---------");
	}
	
}
public class TestTemplate {

	public static void main(String[] args) {
		AbstractClass ac=new ConcreteClass();
		ac.method("op1");
	}
}
