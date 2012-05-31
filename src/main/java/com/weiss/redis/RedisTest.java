package com.weiss.redis;

import org.junit.Test;

import redis.clients.jedis.Jedis;

public class RedisTest {

	@Test
	public void testMset() {
		Jedis jedis = new Jedis("localhost");
		jedis.mset("hello", "world", "nihao", "haoma");
		// jedis.mset("hello", "welcome");
		// jedis.mset("hello", "hi");
		System.out.println("size:" + jedis.dbSize());
	}

	@Test
	public void test2() {
		Jedis jedis = new Jedis("localhost");
		String result = jedis.get("server:name");
		System.out.println("server:name is: " + result);
		System.out.println("size: " + jedis.dbSize());
	}

	@Test
	public void test1() {
		System.out.println("Hello World!");

		Jedis jedis = new Jedis("localhost");// redis服务器地址
		// jedis.auth("requiepass");// 验证密码
		jedis.set("java", "java hello world");
		String value = jedis.get("java");
		System.out.println(value);
	}

	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost");
		jedis.set("foo", "bar");
		String value = jedis.get("foo");
		System.out.println(value);

	}

}
