package com.weiss.couchbase;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.couchbase.client.CouchbaseClient;

public class CBTest {
	private static List<URI> baseList;
	private static CouchbaseClient client;

	static {
		try {
			baseList = Arrays.asList(new URI("http://192.168.0.2:8091/pools"));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	@Before
	public void setup() throws IOException {
		client = new CouchbaseClient(baseList, "default", "");
	}

	@After
	public void teardown() {
	}

	@Test
	public void testSetAndGet() {
		client.set("server.name", 0, "weiss-couchbase");
		// client.add("server.name", 0, "weiss-couchbase");
		System.out.println(client.get("server.name"));
	}
}
