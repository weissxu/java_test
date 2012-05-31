package com.weiss.httpcomponet;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpHost;
import org.apache.http.client.utils.URIUtils;
import org.junit.Test;

public class UriUtilTest {
	public static final String BAIDU = "http://www.baidu.com";
	public static final String BAIDU1 = "www.baidu.com";
	public static final String BAIDU2 = "http://www.baidu.com/s?wd=hello&rsv_bp=0&rsv_spt=3&inputT=1102";
	public static final List<String> uris = Arrays.asList(BAIDU, BAIDU1, BAIDU2);

	@Test
	public void test1() throws URISyntaxException {
		for (String uriStr : uris) {
			URI uri = new URI(uriStr);
			if (!uri.isAbsolute()) {
				System.out.println(uri);
			} else {
				HttpHost host = URIUtils.extractHost(uri);
				System.out.println(host);
			}
			System.out.println("-----------");
		}
	}
}
