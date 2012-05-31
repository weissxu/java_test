package com.weiss.j2se.enu;

public enum Font {
	BLACK, INTALIC,HELLO,WORLD;
	
	public static void main(String[] args) {
		for(Font f:Font.values()){
			System.out.println(f);
		}
		System.out.println("------------");
		Font f=Font.valueOf(args[0]);
		for(Font font:Font.values()){
			System.out.println("与"+font+"比较，结果是："+f.compareTo(font));
		}
	}
}
