package com.weiss.j2se.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TestObjectIO {
	public static void main(String[] args) {
		ObjectInputStream in=null;
		ObjectOutputStream out=null;
		Person p=new Person("weiss",23);
		try {
			/*out=new ObjectOutputStream(new FileOutputStream("d:/person.java"));
			out.writeObject(p);*/
			in=new ObjectInputStream(new FileInputStream("d:/person.java"));
			Object obj=in.readObject();
			System.out.println(obj);
			System.out.println("success!!");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
//				out.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
