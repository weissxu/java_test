package com.weiss.thread;

public class DeadLock {
	public static void main(String[] args) {
		Object obj1=new Object();
		Object obj2=new Object();
		Lock l1=new Lock(obj1,obj2);
		Lock l2=new Lock(obj2,obj1);
		new Thread(l1).start();
		new Thread(l2).start();
	}
}
class Lock implements Runnable{
	private Object obj1;
	private Object obj2;
	public Lock(Object obj1,Object obj2){
		this.obj1=obj1;
		this.obj2=obj2;
	}
	public void sayHi(){
		
		synchronized(obj1){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(obj2){
				System.out.println("hello");
			}
		}
		
	}
	
	public void run() {
		sayHi();
	}
}
