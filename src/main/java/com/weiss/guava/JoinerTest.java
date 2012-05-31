package com.weiss.guava;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Ints;

public class JoinerTest {
	private static final ImmutableList<String> immutableList = ImmutableList.of("01", "02", "03", "04", "05");

	@Test
	public void testJoin1() {
		String numbersAsString = Joiner.on("\",\"").join(immutableList);
		System.out.println(numbersAsString);
	}

	@Test
	public void testJoin() {
		int[] numbers = { 1, 2, 3, 4, 5 };
		String numbersAsString = Joiner.on("\n").join(Ints.asList(numbers));
		System.out.println(numbersAsString);
	}
}
