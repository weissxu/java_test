package com.weiss.j2se.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileIO {

	public static void main(String[] args) {
		FileReader fr=null;
		FileWriter fw=null;
		char[] chars=new char[1024];
		int n=0;
		try {
			fr=new FileReader(new File("d:/temp.txt"));
			fw=new FileWriter(new File("d:/temp00.txt"));
			while((n=fr.read(chars))!=-1){
				fw.write(chars,0,n);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				fr.close();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
