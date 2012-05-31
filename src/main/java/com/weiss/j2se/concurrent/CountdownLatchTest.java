package com.weiss.j2se.concurrent;

import java.util.concurrent.CountDownLatch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CountdownLatchTest {
	static class Worker implements Runnable {
		CountDownLatch startLatch;
		CountDownLatch stopLatch;

		public Worker(CountDownLatch startLatch, CountDownLatch stopLatch) {
			this.startLatch = startLatch;
			this.stopLatch = stopLatch;
		}

		@Override
		public void run() {
			try {
				startLatch.await();
			} catch (InterruptedException e) {
				logger.error(e);
			}
			logger.info("i am working");
			stopLatch.countDown();
		}

	}

	private static final Log logger = LogFactory.getLog(CountdownLatchTest.class);
	private static final int num = 5;

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch startLatch = new CountDownLatch(1);
		final CountDownLatch stopLatch = new CountDownLatch(num);
		for (int i = 0; i < num; i++) {
			new Thread(new Worker(startLatch, stopLatch)).start();
		}
		logger.info("start...");
		startLatch.countDown();
		stopLatch.await();
		logger.info("finished...");

	}
}
