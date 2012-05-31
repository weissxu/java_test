package com.weiss.thread.activeObject;

public class ScheduledThread extends Thread {
	private final ActivationQueue queue;
	
	public ScheduledThread(ActivationQueue queue) {
		super();
		this.queue = queue;
	}
	public void invoke(MethodRequest request){
		queue.putTask(request);
	}
	
	public void run() {
		MethodRequest request=queue.getTask();
		request.execute();
	}
}
