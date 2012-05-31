package com.weiss.thread;

public class TWindow implements Runnable{
	int num=1000;
	
	public synchronized void run() {
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"正在出售票号："+num--);
		}
	}
	
}
