package com.weiss.j2se.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Udp1 {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket=new DatagramSocket();
		String str="hello,world!";
		byte[] b=new byte[1024];
		b=str.getBytes();
		DatagramPacket p=new DatagramPacket(b, b.length, InetAddress.getByName("localhost"), 5000);
		socket.send(p);
		
		b=new byte[1024];
		p=new DatagramPacket(b, 1000);
		socket.receive(p);
		System.out.println(new String(b));
	}
}
