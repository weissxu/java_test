package com.weiss.msgpack.rpc;

public interface Calculator {
	public int add(int num1, int num2);

	public int subtract(int num1, int num2);

	public int multiply(int num1, int num2);

	public int divide(int num1, int num2);

	public User getUser(String name);
}
