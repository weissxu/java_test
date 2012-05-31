package com.weiss.thread;

public class WaterSup implements Runnable{

	
	public void run() {
		while(true){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(" i am water supplier, i am suppling water!!");
		}
	}

}
