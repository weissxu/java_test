package com.weiss.msgpack.rpc;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CalculatorHandler {

	private static Map<String, User> userMap = new HashMap<String, User>();

	static {
		User user1 = new User(1, "weiss", new Date(), 10);
		User user2 = new User(2, "mike", new Date(), 20);
		userMap.put("weiss", user1);
		userMap.put("mike", user2);
	}

	public int add(int num1, int num2) {
		return num1 + num2;
	}

	public int subtract(int num1, int num2) {
		return num1 - num2;
	}

	public int multiply(int num1, int num2) {
		return num1 * num2;
	}

	public int divide(int num1, int num2) {
		return num1 / num2;
	}

	public static User getUser(String name) {
		return userMap.get(name);
	}
}
