package com.weiss.j2se.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileRead {
	public static void main(String[] args) {
		BufferedReader br=null;
		try {
			br=new BufferedReader(new FileReader(new File("D:/test.txt")));
//			char[] cbuf=new char[2000];
			//一次读一行
			String str=br.readLine();
			//一次性全部读入
			/*int len=br.read(cbuf);
			System.out.println(new String(cbuf,0,len));*/
			while(str!=null){
				System.out.println(str);
				str=br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*InputStream is=null;
		try {
			is=new FileInputStream("D:/workspace/j2se/src/com/weiss/io/FileRead.java");
			byte[] b=new byte[2024];
			int len=is.read(b);
			System.out.println(new String(b,0,len));
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(is!=null){
					is.close();
					is=null;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/
	}

}
