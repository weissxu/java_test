package com.weiss.guava;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.base.CharMatcher;

public class CharMatcherTest {
	@Test
	public void testRetain() {
		String retained = CharMatcher.DIGIT.retainFrom("some text 89983 and 656more");
		System.out.println(retained);
	}

	@Test
	public void testRemove() {
		String retained = CharMatcher.is('a').removeFrom("hello,world,gaga,how old are you?");
		System.out.println(retained);
	}

	@Test
	public void testAssert() {
		assertEquals("89983", CharMatcher.DIGIT.retainFrom("some text 89983 and more"));
		assertEquals("some text  and more", CharMatcher.DIGIT.removeFrom("some text 89983 and more"));
	}
}
