package com.weiss.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import org.apache.commons.io.IOUtils;

public class FastJsonTest {
	public static int aCount=100;
	public static int bCount=5;
	
	public static void calc(int value) {
		System.out.println("value: "+value+"-->" +(value/bCount+1)*aCount);
	}
	
	
	public static void main(String[] args) throws IOException {
//		
		calc(0);
		calc(4);
		calc(5);
		calc(6);
		calc(9);
		calc(10);
		calc(11);
	}
}
