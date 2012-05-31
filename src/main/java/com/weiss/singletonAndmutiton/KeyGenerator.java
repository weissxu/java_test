package com.weiss.singletonAndmutiton;

import java.util.HashMap;
import java.util.Map;

public class KeyGenerator {
	private KeyInfo keyInfo;
	private Map<String,KeyInfo> list=new HashMap<String,KeyInfo>(10);
	
	private static  final KeyGenerator keygen=new KeyGenerator();
	private KeyGenerator(){}
	public static  KeyGenerator getInstance(){
		return keygen;
	}
	
	public int getKeyNext(String name){
		if(list.containsKey(name)){
			keyInfo=(KeyInfo) list.get(name);
		}else{
			keyInfo=new KeyInfo(name);
			list.put(name, keyInfo);
		}
		return keyInfo.getKeyNext();
	}
}
