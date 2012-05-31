package com.weiss.httpcomponet;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class CookieTest {
	// USRHAJAWB=usrmdins13128; SinaRot//=81; _s_tentry=-; UOR=,weibo.com,;
	// Apache=1083137524690.5939.1333001251562;
	// SINAGLOBAL=1083137524690.5939.1333001251562;
	// ULV=1333001251572:1:1:1:1083137524690.5939.1333001251562:;
	// USRHAWB=usrmdins1465;
	// SUS=SID-2683917293-1333001278-XD-jhynm-5785835d871b866af7236d4f76e1a744;
	// SUE=es%3Db176b7cbeeca6a7408d2da31d0299e63%26ev%3Dv1%26es2%3D4282389fbbe9f9eb27086f901ebef53a%26rs0%3DqAjYE0cjY%252FC0052k6QOy39fezBVUdKGJmhqO4UN07BxqOkr1ExWxAi6vGNicZQ9i5dTxpExf8o0O1pCqcxdDZE2kKZZaUTazXuLZT3ROiaiqBrRI%252B79n1c54cG8xaTUswenLy0av%252FeAamQMMqdCOeY6V3CFZVs4UJWhgT%252BbEuCA%253D%26rv%3D0;
	// SUP=cv%3D1%26bt%3D1333001278%26et%3D1333087678%26d%3Dc909%26i%3Db20d%26us%3D1%26vf%3D0%26vt%3D0%26ac%3D2%26uid%3D2683917293%26user%3Dxusiwei%2540mitao.cn%26ag%3D4%26name%3Dxusiwei%2540mitao.cn%26nick%3Dweissxu%26fmp%3D%26lcp%3D;
	// ALF=1333606078; SSOLoginState=1333001278; wvr=3.6; un=xusiwei@mitao.cn;
	// ads_ck=1; __utma=15428400.688262555.1333001309.1333001309.1333001309.1;
	// __utmc=15428400;
	// __utmz=15428400.1333001309.1.1.utmcsr=plaza.weibo.com|utmccn=(referral)|utmcmd=referral|utmcct=/
	private static final String WEIBO_URL = "http://weibo.com/jx/pic.php?topnav=1";
	private static HttpClient httpclient = new DefaultHttpClient();

	@Test
	public void testOrig() throws ClientProtocolException, IOException {
		HttpUriRequest get = new HttpGet(WEIBO_URL);
		HttpResponse response = httpclient.execute(get);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			System.out.println(EntityUtils.toString(entity));
			EntityUtils.consume(entity);
		}
	}

	@Test
	public void testCookie() throws ClientProtocolException, IOException {
		// HttpPost post = new HttpPost(WEIBO_URL);
		//
		// post.s.HttpResponse response = httpclient.execute(post);
		// HttpEntity entity = response.getEntity();
		// if (entity != null) {
		// System.out.println(EntityUtils.toString(entity));
		// EntityUtils.consume(entity);
		// }
	}
}
