package com.weiss.j2se.net;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
	public static void main(String[] args) throws Exception {
		ServerSocket ss = new ServerSocket(5000);
		while (true) {

			System.out.println("i am listening~~~~~");
			final Socket s = ss.accept();
			InputStream in = s.getInputStream();
			final DataOutputStream out = new DataOutputStream(s.getOutputStream());

			byte[] b = new byte[1024];
			int len = in.read(b);
			System.out.println(new String(b, 0, len));

			new Thread() {
				@Override
				public void run() {
					try {
						out.writeBytes("HTTP/1.0 200 OK\r\n");
						out.writeBytes("Content-type:text/html\r\n");
						out.writeBytes("\r\n");
						out.writeBytes("<h1>how do you do?</h1>");
						out.flush();
						Thread.sleep(1000);
						s.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				};
			}.start();

		}
	}
}
