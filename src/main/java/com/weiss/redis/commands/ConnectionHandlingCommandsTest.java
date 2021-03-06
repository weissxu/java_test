package com.weiss.redis.commands;

import org.junit.Test;

import redis.clients.jedis.BinaryJedis;

import com.weiss.redis.HostAndPortUtil;
import com.weiss.redis.HostAndPortUtil.HostAndPort;

public class ConnectionHandlingCommandsTest extends JedisCommandTestBase {
	protected static HostAndPort hnp = HostAndPortUtil.getRedisServers().get(0);

	@Test
	public void quit() {
		assertEquals("OK", jedis.quit());
	}

	@Test
	public void binary_quit() {
		BinaryJedis bj = new BinaryJedis(hnp.host);
		assertEquals("OK", bj.quit());
	}
}