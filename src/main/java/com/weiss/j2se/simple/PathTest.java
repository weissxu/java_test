package com.weiss.j2se.simple;

import org.junit.Test;

public class PathTest {
	@Test
	public void testProp() {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(System.getProperty("user.home"));
	}
}
