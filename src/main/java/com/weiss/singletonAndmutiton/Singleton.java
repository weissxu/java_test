package com.weiss.singletonAndmutiton;

public class Singleton {
	private static Singleton instance = new Singleton();
	private static int m;
	private static int n = 0;
	static {
		System.out.println("==========static 块<clinit>===========");
	}

	private Singleton() {
		m++;
		n++;
		System.out.println("=============<init>=============");
	}

	protected static Singleton getInstance() {
		System.out.println("=============static method()===============");

		return instance;
	}

	public int getM() {
		return m;
	}

	public int getN() {
		return n;
	}
}
