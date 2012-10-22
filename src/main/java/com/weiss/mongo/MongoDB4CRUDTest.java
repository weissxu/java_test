package com.weiss.mongo;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryOperators;
import com.mongodb.util.JSON;

public class MongoDB4CRUDTest {

	private Mongo mg = null;
	private DB db;
	private DBCollection users;

	@Before
	public void before() throws UnknownHostException {
		mg = new Mongo();
		db = mg.getDB("test");
		users = db.getCollection("users");
	}

	@After
	public void teardown() {
		mg.close();
	}

	private void queryAll() {
		System.out.println("查询users的所有数据：");
		// db游标
		DBCursor cur = users.find();
		System.out.println("count: " + users.count());
		while (cur.hasNext()) {
			DBObject next = cur.next();
			System.out.println(next);
		}
	}

	@Test
	public void add() {
		// 先查询所有数据
		queryAll();

		List<DBObject> list = new ArrayList<DBObject>();
		for (int i = 1; i < 10; i++) {

			DBObject user = new BasicDBObject();
			user.put("name", "weiss" + i);
			user.put("age", 24 + i);
			user.put("sex", i % 2 == 0 ? "男" : "女");
			list.add(user);
		}
		users.insert(list);

		queryAll();
	}

	@Test
	public void remove() {
		System.out.println("remove age >= 30: " + users.remove(new BasicDBObject("age", new BasicDBObject("$gte", 30))).getN());
		queryAll();
	}

	@Test
	public void modify() {
		System.out.println("修改："
				+ users.update(new BasicDBObject("_id", new ObjectId("506d2072151d478e5617458e")),
						new BasicDBObject("$set", new BasicDBObject("age", 99))).getN());
		System.out.println("修改："
				+ users.update(new BasicDBObject("name", "haha"), new BasicDBObject("name", "dingding"), true, true).getN());
		queryAll();
	}

	@Test
	public void query() {
		// 查询age = 24
		System.out.println("find age = 25: " + users.find(new BasicDBObject("age", 24)).toArray());

		// 查询age >= 24
		System.out.println("find age >= 25: " + users.find(new BasicDBObject("age", new BasicDBObject("$gte", 24))).toArray());
		System.out.println("find age <= 25: " + users.find(new BasicDBObject("age", new BasicDBObject("$lte", 24))).toArray());

		System.out.println("查询age!=25：" + users.find(new BasicDBObject("age", new BasicDBObject("$ne", 25))).toArray());
		System.out.println("查询age in 25/26/27："
				+ users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.IN, new int[] { 25, 26, 27 }))).toArray());
		System.out.println("查询age not in 25/26/27："
				+ users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.NIN, new int[] { 25, 26, 27 }))).toArray());
		System.out.println("查询age exists 排序："
				+ users.find(new BasicDBObject("age", new BasicDBObject(QueryOperators.EXISTS, true))).toArray());

		System.out.println("只查询age属性：" + users.find(null, new BasicDBObject("age", true)).toArray());

		// 只查询一条数据，多条去第一条
		System.out.println("findOne: " + users.findOne());
		System.out.println("findOne: " + users.findOne(new BasicDBObject("age", 26)));
		System.out.println("findOne: " + users.findOne(new BasicDBObject("age", 26), new BasicDBObject("name", true)));

		// 查询修改、删除
		System.out.println("findAndRemove 查询age=25的数据，并且删除: " + users.findAndRemove(new BasicDBObject("age", 25)));

		// 查询age=26的数据，并且修改name的值为Abc
		System.out.println("findAndModify: " + users.findAndModify(new BasicDBObject("age", 26), new BasicDBObject("name", "Abc")));
		System.out.println("findAndModify: " + users.findAndModify(new BasicDBObject("age", 28), // 查询age=28的数据
				new BasicDBObject("name", true), // 查询name属性
				new BasicDBObject("age", true), // 按照age排序
				false, // 是否删除，true表示删除
				new BasicDBObject("name", "Abc"), // 修改的值，将name修改成Abc
				true, true));

		queryAll();
	}

	// mongoDB不支持联合查询、子查询，这需要我们自己在程序中完成。将查询的结果集在Java查询中进行需要的过滤即可。

	@Test
	public void testOthers() {
		DBObject user = new BasicDBObject();
		user.put("name", "hoojo");
		user.put("age", 24);

		// JSON 对象转换
		System.out.println("serialize: " + JSON.serialize(user));
		// 反序列化
		System.out.println("parse: " + JSON.parse("{ \"name\" : \"hoojo\" , \"age\" : 24}"));

		System.out.println("判断temp Collection是否存在: " + db.collectionExists("temp"));

		// 如果不存在就创建
		if (!db.collectionExists("temp")) {
			DBObject options = new BasicDBObject();
			options.put("size", 20);
			options.put("capped", 20);
			options.put("max", 20);
			System.out.println(db.createCollection("account", options));
		}

		// 设置db为只读
		db.setReadOnly(true);

		// 只读不能写入数据
		db.getCollection("test").save(user);
		System.out.println(db.getOptions());
	}
}