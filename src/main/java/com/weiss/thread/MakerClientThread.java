package com.weiss.thread;

import com.weiss.thread.activeObject.ActiveObject;
import com.weiss.thread.activeObject.Result;

public class MakerClientThread extends Thread {
	private final ActiveObject activeObject;
	private final char ch;
	
	public MakerClientThread(String name,ActiveObject activeObject) {
		super(name);
		this.activeObject = activeObject;
		this.ch = name.charAt(0);
	}

	
	public void run() {
		try {
			for(int i=0;true;i++){
				Result result=activeObject.makeString(i, ch);
				Thread.sleep(10);
				String value=result.getResultValue();
				System.out.println(Thread.currentThread().getName()+": value="+value);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
