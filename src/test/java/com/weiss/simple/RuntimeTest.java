package com.weiss.simple;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.junit.Test;

public class RuntimeTest {
	@Test
	public void testExe() throws Exception {
		Process process = Runtime.getRuntime().exec("ipconfig");
		System.out.println(process);
		InputStream inputStream = process.getInputStream();
		InputStreamReader isr=new InputStreamReader(inputStream,"gbk");
		BufferedReader br=new BufferedReader(isr);
		String line;
		while((line=br.readLine())!=null){
			System.out.println(line);
		}
		// byte[] bs = new byte[1024];
		// int count;
		// while ((count = inputStream.read(bs)) != -1) {
		// System.out.println(new String(bs,0,count));
		// count=inputStream.read(bs);
		// }
	}
}
