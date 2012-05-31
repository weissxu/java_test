package com.weiss.j2se.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolRegectTest {
	private static final int MAX_RUNNABLES = 1;

	private final List<Runnable> runnableBuffer = new ArrayList<Runnable>(MAX_RUNNABLES);

	private final ThreadPoolExecutor myThreadPool = new ThreadPoolExecutor(MAX_RUNNABLES, MAX_RUNNABLES, 0L,
			TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

	public PoolRegectTest() {
		myThreadPool.prestartAllCoreThreads();

		for (int i = 0; i < MAX_RUNNABLES * 5; i++) {
			runnableBuffer.add(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	public void start() {

		for (Runnable r : runnableBuffer) {
			System.out.println("corePoolSize: " + myThreadPool.getCorePoolSize());
			System.out.println("maxPoolSize: " + myThreadPool.getMaximumPoolSize());
			System.out.println("poolSize: " + myThreadPool.getPoolSize());
			System.out.println("taskCount: " + myThreadPool.getTaskCount());

			System.out.println("Starting runnable ");
			System.out.println("");
			myThreadPool.execute(r);
		}
		System.out.println("All runnables have started");
	}

	public void stop() throws InterruptedException {
		myThreadPool.shutdown();
		myThreadPool.awaitTermination(10, TimeUnit.SECONDS);
	}

	public static void main(String[] args) throws InterruptedException {
		PoolRegectTest t = new PoolRegectTest();
		t.start();
		t.stop();
	}
}