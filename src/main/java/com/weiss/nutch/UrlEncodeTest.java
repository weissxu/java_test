package com.weiss.nutch;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.junit.Test;

public class UrlEncodeTest {
	public static final String url1 = "http://www.topit.me/tag/%E7%88%B1%E6%83%85";
	public static final String url2 = "http://www.topit.me/tag/爱情";

	@Test
	public void testDecode() throws UnsupportedEncodingException {
		assertEquals(URLDecoder.decode(url1, "utf-8"), url2);
	}

	@Test
	public void testEncode() throws UnsupportedEncodingException {
		assertEquals(URLEncoder.encode(url2, ""), url1);
	}
}
