package com.weiss.j2se.io;

import java.io.IOException;
import java.io.PipedReader;

public class Receiver implements Runnable {
	/*private PipedInputStream pi;
	public Receiver(){
		pi=new PipedInputStream();
	}
	public PipedInputStream getPi(){
		return this.pi;
	}*/
	private PipedReader pi;
	public Receiver(){
		pi=new PipedReader();
	}
	public PipedReader getPi(){
		return this.pi;
	}
	
	public void run() {
		char[] bs=new char[1024];
		int i=0;
		try {
			i=pi.read(bs);
			System.out.println("receiver message:"+new String(bs,0,i));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
