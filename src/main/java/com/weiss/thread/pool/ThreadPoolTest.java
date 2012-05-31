package com.weiss.thread.pool;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTest {
	public static void main(String[] args) {
		ExecutorService executor=new ThreadPoolExecutor(3,5,2,TimeUnit.SECONDS,
				new ArrayBlockingQueue<Runnable>(3),new ThreadPoolExecutor.CallerRunsPolicy());
		
		try {
			for(int i=0;i<50;i++){
				Thread.sleep(200);
				System.out.println("加入任务：weiss"+i);
				executor.execute(new WorkTask("weiss"+i));
			}
			Thread.sleep(40000);
			System.out.println("线程池准备关闭！！");
			
			executor.shutdown();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class WorkTask implements Runnable {
	private String name;

	public WorkTask(String name) {
		super();
		this.name = name;
	}

	
	public void run() {
		try {
			Thread.sleep(600);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread() + " 正在执行任务，任务名：" + name+",当前时间是："+new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));

	}

}
