package com.weiss.data;

import org.junit.Test;

public class DataTest {
	@Test
	public void testShort(){
		//32767~~-32768
		short st1=32765;
		short st2=32765;
		short st=(short) (st1+st2);
		int stInt=st1+st2;
		System.out.println("st1: "+st1);
		System.out.println("st: "+st);
		System.out.println("stInt: "+stInt);
		System.out.println("st1+st2: "+(st1+st2));
	}
	
	@Test
	public void byteTest(){
		System.out.println('\u0041');
		System.out.println('\u0051');
		System.out.println('\u0052');
		System.out.println("--------------------------------");
		System.out.println('\u007d');
		System.out.println('\u007e');
		System.out.println('\u007f');
	}
}
