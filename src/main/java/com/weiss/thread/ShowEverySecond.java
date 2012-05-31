package com.weiss.thread;

public class ShowEverySecond {
	public static void main(String[] args){
		Dog dog=new Dog();
		Thread t=new Thread(dog);
		t.start();
	}
}
class Dog implements Runnable{
	int num=0;
	
	public void run() {
		
		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("hello,world"+num++);
			if(num>10) System.exit(0);
		}
	}
	
}
