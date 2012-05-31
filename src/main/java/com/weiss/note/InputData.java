package com.weiss.note;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputData {
	private BufferedReader buf = null ;		// 接收数据
	public InputData(){
		this.buf = new BufferedReader(new InputStreamReader(System.in)) ;
	}
	public String getString(String info){	// 得到字符串
		String temp =  null ;	// 接收输入内容
		System.out.print(info) ;
		try{
			temp = this.buf.readLine() ;	// 接收数据
		}catch(IOException e){
			e.printStackTrace() ;
		}
		return temp ;
	}
	public int getInt(String info,String err){	// 得到整型数据
		int temp = 0 ;
		String str = null ;
		boolean flag = true ;	// 定义一个循环标记
		while(flag){
			str = this.getString(info) ;
			if(str.matches("\\d+")){
				temp = Integer.parseInt(str) ;
				flag = false ;	// 更改标志位，将退出循环
			}else{
				System.out.println(err) ;
			}
		}
		return temp ;
	}
}
