package com.weiss.j2se.io.decorator;

public class Client {
	public static void main(String[] args) {
		Component comp=new ConcreteComponent();
		Component comp1=new ConcreteDecorator1(comp);
		Component comp2=new ConcreteDecorator2(comp1);
		comp2.doSomething();
	}
}
