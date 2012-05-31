package com.weiss.j2se.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.SequenceInputStream;

public class TestSeques {
	public static void main(String[] args) {
		InputStream in1=null;
		InputStream in2=null;
		OutputStream out=null;
		SequenceInputStream in=null;
		try {
			in1=new FileInputStream("D:/test.txt");
			in2=new FileInputStream("D:/test1.txt");
			out=new FileOutputStream("d:/test2.txt");
			in=new SequenceInputStream(in1, in2);
			int temp=in.read();
			while(temp!=-1){
				out.write(temp);
				temp=in.read();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				in1.close();
				in2.close();
				in.close();
				out.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
}
