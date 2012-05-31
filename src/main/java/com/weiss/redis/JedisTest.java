package com.weiss.redis;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisDataException;
import redis.clients.util.SafeEncoder;

import com.weiss.redis.commands.JedisCommandTestBase;

public class JedisTest extends JedisCommandTestBase {

	@Test
	public void useWithoutConnecting() {
		Jedis jedis = new Jedis("localhost");
		// jedits.auth("foobared");
		System.out.println("size:" + jedis.dbSize());
	}

	@Test
	public void checkBinaryData() {
		byte[] bigdata = new byte[1777];
		for (int b = 0; b < bigdata.length; b++) {
			bigdata[b] = (byte) ((byte) b % 255);
		}
		Map<String, String> hash = new HashMap<String, String>();
		hash.put("data", SafeEncoder.encode(bigdata));

		String status = jedis.hmset("foo", hash);

		System.out.println("size:" + jedis.dbSize());

		assertEquals("OK", status);
		assertEquals(hash, jedis.hgetAll("foo"));
	}

	@Test
	public void connectWithShardInfo() {
		JedisShardInfo shardInfo = new JedisShardInfo("localhost", Protocol.DEFAULT_PORT);
		// shardInfo.setPassword("foobared");
		Jedis jedis = new Jedis(shardInfo);
		System.out.println(jedis.get("foo"));
	}

	@Test(expected = JedisConnectionException.class)
	public void timeoutConnection() throws Exception {
		jedis = new Jedis("localhost", 6379, 10000);
		// jedits.auth("foobared");
		jedis.configSet("timeout", "1");
		// we need to sleep a long time since redis check for idle connections
		// every 10 seconds or so
		Thread.sleep(15000);
		System.out.println(jedis.hmget("foobar", "foo"));
	}

	@Test(expected = JedisDataException.class)
	public void failWhenSendingNullValues() {
		jedis.set("foo", null);
	}

	@Test
	public void shouldReconnectToSameDB() throws IOException {
		jedis.select(1);
		jedis.set("foo", "bar");
		jedis.getClient().getSocket().shutdownInput();
		jedis.getClient().getSocket().shutdownOutput();
		assertEquals("bar", jedis.get("foo"));
	}
}