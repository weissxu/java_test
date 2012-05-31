package com.weiss.thread;

public class Hasband implements Runnable{
	private MoneyContainer mc;

	public Hasband(MoneyContainer mc) {
		super();
		this.mc = mc;
	}
	
	public void add(int money){
		this.mc.add(money);
		System.out.println("-------存钱了："+money+",当前余额是："+mc.getMoney());
	}

	
	public void run() {
		for(int i=0;i<10;i++){
			try {
				Thread.sleep(5000);
				this.add(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}
