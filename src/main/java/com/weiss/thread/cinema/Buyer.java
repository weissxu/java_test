package com.weiss.thread.cinema;

public class Buyer implements Runnable{
	private Seller seller;
	private int money;
	public Buyer(Seller seller,int money){
		this.seller=seller;
		this.money=money;
	}
	
	public void run() {
		seller.sell(money);
		
	}
}
