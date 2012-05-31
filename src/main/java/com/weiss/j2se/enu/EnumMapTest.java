package com.weiss.j2se.enu;

import java.util.EnumMap;
import java.util.Map.Entry;
import java.util.Set;

public class EnumMapTest {
	public static void main(String[] args) {
		EnumMap<Direct, String> map=new EnumMap<Direct, String>(Direct.class);
		map.put(Direct.EAST, "向东");
		map.put(Direct.WEST, "向西");
		map.put(Direct.SOUTH, "向南");
		map.put(Direct.NORTH, "向北");
		Set<Entry<Direct, String>> set=map.entrySet();
		for(Entry<Direct, String> entry:set){
			System.out.println(entry);
		}
	}
}


enum Direct{
	NORTH,SOUTH,WEST,EAST;
}
