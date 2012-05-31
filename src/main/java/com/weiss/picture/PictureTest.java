package com.weiss.picture;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class PictureTest {
	public static final String PICURL = "http://www.baidu.com/img/baidu_sylogo1.gif";
	public static final String FILENAME = "baidu_sylogo1.gif";

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
}
