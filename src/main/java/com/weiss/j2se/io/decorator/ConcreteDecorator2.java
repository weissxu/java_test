package com.weiss.j2se.io.decorator;

public class ConcreteDecorator2 extends Decorator {

	public ConcreteDecorator2(Component comp) {
		super(comp);
	}
	
	
	public void doSomething() {
		super.doSomething();
		this.doAnother();
	}

	private void doAnother() {
		System.out.println("function: C");
	}
}
