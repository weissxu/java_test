package com.weiss.j2se.io;

import java.io.File;

public class TestFileList {
	public static void main(String[] args) {
		File file=new File("E:\\course\\web");
		list(file,1);
	}
	public static void list(File f,int level){
		StringBuffer sb=new StringBuffer("");
		for(int i=0;i<level;i++){
			sb.append("--");
		}
		System.out.println(sb.toString()+f.getName());
		if(f.isDirectory()){
			File[] files=f.listFiles();
			for(File file:files){
				list(file,level+1);
			}
		}
	}
}
