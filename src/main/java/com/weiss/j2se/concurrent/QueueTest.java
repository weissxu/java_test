package com.weiss.j2se.concurrent;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class QueueTest {
	private static final Log logger = LogFactory.getLog(QueueTest.class);

	public static void main(String[] args) {

		final ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<String>(3);
		Thread producer = new Thread() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						queue.put("hello");
					} catch (InterruptedException e) {
						logger.error(e);
					}
				}
			}
		};
		Thread consumer = new Thread() {
			public void run() {
				for (int i = 0; i < 5; i++) {
					try {
						logger.info(queue.take());
					} catch (InterruptedException e) {
						logger.error(e);
					}
				}
			};
		};

		producer.start();
		consumer.start();
	}
}
