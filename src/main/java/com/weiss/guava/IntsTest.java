package com.weiss.guava;

import org.junit.Test;

import com.google.common.primitives.Ints;

public class IntsTest {
	@Test
	public void testInts() {
		int[] array = { 1, 2, 3, 4, 5 };
		int a = 4;
		boolean contains = Ints.contains(array, a);
		System.out.println("contains: " + contains);
		int indexOf = Ints.indexOf(array, a);
		System.out.println("indexOf: " + indexOf);
		int max = Ints.max(array);
		System.out.println("max: " + max);
		int min = Ints.min(array);
		System.out.println("min: " + min);
		int[] array2 = { 1, 2, 3, 4, 5, 6, 7 };
		int[] concat = Ints.concat(array, array2);
		System.out.println("=====================");
		for (int num : concat)
			System.out.println(num);
	}
}
