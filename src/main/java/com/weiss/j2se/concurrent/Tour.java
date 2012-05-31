package com.weiss.j2se.concurrent;

import java.util.concurrent.CyclicBarrier;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Tour implements Runnable {
	private static final Log logger = LogFactory.getLog(Tour.class);
	private CyclicBarrier barrier;
	private int[] timeStep;

	public Tour(CyclicBarrier barrier, int[] timeStep) {
		super();
		this.barrier = barrier;
		this.timeStep = timeStep;
	}

	public void run() {
		for (int i = 0; i < timeStep.length; i++) {
			try {
				Thread.sleep(timeStep[i] * 1000);
				logger.info(Thread.currentThread() + "reached!!");
				barrier.await();

			} catch (Exception e) {
				logger.error(e);
			}
		}

	}

}
