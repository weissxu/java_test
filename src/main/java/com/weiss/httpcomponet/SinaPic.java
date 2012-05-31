package com.weiss.httpcomponet;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class SinaPic {
	// https://api.weibo.com/oauth2/authorize?client_id=1988842715&redirect_uri=http://weibo.com/jx/pic.php&response_type=code
	// userId passwd
	@Test
	public void testPicContent() throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("http://weibo.com");
		post.setHeader("User-Agent", "Mozilla/5.0 (X11; Linux i686; rv:5.0) Gecko/20100101 Firefox/5.0");
		post.setHeader("Referer", "http://weibo.com/");
		post.setHeader("Content-Type", "application/x-www-form-urlencoded"); // 登录表单的信息
		List<NameValuePair> qparams = new ArrayList<NameValuePair>();
		qparams.add(new BasicNameValuePair("Referer",
				"http://weibo.com/login.php?url=http%3A%2F%2Fweibo.com%2Fjx%2Fpic.php%3Fcode%3D9d1b221a7f23a1ac7561de9bd4b88565"));
		// qparams.add(new BasicNameValuePair("gateway", "1"));
		// qparams.add(new BasicNameValuePair("from", ""));
		// qparams.add(new BasicNameValuePair("savestate", "0"));
		// qparams.add(new BasicNameValuePair("useticket", "1"));
		// qparams.add(new BasicNameValuePair("ssosimplelogin", "1"));
		// qparams.add(new BasicNameValuePair("service", "miniblog"));
		qparams.add(new BasicNameValuePair("encoding", "utf-8"));
		qparams.add(new BasicNameValuePair("loginname", "xusiwei@mitao.cn"));
		qparams.add(new BasicNameValuePair("password", "qwer1234"));
		UrlEncodedFormEntity params = new UrlEncodedFormEntity(qparams, "UTF-8");
		post.setEntity(params);
		post.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
		// Execute the request
		HttpResponse response = client.execute(post);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			String content = EntityUtils.toString(entity, "utf-8");
			System.out.println(content);
		}
		client.getConnectionManager().shutdown();
	}
}
