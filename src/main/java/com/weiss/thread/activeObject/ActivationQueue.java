package com.weiss.thread.activeObject;


public class ActivationQueue {
	private final int len=5;
	private final MethodRequest[] queue;
	private int head;
	private int tail;
	private int count;
	public ActivationQueue(int num) {
		
		
		queue=new MethodRequest[len];
		head=0;
		tail=0;
		count=0;
		
	}
	
	
	public synchronized void putTask(MethodRequest request){
		while(count>=len){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		queue[tail]=request;
		tail=(tail+1)%len;
		count++;
		System.out.println("任务加入了，加载任务的是线程："+Thread.currentThread());
		notifyAll();
	}
	
	public synchronized MethodRequest getTask(){
		while(count<=0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		MethodRequest request=queue[head];
		head=(head+1)%len;
		count--;
		notifyAll();
		return request;
	}
	
}
