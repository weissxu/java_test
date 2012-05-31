package com.weiss.thread;

public class GatePass {
	public static void main(String[] args) {
		Gate gate=new Gate();
		new UserThread(gate, "alice", "alabama").start();
		new UserThread(gate, "blice", "blabama").start();
		new UserThread(gate, "clice", "clabama").start();
	}
}


class Gate{
	private int count=0;
	private String name;
	private String addr;
	public void pass(String name,String addr){
		this.count++;
		this.name=name;
		this.addr=addr;
		System.out.println("a user is passing,his name is " + name+"!the count is "+count);
		check();
	}
	private void check(){
		if(this.name.charAt(0) != this.addr.charAt(0)){
			System.out.println("there is something wrong,be careful!!" + toString());
		}
	}
	
	public String toString(){
		return "count:" + count +" name:"  + name +"addr:"+addr;
		
	}
}

class UserThread extends Thread{
	private final Gate gate;
	private final String name;
	private final String addr;
	public UserThread(Gate gate,String name,String addr){
		this.gate=gate;
		this.name=name;
		this.addr=addr;
	}
	
	public void run() {
		System.out.println(name + "has started!");
		while(true){
			try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			gate.pass(name, addr);
		}
	}
	
	
	
	
}