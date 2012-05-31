package com.weiss.thread.pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest1 {
	public static void main(String[] args) {
		/*ExecutorService executor=new ThreadPoolExecutor(3,5,2,TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3),new ThreadPoolExecutor.CallerRunsPolicy());
		*/
		ScheduledExecutorService executor=Executors.newSingleThreadScheduledExecutor();
		executor.scheduleWithFixedDelay(new WorkTask("weiss"), 3, 3, TimeUnit.SECONDS);
		try {
			
			Thread.sleep(40000);
			System.out.println("线程池准备关闭！！");
			
			executor.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

