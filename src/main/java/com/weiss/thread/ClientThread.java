package com.weiss.thread;

public class ClientThread extends Thread {
	private Log2 log;

	public ClientThread(String name, Log2 log) {
		super(name);
		this.log = log;
	}

	
	public void run() {
		for (int i = 0; i < 10; i++) {
			log.getLog().print(Thread.currentThread().toString() + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
