package com.weiss.j2se.concurrent;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BarrierMain {
	private static final Log logger = LogFactory.getLog(BarrierMain.class);
	private static int[] walkerStep = { 3, 8, 10 };
	private static int[] busStep = { 2, 5, 7 };
	private static int[] selfStep = { 1, 3, 5 };

	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
			public void run() {
				logger.info("we are all here!!");
			}
		});

		ExecutorService exe = Executors.newFixedThreadPool(3);
		exe.submit(new Tour(barrier, walkerStep));
		exe.submit(new Tour(barrier, busStep));
		exe.submit(new Tour(barrier, selfStep));
		exe.shutdown();
	}
}
