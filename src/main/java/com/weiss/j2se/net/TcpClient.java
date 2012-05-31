package com.weiss.j2se.net;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClient {
	public static void main(String[] args) throws Exception{
		Socket s=new Socket("127.0.0.1",5000);
		InputStream in=s.getInputStream();
		OutputStream out=s.getOutputStream();
		out.write("hello,world".getBytes());
		byte[] b=new byte[1024];
		int len=in.read(b);
		System.out.println(new String(b,0,len));
		in.close();
		out.close();
		s.close();
	}
}
