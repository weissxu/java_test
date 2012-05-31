package com.weiss.thread.cinema;

public class Seller {
	private int fiveNo;
	private int tenNo;
	private int twentyNo;
	public synchronized void sell(int money){
		if(money==5){
			fiveNo++;
			System.out.println("收你5元，刚好，没有找零！！");
			System.out.println("当前余额:5元："+this.getFiveNo()+"----10元："+this.getTenNo()+"----20元："+this.getTwentyNo());
			
		}else if(money==10){
			while(fiveNo<1){
				System.out.println("没有零钱，请一旁稍等！！当前线程:"+Thread.currentThread().getName());
				try {
					wait();
					System.out.println("结束等待！！当前线程:"+Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			tenNo++;
			fiveNo--;
			System.out.println("收你10元，找零5元，请收好！！");
			System.out.println("当前余额:5元："+this.getFiveNo()+"----10元："+this.getTenNo()+"----20元："+this.getTwentyNo());
		}else if(money==20){
			while(fiveNo<1||tenNo<1){
				System.out.println("没有零钱，请一旁稍等！！当前线程:"+Thread.currentThread().getName());
				try {
					wait();
					System.out.println("结束等待！！当前线程:"+Thread.currentThread().getName());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			twentyNo++;
			tenNo--;
			fiveNo--;
			System.out.println("收你20元，找零15元，请收好！！");
			System.out.println("当前余额:5元："+this.getFiveNo()+"----10元："+this.getTenNo()+"----20元："+this.getTwentyNo());
		}
		notify();
	}
	public int getFiveNo() {
		return fiveNo;
	}
	public void setFiveNo(int fiveNo) {
		this.fiveNo = fiveNo;
	}
	public int getTenNo() {
		return tenNo;
	}
	public void setTenNo(int tenNo) {
		this.tenNo = tenNo;
	}
	public int getTwentyNo() {
		return twentyNo;
	}
	public void setTwentyNo(int twentyNo) {
		this.twentyNo = twentyNo;
	}
}
