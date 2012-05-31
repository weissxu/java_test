package com.weiss.j2se;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class StringTest {
	@Test
	public void testSubString() {
		String result = new String("hello").substring(0, 20);
		System.out.println(result);
	}

	@Test
	public void testSub2() {
		String result = StringUtils.substring("hello", 0, 100);
		System.out.println(result.length());
		System.out.println(result);
	}
}
