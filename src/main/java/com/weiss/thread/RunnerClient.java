package com.weiss.thread;

public class RunnerClient {
	public static void main(String[] args) {
		ThreadGroup group=new ThreadGroup("mygroup");
		Thread main=Thread.currentThread();
		System.out.println(main);
		Runner r1=new Runner();
		Runner r2=new Runner();
		Runner r3=new Runner();
		WaterSup w=new WaterSup();
		//		Thread wt=new Thread(w);
		Thread wt=new Thread(group,w);
		wt.setDaemon(true);
		wt.start();
		new Thread(r1).start();
		new Thread(r2).start();
		new Thread(r3).start();
		System.out.println(Thread.activeCount());
	}
}
