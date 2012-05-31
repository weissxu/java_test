package com.weiss.j2se.collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		/*Map<String,String> map=new HashMap<String,String>();
		map.put("a", "zhangsan");
		map.put("b", "lisi");
		map.put("c", "wangwu");
		map.put("d", "zhangsan");
		Set<String> keys=map.keySet();
		System.out.println(keys.getClass());	
		Collection<String> values=map.values();
		System.out.println(values.getClass());*/
		
		
		Map<String,Integer> map=new HashMap<String,Integer>();
		for(int i=0;i<args.length;i++){
			String temp=args[i];
			if(map.keySet().contains(temp)){
				int j=map.get(temp);
				map.put(temp, j+1);
			}else{
				map.put(temp,1);
			}
		}
		for(Entry<String,Integer> entry:map.entrySet()){
			System.out.println(entry);
		}
		System.out.println(map);
	}
}
