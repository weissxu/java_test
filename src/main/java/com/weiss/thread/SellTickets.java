package com.weiss.thread;

public class SellTickets {

	public static void main(String[] args) throws InterruptedException {
		TWindow tw=new TWindow();
		new Thread(tw).start();
		new Thread(tw).start();
		new Thread(tw).start();
		System.out.println(tw.num);
		Thread.sleep(2000);
		System.out.println(tw.num);
	}

}
class TWindow1 extends TWindow{
	
}