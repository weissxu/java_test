package com.weiss.j2se.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientOut implements Runnable {
	private Socket socket;

	public ClientOut(Socket socket) {
		this.socket = socket;
	}

	
	public void run() {
		OutputStream out = null;
		InputStream in = null;
		try {
			/*
			 * BufferedWriter bw=new BufferedWriter(new
			 * OutputStreamWriter(socket.getOutputStream())); BufferedReader
			 * br=new BufferedReader(new InputStreamReader(System.in));
			 * 
			 * String str; while(true){ str=br.readLine(); bw.write(str); }
			 */
			out = socket.getOutputStream();
			in = System.in;

			byte[] b = new byte[1024];
			while (true) {
				in.read(b);
				out.write(b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != in)
					in.close();
				if (null != out)
					out.close();
			} catch (Exception e2) {
			}
		}

	}

}
