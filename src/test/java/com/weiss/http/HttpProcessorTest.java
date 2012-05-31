package com.weiss.http;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.BasicHttpProcessor;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.RequestConnControl;
import org.apache.http.protocol.RequestContent;
import org.apache.http.protocol.RequestExpectContinue;
import org.apache.http.protocol.RequestTargetHost;
import org.apache.http.protocol.RequestUserAgent;
import org.junit.Test;

public class HttpProcessorTest {
	@Test
	public void testInterceptor() throws IOException, HttpException{
		BasicHttpProcessor httpproc = new BasicHttpProcessor();
		// Required protocol interceptors
		httpproc.addInterceptor(new RequestContent());
		httpproc.addInterceptor(new RequestTargetHost());
		// Recommended protocol interceptors
		httpproc.addInterceptor(new RequestConnControl());
		httpproc.addInterceptor(new RequestUserAgent());
		httpproc.addInterceptor(new RequestExpectContinue());

		HttpContext context = new BasicHttpContext();
		context.setAttribute(ExecutionContext.HTTP_TARGET_HOST,new HttpHost("localhost:8080"));

		HttpRequest request = new BasicHttpRequest("GET", "/");
		httpproc.process(request, context);
	}
}
