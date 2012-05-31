package com.weiss.j2se.net;

import java.net.Socket;

public class Client {
	public static void main(String[] args) throws Exception{
		Socket s=new Socket("127.0.0.1",5000);
		new Thread(new ClientIn(s)).start();
		new Thread(new ClientOut(s)).start();
	}
}
