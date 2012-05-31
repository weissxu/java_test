package com.weiss.thread;

import com.weiss.thread.activeObject.ActiveObject;

public class DisplayClientThread extends Thread {
	private final ActiveObject activeObject;
	
	
	public DisplayClientThread(String name,ActiveObject activeObject) {
		super(name);
		this.activeObject = activeObject;
	}


	
	public void run() {
		try {
			for(int i=0;true;i++){
				String str=Thread.currentThread().getName()+i;
				activeObject.display(str);
				Thread.sleep(200);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
