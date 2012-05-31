package com.weiss.j2se.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class InAndOut {
	public static void main(String[] args) {
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		char buf[] = new char[1024];

		try {
			isr = new InputStreamReader(new FileInputStream(new File(
					"D:\\Users\\acer\\Pictures\\1.jpg")));
			osw = new OutputStreamWriter(new FileOutputStream(new File(
					"d:/2.jpg")));
			while (isr.read(buf) != -1) {
				osw.write(buf);
			}
			System.out.println("success!!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				isr.close();
				osw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
