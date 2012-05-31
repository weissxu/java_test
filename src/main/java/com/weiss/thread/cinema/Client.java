package com.weiss.thread.cinema;

public class Client {
	public static void main(String[] args) {
		Seller seller=new Seller();
		seller.setFiveNo(1);seller.setTenNo(0);seller.setTwentyNo(0);
		Buyer b1=new Buyer(seller,20);
		Buyer b2=new Buyer(seller,10);
		Buyer b3=new Buyer(seller,5);
		Thread t1=new Thread(b1,"buyer1");
		Thread t2=new Thread(b2,"buyer2");
		Thread t3=new Thread(b3,"buyer3");
		t1.start();
		t2.start();
		t3.start();
	}
}
