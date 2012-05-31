package com.weiss.guava;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.Stopwatch;

public class StopWatchTest {
	@Test
	public void testWatch() throws InterruptedException {
		Stopwatch stopwatch = new Stopwatch();
		stopwatch.start();
		Thread.sleep(2000);
		stopwatch.stop();
		System.out.println("consume time: " + stopwatch.elapsedTime(TimeUnit.SECONDS));
	}
}
