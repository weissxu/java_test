package com.weiss.j2se.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestSysIn {
	public static void main(String[] args) {
		/*Scanner s=new Scanner(System.in);
		String temp=s.next();
		while(!("bye".equals(temp))){
			System.out.println(temp);
			temp=s.next();
		}
		System.out.println("系统退出！！");*/
		BufferedReader in=null;
		String temp="";
		try {
			while(true){
				in=new BufferedReader(new InputStreamReader(System.in));
				System.out.println("请输入第一个数字");
				temp=in.readLine();
				int num1=Integer.parseInt(temp);
				System.out.println("请输入第二个数字");
				temp=in.readLine();
				int num2=Integer.parseInt(temp);
				System.out.println("和："+(num1+num2));
				System.out.println("请问是否继续计算：yes or no？");
				temp=in.readLine();
				if("no".equals(temp)) break;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) { 
				// TODO Auto-generated catch block
//				e.printStackTrace();
			}
		}
	}
}
