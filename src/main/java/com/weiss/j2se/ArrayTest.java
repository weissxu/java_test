package com.weiss.j2se;

import java.util.Arrays;

import org.junit.Test;

public class ArrayTest {
	@Test
	public void testCopy() {
		// List<Integer> src = Arrays.asList(1,2,3,4,5);
		int[] src = { 1, 2, 3, 4, 5, 6 };
		int[] result = Arrays.copyOfRange(src, 2, 8);
		for (int num : result)
			System.out.println(num);
	}
}
