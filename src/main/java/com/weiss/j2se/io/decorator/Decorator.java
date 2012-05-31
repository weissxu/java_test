package com.weiss.j2se.io.decorator;

public class Decorator implements Component {
	private Component comp;

	public Decorator(Component comp) {
		super();
		this.comp = comp;
	}

	public void doSomething() {
		comp.doSomething();
	}

}
