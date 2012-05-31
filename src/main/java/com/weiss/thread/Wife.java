package com.weiss.thread;

public class Wife implements Runnable{
	private MoneyContainer mc;

	public Wife(MoneyContainer mc) {
		super();
		this.mc = mc;
	}
	public void sub(int money){
		
		this.mc.sub(money);
		System.out.println("取钱了"+money+",当前余额是："+mc.getMoney());
	}
	
	public void run() {
		for(int i=0;i<10;i++){
			this.sub(200);
		}
		
	}
}
