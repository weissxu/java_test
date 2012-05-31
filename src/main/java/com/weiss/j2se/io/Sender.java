package com.weiss.j2se.io;

import java.io.IOException;
import java.io.PipedOutputStream;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Sender implements Runnable{
	/*private PipedOutputStream po;
	public Sender(Receiver r){
		try {
			po=new PipedOutputStream(r.getPi());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public PipedOutputStream getPo(){
		return this.po;
	}*/
	private PipedWriter po;
	public Sender(Receiver r){
		try {
			po=new PipedWriter(r.getPi());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public PipedWriter getPo(){
		return this.po;
	}
	
	public void run() {
		String str="hello,world";
		try {
			po.write(str.toCharArray());
			System.out.println("sender message:"+str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
