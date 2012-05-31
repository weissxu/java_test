package com.weiss.thread;

public class Runner implements Runnable{

	
	public void run() {
		for(int i=0;i<50;i++){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("i am "+Thread.currentThread().getName() +",i am runing!"+i);
		}
	}
	
}
