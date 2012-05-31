package com.weiss.ord;

import java.util.Arrays;

public class TestCompare {
	public static void main(String[] args) {
		Person[] persons={new Person("zhangsan",20),new Person("lisi",32),new Person("zhangsan",20),new Person("lisi",32)};
		for(Person p:persons){
			System.out.println(p.hashCode());
		}
		/*Arrays.sort(persons);
		for(Person p:persons){
			System.out.println(p);
		}*/
		
	}
}
