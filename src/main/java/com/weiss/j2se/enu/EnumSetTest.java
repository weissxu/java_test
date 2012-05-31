package com.weiss.j2se.enu;

import java.util.EnumSet;

public class EnumSetTest {
	public static void main(String[] args) {
		EnumSet<Font> e=EnumSet.of(Font.BLACK, Font.values());
		print(e);
	}
	
	public static void print(EnumSet<Font> enumSet){
		for(Font f:enumSet){
			System.out.println(f);
		}
	}
}
