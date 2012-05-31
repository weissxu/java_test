package com.weiss.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.http.HeaderIterator;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpConnectionMetrics;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultHttpClientConnection;
import org.apache.http.impl.DefaultHttpServerConnection;
import org.apache.http.impl.SocketHttpClientConnection;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpProcessor;
import org.apache.http.protocol.HttpRequestExecutor;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpConnTest {
	@Test
	public void testConn1() throws IOException {

		Socket socket = new Socket("localhost", 8080);
		// Initialize socket
		BasicHttpParams params = new BasicHttpParams();
		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		conn.bind(socket, params);
		System.out.println(conn.isOpen());
		HttpConnectionMetrics metrics = conn.getMetrics();
		System.out.println("getRequestCount: " + metrics.getRequestCount());
		System.out.println("getResponseCount: " + metrics.getResponseCount());
		System.out.println("getReceivedBytesCount: " + metrics.getReceivedBytesCount());
		System.out.println("getSentBytesCount: " + metrics.getSentBytesCount());
	}

	@Test
	// failed to execute....
	public void testConn2() throws HttpException, IOException {
		Socket socket = new Socket("localhost", 8888);
		// Initialize socket
		socket.setSoTimeout(0);
		HttpParams params = new BasicHttpParams();
		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		conn.bind(socket, params);
		HttpRequest request = new BasicHttpRequest("GET", "/");
		conn.sendRequestHeader(request);
		// HttpResponse response = conn.receiveResponseHeader();
		// conn.receiveResponseEntity(response);
		// HttpEntity entity = response.getEntity();
		// if (entity != null) {
		//
		// System.out.println(EntityUtils.toString(entity));
		// // Do something useful with the entity and, when done, ensure all
		// // content has been consumed, so that the underlying connection
		// // can be re-used
		// EntityUtils.consume(entity);
		// }
		System.in.read();
	}

	@Test
	public void testConn3() throws HttpException, IOException {
		Socket socket = new Socket("localhost", 8888);
		// Initialize socket
		socket.setSoTimeout(0);
		HttpParams params = new BasicHttpParams();
		DefaultHttpClientConnection conn = new DefaultHttpClientConnection();
		conn.bind(socket, params);
		params.setParameter("connection", "Keep-Alive");

		HttpProcessor httpproc = new BasicHttpProcessor();
		HttpContext context = new BasicHttpContext();

		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST, new HttpHost("localhost:8888"));

		HttpRequestExecutor httpexecutor = new HttpRequestExecutor();

		BasicHttpRequest request = new BasicHttpRequest("GET", "/");
		request.setParams(params);
		httpexecutor.preProcess(request, httpproc, context);
		HttpResponse response = httpexecutor.execute(request, conn, context);
		response.setParams(params);
		httpexecutor.postProcess(response, httpproc, context);

		HttpEntity entity = response.getEntity();
		System.out.println(EntityUtils.toString(entity));
		EntityUtils.consume(entity);
	}

	public static void main(String[] args) throws Exception {
		Socket socket;

		ServerSocket server = new ServerSocket(8888, 1, InetAddress.getByName("localhost"));
		System.out.println("start...");
		socket = server.accept();
		System.out.println("accepted...");
		// Initialize socket
		HttpParams params = new BasicHttpParams();
		DefaultHttpServerConnection conn = new DefaultHttpServerConnection();
		conn.bind(socket, params);
		HttpRequest request = conn.receiveRequestHeader();
		HeaderIterator itr = request.headerIterator();
		while(itr.hasNext())
			System.out.println("header: "+itr.next());
		if (request instanceof HttpEntityEnclosingRequest) {
			conn.receiveRequestEntity((HttpEntityEnclosingRequest) request);
			HttpEntity entity = ((HttpEntityEnclosingRequest) request).getEntity();
			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
				// Do something useful with the entity and, when done, ensure
				// all
				// content has been consumed, so that the underlying connection
				// coult be re-used
				EntityUtils.consume(entity);
			}
		}
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK");
		response.setEntity(new StringEntity("Got it\r\n\r\n"));
		conn.sendResponseHeader(response);
		conn.sendResponseEntity(response);
		Thread.sleep(1000);
	}
}
