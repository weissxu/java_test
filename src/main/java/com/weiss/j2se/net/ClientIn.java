package com.weiss.j2se.net;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientIn implements Runnable {
	private Socket socket;
	public ClientIn(Socket socket){
		this.socket=socket;
	}
	
	public void run() {
		InputStream in=null;
		try {
			/*BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String str;
			while(true){
				str=br.readLine();
				System.out.println(str);
			}*/
			in=socket.getInputStream();
			byte[] b=new byte[1024];
			int len=0;
			while(true){
				len=in.read(b);
				System.out.println("server say:"+new String(b,0,len));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(null!=in)
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}
