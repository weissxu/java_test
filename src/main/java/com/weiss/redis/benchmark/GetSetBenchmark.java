package com.weiss.redis.benchmark;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Calendar;

import redis.clients.jedis.Jedis;

import com.weiss.redis.HostAndPortUtil;
import com.weiss.redis.HostAndPortUtil.HostAndPort;

public class GetSetBenchmark {
	private static HostAndPort hnp = HostAndPortUtil.getRedisServers().get(0);
	private static final int TOTAL_OPERATIONS = 100000;

	public static void main(String[] args) throws UnknownHostException, IOException {
		Jedis jedis = new Jedis(hnp.host, hnp.port);
		jedis.connect();
		// jedits.auth("foobared");
		jedis.flushAll();

		long begin = Calendar.getInstance().getTimeInMillis();

		for (int n = 0; n <= TOTAL_OPERATIONS; n++) {
			String key = "foo" + n;
			jedis.set(key, "bar" + n);
			jedis.get(key);
		}

		long elapsed = Calendar.getInstance().getTimeInMillis() - begin;

		jedis.disconnect();

		System.out.println(((1000 * 2 * TOTAL_OPERATIONS) / elapsed) + " ops");
	}
}