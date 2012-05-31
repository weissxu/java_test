package com.weiss.server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
	public static void main(String[] args) {
		new MiniServer(8888).execute();
	}
}

class MiniServer {
	private final int port;

	public MiniServer(int port) {
		super();
		this.port = port;
	}

	public void execute() {
		ServerSocket ss =null;
		try {
			ss = new ServerSocket(port);
			System.out.println("listening on " + ss);
			while (true) {
				System.out.println("accepting~~~~~");
				final Socket s = ss.accept();
				System.out.println("connect to" + s);
				new Thread(
						new Runnable() {
							
							
							public void run() {
								new Server().service(s);
							}
						}
						
				).start();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(ss!=null)
				try {
					ss.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	class Server{
		private Server(){}
		
		public void service(Socket s){
			System.out.println(Thread.currentThread()+":Server.service("+s+") begin!!");
			try {
				DataOutputStream out=new DataOutputStream(s.getOutputStream());
				out.writeBytes("HTTP/1.0 200 OK\r\n");
				out.writeBytes("Content-type: text/html\r\n");
				out.writeBytes("\r\n");
				out.writeBytes("<h1>CountDown start!!</h1>");
				/*for(int i=10;i>=0;i--){
					System.out.println(Thread.currentThread()+": countDown i="+i);
					out.writeBytes("<h1>"+i+"</h1>");
					out.flush();
					Thread.sleep(1000);
					
				}*/
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				try {
					s.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			System.out.println(Thread.currentThread()+":Server.service("+s+") end!!");
		}
	}
	
}














