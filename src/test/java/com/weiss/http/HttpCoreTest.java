package com.weiss.http;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Test;

public class HttpCoreTest {
	@Test
	public void testVersion() {
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");

		System.out.println(response.getProtocolVersion());
		System.out.println(response.getStatusLine().getStatusCode());
		System.out.println(response.getStatusLine().getReasonPhrase());
		System.out.println(response.getStatusLine().toString());
	}

	@Test
	public void testHeader() {
		HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
		response.addHeader("Set-Cookie", "c1=a; path=/; domain=localhost");
		response.addHeader("Set-Cookie", "c2=b; path=\"/\", c3=c; domain=\"localhost\"");
		Header h1 = response.getFirstHeader("Set-Cookie");
//		System.out.println(h1);
//		Header h2 = response.getLastHeader("Set-Cookie");
//		System.out.println(h2);
//		Header[] hs = response.getHeaders("Set-Cookie");
//		System.out.println(hs.length);
		HeaderElement[] eles = h1.getElements();
		for (HeaderElement ele : eles)
			System.out.println(ele);
		HeaderIterator iterator = response.headerIterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
	}
}
