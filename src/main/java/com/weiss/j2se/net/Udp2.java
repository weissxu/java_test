package com.weiss.j2se.net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Udp2 {
	public static void main(String[] args) throws Exception {
		DatagramSocket socket=new DatagramSocket(5000);
		
		byte[] b=new byte[1024];
		DatagramPacket p=new DatagramPacket(b, 1000);
		//p=new DatagramPacket(b, offset, length, address, port)
		socket.receive(p);
		System.out.println(new String(b,0,b.length));
		String str="welcome!!";
		b=str.getBytes();
		DatagramPacket p1=new DatagramPacket(b, b.length, p.getAddress(),p.getPort());
		socket.send(p1);
	}
}
