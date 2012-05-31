package com.weiss.j2se.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class BufferedIO {

	public static void main(String[] args) {
		BufferedReader br=null;
		BufferedWriter bw=null;
		String str=null;
		StringBuffer sb=null;
		try {
			br=new BufferedReader(new FileReader(new File("d:/test.txt")));
			bw=new BufferedWriter(new FileWriter(new File("d:/test1.txt")));
			sb=new StringBuffer();
			while((str=br.readLine())!=null){
				System.out.println(str);
				sb.append(str).append("\r\n");
			}
			bw.write(sb.toString());
			System.out.println("sucess!!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				br.close();
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
