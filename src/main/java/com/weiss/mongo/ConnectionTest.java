package com.weiss.mongo;

import java.util.HashMap;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;

public class ConnectionTest {

	public static void main(String[] args) throws Exception {
		Mongo mongo = null;
		// 创建一个Mongo实例
		mongo = new Mongo("localhost", 27017);
		System.out.println("The Database names are : " + mongo.getDatabaseNames());
		System.out.println("The Database diver version is : " + mongo.getVersion());
		// 获取一个数据库
		DB db = mongo.getDB("test");
		System.out.println("The Test connections are : " + db.getCollectionNames());
		// 获取数据库连接
		DBCollection conns = db.getCollection("student");
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("name", "five");
		m.put("age", 10);
		m.put("class", "no1");
		conns.insert(new BasicDBObject(m));
		// 获取数据库游标
		DBCursor cur = conns.find();
		while (cur.hasNext()) {
			System.out.println(cur.next());
		}
		System.out.println(cur.count());
		System.out.println(cur.getCursorId());

		mongo.close();
	}

}