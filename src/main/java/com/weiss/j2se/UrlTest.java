package com.weiss.j2se;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class UrlTest {
	public static void main(String[] args) throws Exception {
		URL url=new URL("http://www.sina.com.cn");
		URLConnection conn=url.openConnection();
		InputStream is=conn.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(is,"big5"));
		
		String line;
		while((line=br.readLine())!=null){
			System.out.println(line);
			System.out.println("--------------------------------");
		}
		
//		byte[] buf=new byte[1024];
//		int legth=0;
//		
//		while((legth=is.read(buf))!=-1){
//			System.out.println(new String(buf,0,legth));
//			System.out.println("-----------------------------");
//		}
		
		
	}
}
