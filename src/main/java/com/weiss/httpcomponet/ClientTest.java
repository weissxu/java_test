package com.weiss.httpcomponet;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class ClientTest {

	public static final String PICURL = "http://www.baidu.com/img/baidu_sylogo1.gif";
	public static final String FILENAME = "baidu_sylogo1.gif";
	private static final String BAIDU = "http://www.baidu.com";
	private static final String BAIDU1 = "http://www.baidu.com/s?wd=hello&rsv_bp=0&rsv_spt=3&inputT=1102";

	@Test
	public void testGetContent() throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();

		HttpUriRequest request = new HttpGet(BAIDU);

		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			System.out.println(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		}
	}

	@Test
	public void testConnManager() throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient(new ThreadSafeClientConnManager());

		HttpUriRequest request = new HttpGet(BAIDU);

		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			// String content = EntityUtils.toString(entity);
			System.out.println("----------------------before consume ");
			System.out.println(entity.getContentLength());
			// EntityUtils.consume(entity);
			System.out.println("----------------------after consume ");
		}

		HttpUriRequest request1 = new HttpGet(BAIDU1);

		HttpResponse response1 = client.execute(request1);
		HttpEntity entity1 = response1.getEntity();
		if (entity1 != null) {
			// String content = EntityUtils.toString(entity);
			System.out.println("------------------------before consume 1 ");
			System.out.println(entity.getContentLength());
			// EntityUtils.consume(entity1);
			System.out.println("------------------------after consume 1 ");
		}
	}

	@Test
	public void testDownload() throws ClientProtocolException, IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(PICURL);
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();

		FileUtils.writeByteArrayToFile(new File(FILENAME), EntityUtils.toByteArray(entity));
		EntityUtils.consume(entity);
		System.out.println("success!!");
	}

	public static void main(String[] args) throws InterruptedException {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));

		ClientConnectionManager cm = new ThreadSafeClientConnManager(schemeRegistry);
		HttpClient httpClient = new DefaultHttpClient(cm);

		// URIs to perform GETs on
		String[] urisToGet = { "http://www.baidu.com/", "http://www.google.com/s?wd=hello",
				"http://www.sina.com/s?wd=welcome", "http://www.163.com/s?wd=hi" };

		// create a thread for each URI
		GetThread[] threads = new GetThread[urisToGet.length];
		for (int i = 0; i < threads.length; i++) {
			HttpUriRequest httpget = new HttpHead(urisToGet[i]);
			threads[i] = new GetThread(httpClient, httpget);
		}

		// start the threads
		for (int j = 0; j < threads.length; j++) {
			threads[j].start();
		}

		// join the threads
		for (int j = 0; j < threads.length; j++) {
			threads[j].join();
		}

		cm.shutdown();
	}

	static class GetThread extends Thread {

		private final HttpClient httpClient;
		private final HttpContext context;
		private final HttpUriRequest httpget;

		public GetThread(HttpClient httpClient, HttpUriRequest httpget) {
			this.httpClient = httpClient;
			this.context = new BasicHttpContext();
			this.httpget = httpget;
		}

		@Override
		public void run() {
			try {
				HttpResponse response = this.httpClient.execute(this.httpget, this.context);
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					System.out.println(entity);
				}
				// ensure the connection gets released to the manager
				EntityUtils.consume(entity);
			} catch (Exception ex) {
				this.httpget.abort();
			}
		}

	}
}
