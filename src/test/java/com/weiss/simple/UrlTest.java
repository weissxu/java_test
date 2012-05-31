package com.weiss.simple;

import java.net.URL;

import org.junit.Test;

public class UrlTest {
	@Test
	public void testTo() throws Exception{
		URL url=new URL("http://www.baidu.com");
		System.out.println(url.openConnection());
		System.out.println(url.toExternalForm());
	}
}
