package com.weiss.j2se.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;

public class TestINet {
	public static void main(String[] args) throws IOException {
		/*URL url=new URL("http://www.baidu.com");
		BufferedReader br=new BufferedReader(new InputStreamReader(url.openStream()));
		String str;
		while(true){
			str=br.readLine();
			System.out.println(str);
			if("".equals(str)||null==str){break;}
		}
		br.close();*/
		
		InetAddress addr=InetAddress.getLocalHost();
		InetAddress addr2=InetAddress.getByName("www.baidu.com");
		System.out.println(addr2);
	}
}
