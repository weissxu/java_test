package com.weiss.httpcomponet;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class InterceptorTest {

	public static final String BAIDU = "http://www.baidu.com/";
	public static final String GOOGLE = "http://www.google.com";
	public static final String YOUKU = "http://fun.youku.com/index/focus";
	public static final String YOUKU2 = "http://music.youku.com/search";
	public static final String TOPIT = "http://www.topit.me/item/1000";
	public static final String TOPIT2 = "http://www.topit.me/item/100";

	@Test
	public void testTarget() throws ClientProtocolException, IOException {
		testTarget(YOUKU2);
		// testTarget(TOPIT);
		// testTarget(TOPIT2);
	}

	public void testTarget(String url) throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		AtomicInteger count = new AtomicInteger(0);
		localContext.setAttribute("count", count);

		System.out.println("------------------Host: " + url);
		httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
			@Override
			public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
				String target = ((HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST)).getHostName();
				AtomicInteger count = (AtomicInteger) context.getAttribute("count");
				System.out.println(context.getAttribute(ExecutionContext.HTTP_CONNECTION));
				request.addHeader("Count", Integer.toString(count.getAndIncrement()));
				System.out.println("requestLine.getUri: " + request.getRequestLine().getUri());

				System.out.println("target: " + target);
			}
		});

		HttpGet httpget = new HttpGet(url);
		HttpResponse response = httpclient.execute(httpget, localContext);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			EntityUtils.consume(entity);
		}
		System.out.println("in the end ,the count is: " + count);
	}

	@Test
	public void testIntercept1() throws ClientProtocolException, IOException {
		// new BasicPooledConnAdapter(new ThreadSafeClientConnManager(), null);
		// BasicPooledConnAdapter context = new BasicPooledConnAdapter(null,
		// null);
		HttpHost host = new HttpHost("http://www.topit.me");
		// HttpParams params = new BasicHttpParams();
		// ConnRouteParams.setForcedRoute(params, new HttpRoute(host));
		HttpContext context = new BasicHttpContext();

		DefaultHttpClient httpclient = new DefaultHttpClient(new ThreadSafeClientConnManager());

		HttpGet httpget = new HttpGet(TOPIT2);

		httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
			@Override
			public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
				System.out.println("hostname: "
						+ ((HttpHost) context.getAttribute(ExecutionContext.HTTP_TARGET_HOST)).getHostName());
				System.out.println("requestLine: "
						+ ((HttpRequest) context.getAttribute(ExecutionContext.HTTP_REQUEST)).getRequestLine());
				System.out.println("connection: " + context.getAttribute(ExecutionContext.HTTP_CONNECTION));

				// System.out.println("PROTOCOL_VERSION: "
				// +
				// request.getParams().getParameter(CoreProtocolPNames.PROTOCOL_VERSION));
				// System.out.println("HTTP_CONTENT_CHARSET: "
				// +
				// request.getParams().getParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET));
				// System.out.println("USE_EXPECT_CONTINUE: "
				// +
				// request.getParams().getParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE));
				// System.out.println("STRICT_TRANSFER_ENCODING: "
				// +
				// request.getParams().getParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING));
				System.out.println("-------------------------");
			}
		});
		HttpResponse response = httpclient.execute(host, httpget);
		System.out.println(response.getStatusLine());
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			System.out.println(EntityUtils.toString(entity));
		}
		// httpclient.execute(host, httpget, context);
		httpclient.getConnectionManager().shutdown();
	}

	@Test
	public void testIntercept() throws ClientProtocolException, IOException {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpContext localContext = new BasicHttpContext();
		AtomicInteger count = new AtomicInteger(1);
		localContext.setAttribute("count", count);
		httpclient.addRequestInterceptor(new HttpRequestInterceptor() {
			@Override
			public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
				AtomicInteger count = (AtomicInteger) context.getAttribute("count");
				System.out.println(context.getAttribute(ExecutionContext.HTTP_CONNECTION));
				request.addHeader("Count", Integer.toString(count.getAndIncrement()));
			}
		});

		HttpGet httpget = new HttpGet(BAIDU);
		for (int i = 0; i < 10; i++) {
			HttpResponse response = httpclient.execute(httpget, localContext);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				EntityUtils.consume(entity);
			}
		}
		System.out.println("in the end ,the count is: " + count);
	}
}
