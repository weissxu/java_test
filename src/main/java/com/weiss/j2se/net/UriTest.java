package com.weiss.j2se.net;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UriTest {

	public static final String BAIDU = "http://www.baidu.com";
	public static final String BAIDU1 = "www.baidu.com";
	public static final String BAIDU2 = "http://www.baidu.com/s?wd=hello&rsv_bp=0&rsv_spt=3&inputT=1102";
	public static final List<String> uris = Arrays.asList(BAIDU, BAIDU1, BAIDU2);

	@Test
	public void test1() throws URISyntaxException {
		for (String uriStr : uris) {
			URI uri = new URI(uriStr);
			showInfo(uri);
		}

	}

	private void showInfo(URI uri) {
		System.out.println(uri);
		System.out.println("path: " + uri.getPath());
		System.out.println("secheme: " + uri.getScheme());
		System.out.println("port: " + uri.getPort());
		System.out.println("host: " + uri.getHost());
		System.out.println("-----------------");
	}
}
