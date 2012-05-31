package com.weiss.j2se.net;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String[] args) throws Exception {
		ServerSocket ss=new ServerSocket(5000);
		System.out.println("i am listening~~~~~");
		Socket s=ss.accept();
		new Thread(new ServerIn(s)).start();
		new Thread(new ServerOut(s)).start();
	}
}
