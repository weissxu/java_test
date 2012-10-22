package com.weiss.mongo;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.DB;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

public class GridfsTest {

	private static final String SOMETHING = "SOME THING";

	private DB _db;
	private GridFS _fs;

	@Before
	public void startup() throws Exception {
		Mongo mongo = new Mongo("127.0.0.1");
		_db = mongo.getDB("test");
		_fs = new GridFS(_db);
	}

	private void showFiles() {
		DBCursor cursor = _fs.getFileList();
		System.out.println("===============count: " + cursor.size());
		while (cursor.hasNext()) {
			DBObject object = cursor.next();
			System.out.println(object);
		}
	}

	@Test
	public void testInOut() throws Exception {

		showFiles();

		for (int i = 0; i < 10; i++) {
			GridFSInputFile in = _fs.createFile((SOMETHING + i).getBytes());
			in.save();
		}
		// GridFSDBFile out = _fs.findOne(new BasicDBObject("_id", in.getId()));
		// assert (out.getId().equals(in.getId()));
		// assert (out.getChunkSize() == GridFS.DEFAULT_CHUNKSIZE);

		// ByteArrayOutputStream bout = new ByteArrayOutputStream();
		// out.writeTo(bout);
		// String outString = new String(bout.toByteArray());
		// assert (outString.equals(SOMETHING));
		showFiles();
	}
}
