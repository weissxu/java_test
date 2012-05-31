package com.weiss.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

public class ShardedJedisPipelineTest {
	private static HostAndPortUtil.HostAndPort redis1 = HostAndPortUtil.getRedisServers().get(0);
	private static HostAndPortUtil.HostAndPort redis2 = HostAndPortUtil.getRedisServers().get(1);

	private ShardedJedis jedis;

	@Before
	public void setUp() throws Exception {
		Jedis jedis = new Jedis(redis1.host, redis1.port);
		// jedits.auth("foobared");
		jedis.flushAll();
		jedis.disconnect();
		jedis = new Jedis(redis2.host, redis2.port);
		// jedits.auth("foobared");
		jedis.flushAll();
		jedis.disconnect();

		JedisShardInfo shardInfo1 = new JedisShardInfo(redis1.host, redis1.port);
		JedisShardInfo shardInfo2 = new JedisShardInfo(redis2.host, redis2.port);
		shardInfo1.setPassword("foobared");
		shardInfo2.setPassword("foobared");
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		shards.add(shardInfo1);
		shards.add(shardInfo2);
		this.jedis = new ShardedJedis(shards);
	}

	// @Test
	// public void pipeline() throws UnsupportedEncodingException {
	// ShardedJedisPipeline p = jedis.pipelined();
	// p.set("foo", "bar");
	// p.get("foo");
	// List<Object> results = p.syncAndReturnAll();
	//
	// assertEquals(2, results.size());
	// assertEquals("OK", results.get(0));
	// assertEquals("bar", results.get(1));
	// }
	//
	// @Test
	// public void pipelineResponse() {
	// jedis.set("string", "foo");
	// jedis.lpush("list", "foo");
	// jedis.hset("hash", "foo", "bar");
	// jedis.zadd("zset", 1, "foo");
	// jedis.sadd("set", "foo");
	//
	// ShardedJedisPipeline p = jedis.pipelined();
	// Response<String> string = p.get("string");
	// Response<String> list = p.lpop("list");
	// Response<String> hash = p.hget("hash", "foo");
	// Response<Set<String>> zset = p.zrange("zset", 0, -1);
	// Response<String> set = p.spop("set");
	// Response<Boolean> blist = p.exists("list");
	// Response<Double> zincrby = p.zincrby("zset", 1, "foo");
	// Response<Long> zcard = p.zcard("zset");
	// p.lpush("list", "bar");
	// Response<List<String>> lrange = p.lrange("list", 0, -1);
	// Response<Map<String, String>> hgetAll = p.hgetAll("hash");
	// p.sadd("set", "foo");
	// Response<Set<String>> smembers = p.smembers("set");
	// Response<Set<Tuple>> zrangeWithScores = p.zrangeWithScores("zset", 0,
	// -1);
	// p.sync();
	//
	// assertEquals("foo", string.get());
	// assertEquals("foo", list.get());
	// assertEquals("bar", hash.get());
	// assertEquals("foo", zset.get().iterator().next());
	// assertEquals("foo", set.get());
	// assertFalse(blist.get());
	// assertEquals(Double.valueOf(2), zincrby.get());
	// assertEquals(Long.valueOf(1), zcard.get());
	// assertEquals(1, lrange.get().size());
	// assertNotNull(hgetAll.get().get("foo"));
	// assertEquals(1, smembers.get().size());
	// assertEquals(1, zrangeWithScores.get().size());
	// }
	//
	// @Test(expected = JedisDataException.class)
	// public void pipelineResponseWithinPipeline() {
	// jedis.set("string", "foo");
	//
	// ShardedJedisPipeline p = jedis.pipelined();
	// Response<String> string = p.get("string");
	// string.get();
	// p.sync();
	// }
	//
	// @Test
	// public void canRetrieveUnsetKey() {
	// ShardedJedisPipeline p = jedis.pipelined();
	// Response<String> shouldNotExist = p.get(UUID.randomUUID().toString());
	// p.sync();
	// assertNull(shouldNotExist.get());
	// }
}