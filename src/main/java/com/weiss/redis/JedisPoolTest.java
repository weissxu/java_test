package com.weiss.redis;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool.Config;
import org.junit.Assert;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

import com.weiss.redis.HostAndPortUtil.HostAndPort;

public class JedisPoolTest extends Assert {
	private static HostAndPort hnp = HostAndPortUtil.getRedisServers().get(0);

	@Test
	public void checkConnections() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), hnp.host, hnp.port, 2000);
		Jedis jedis = pool.getResource();
		// jedits.auth("foobared");
		jedis.set("foo", "bar");
		assertEquals("bar", jedis.get("foo"));
		pool.returnResource(jedis);
		pool.destroy();
	}

	@Test
	public void checkConnectionWithDefaultPort() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), hnp.host, hnp.port);
		Jedis jedis = pool.getResource();
		// jedits.auth("foobared");
		jedis.set("foo", "bar1");
		assertEquals("bar1", jedis.get("foo"));
		pool.returnResource(jedis);
		pool.destroy();
	}

	@Test
	public void checkJedisIsReusedWhenReturned() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), hnp.host, hnp.port);
		Jedis jedis = pool.getResource();
		// jedits.auth("foobared");
		jedis.set("foo", "0");
		pool.returnResource(jedis);

		jedis = pool.getResource();
		// jedits.auth("foobared");
		jedis.incr("foo");
		pool.returnResource(jedis);
		pool.destroy();
	}

	@Test
	public void checkPoolRepairedWhenJedisIsBroken() {
		JedisPool pool = new JedisPool(new JedisPoolConfig(), hnp.host, hnp.port);
		Jedis jedis = pool.getResource();
		// jedits.auth("foobared");
		jedis.quit();
		pool.returnBrokenResource(jedis);

		jedis = pool.getResource();
		// jedits.auth("foobared");
		jedis.incr("foo");
		pool.returnResource(jedis);
		pool.destroy();
	}

	@Test(expected = JedisConnectionException.class)
	public void checkPoolOverflow() {
		Config config = new Config();
		config.maxActive = 1;
		config.whenExhaustedAction = GenericObjectPool.WHEN_EXHAUSTED_FAIL;
		JedisPool pool = new JedisPool(config, hnp.host, hnp.port);
		Jedis jedis = pool.getResource();
		// jedits.auth("foobared");
		jedis.set("foo", "0");

		Jedis newJedis = pool.getResource();
		newJedis.auth("foobared");
		newJedis.incr("foo");
	}

	@Test
	public void securePool() {
		JedisPoolConfig config = new JedisPoolConfig();
		config.setTestOnBorrow(true);
		JedisPool pool = new JedisPool(config, hnp.host, hnp.port, 2000, null);
		Jedis jedis = pool.getResource();
		jedis.set("foo", "bar");
		pool.returnResource(jedis);
		pool.destroy();
	}

	@Test
	public void nonDefaultDatabase() {
		JedisPool pool0 = new JedisPool(new JedisPoolConfig(), hnp.host, hnp.port, 2000, "password");
		Jedis jedis0 = pool0.getResource();
		jedis0.set("foo", "bar");
		assertEquals("bar", jedis0.get("foo"));
		pool0.returnResource(jedis0);
		pool0.destroy();

		// JedisPool pool1 = new JedisPool(new JedisPoolConfig(), hnp.host,
		// hnp.port, 2000, "foobared", 1);
		// Jedis jedis1 = pool1.getResource();
		// assertNull(jedis1.get("foo"));
		// pool1.returnResource(jedis0);
		// pool1.destroy();
	}
}