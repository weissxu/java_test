package com.weiss.j2se.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestScanner {
	public static void main(String[] args){
		/*Scanner s=new Scanner(System.in);
		int num1,num2;
		
		String str1,str2;
		System.out.println("请输入第一个数据");
		str1=s.next();
		System.out.println("第一个数据是："+str1);
		num1=Integer.parseInt(str1);
		System.out.println("请输入第二个数据");
		str2=s.next();
		System.out.println("第二个数据是："+str2);
		num2=Integer.parseInt(str2);
		System.out.println(str1+"+"+str2+"="+(num1+num2));
		s.useDelimiter("\n");
		System.out.println("请输入第一个数据");
		String str1=s.next();
		System.out.println("第一个数据是："+str1);*/
		/*Scanner s=null;
		try {
			s = new Scanner(new File("d:/test.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer sb=new StringBuffer();
		while(s.hasNext()){
			sb.append(s.next()).append("\n");
		}
		System.out.println(sb.toString());*/
		File f = new File("D:" + File.separator + "test.txt") ;	// 指定操作文件
		Scanner scan = null ;
		try{
			scan = new Scanner(f) ;	// 从键盘接收数据
		}catch(Exception e){}
		StringBuffer str = new StringBuffer() ;
		while(scan.hasNext()){
			str.append(scan.next()).append('\n')	;	//	取数据
		}
		System.out.println("文件内容为：" + str) ;
	}
	
}
