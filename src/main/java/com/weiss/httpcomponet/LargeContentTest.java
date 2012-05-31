package com.weiss.httpcomponet;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class LargeContentTest {
	private static final HttpClient client = new DefaultHttpClient();
	private static final String YOUKU = "http://dv.youku.com/ycdy/ycdy/_page3917_1.html";
	private static final String SINA = "http://www.sina.com.cn";

	@Test
	public void testYouku() throws ClientProtocolException, IOException {
		testContent(YOUKU);
	}

	@Test
	public void testSina() throws ClientProtocolException, IOException {
		testContent(SINA);
	}

	public void testContent(String url) throws ClientProtocolException, IOException {
		HttpPost post = new HttpPost(url);
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			System.out.println(EntityUtils.toString(entity));
		}
		client.getConnectionManager().shutdown();
	}
}
