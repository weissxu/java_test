package com.weiss.j2se.io.decorator;

public class ConcreteDecorator1 extends Decorator {

	public ConcreteDecorator1(Component comp) {
		super(comp);
	}
	
	
	public void doSomething() {
		super.doSomething();
		this.doAnother();
	}

	private void doAnother() {
		System.out.println("function: B");
	}
}
