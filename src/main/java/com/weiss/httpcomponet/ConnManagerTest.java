package com.weiss.httpcomponet;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class ConnManagerTest {

	@Test
	public void testShutdown() throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet("http://www.google.com/");
		HttpResponse response = httpclient.execute(httpget);
		HttpEntity entity = response.getEntity();
		System.out.println(response.getStatusLine());
		if (entity != null) {
			entity.consumeContent();
		}

		httpclient.getConnectionManager().shutdown();
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testPoolManager() {
		HttpParams params = new BasicHttpParams();
		// 增加最大连接到200
		ConnManagerParams.setMaxTotalConnections(params, 200);
		// 增加每个路由的默认最大连接到20
		ConnPerRouteBean connPerRoute = new ConnPerRouteBean(20);
		// 对localhost:80增加最大连接到50
		HttpHost localhost = new HttpHost("locahost", 80);
		connPerRoute.setMaxForRoute(new HttpRoute(localhost), 50);
		ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
		HttpClient httpClient = new DefaultHttpClient(cm, params);
	}

	@Test
	public void testSigleManager() throws InterruptedException, IOException, HttpException {
		HttpParams params = new BasicHttpParams();
		Scheme http = new Scheme("http", PlainSocketFactory.getSocketFactory(), 80);
		SchemeRegistry sr = new SchemeRegistry();
		sr.register(http);
		ClientConnectionManager connMrg = new SingleClientConnManager(params, sr);
		// 请求新连接。这可能是一个很长的过程。
		HttpRoute route = new HttpRoute(new HttpHost("192.168.0.1", 80));
		ClientConnectionRequest connRequest = connMrg.requestConnection(route, null);
		// 等待连接10秒
		ManagedClientConnection conn = connRequest.getConnection(10, TimeUnit.SECONDS);
		try {
			BasicHttpRequest request = new BasicHttpRequest("GET", "http://www.baidu.com/");
			conn.open(route, null, params);
			conn.sendRequestHeader(request);
			HttpResponse response = conn.receiveResponseHeader();
			conn.receiveResponseEntity(response);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// BasicManagedEntity managedEntity = new
				// BasicManagedEntity(entity, conn, true);
				// // 替换实体
				// response.setEntity(managedEntity);
				System.out.println(EntityUtils.toString(entity));
			}
			// HttpResponse response = conn.receiveResponseHeader();
			// System.out.println(response.getStatusLine());
			conn.releaseConnection();
		} catch (IOException ex) {
			// 在I/O error之上终止连接。
			conn.abortConnection();
			throw ex;
		}
	}
}
