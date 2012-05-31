package com.weiss.simple;

public class StringTest {
	public static void main(String[] args) {
		String s1=new String("hi");
		String s2="hi";
		
		System.out.println("before s1.intern():"+(s1==s2));
		s1=s1.intern();
		System.out.println("after s1.intern():"+(s1==s2));
	}
}
