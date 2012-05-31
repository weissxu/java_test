package com.weiss.thread;

public class MoneyContainer {
	private int money;
	
	public MoneyContainer(int money) {
		super();
		this.money = money;
	}
	public int getMoney(){
		return this.money;
	}

	public  void add(int add){
		synchronized(this){
			this.money += add;
			System.out.println("####存钱了，提醒一下！");
			notifyAll();
		}
		
	}
	public void sub(int sub){
		
		synchronized(this){
			while(this.money<sub){
				System.out.println("####余额不足等待中！！");
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.money -=sub;
		}
	}
}
