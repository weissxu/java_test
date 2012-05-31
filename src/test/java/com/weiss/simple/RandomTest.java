package com.weiss.simple;

import java.util.Random;

import org.junit.Test;

public class RandomTest {
	@Test
	public void testRandom() {
		Random random = new Random();
		for (int i = 0; i < 10; i++)
			System.out.println(random.nextInt(5));
	}
}
