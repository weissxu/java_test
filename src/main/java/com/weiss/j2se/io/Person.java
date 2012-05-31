package com.weiss.j2se.io;

import java.io.Serializable;

public class Person implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private int age;
	public Person(String name,int age){
		this.name=name;
		this.age=age;
	}
	
	public String toString() {
		return "name:"+name+"  age:"+age;
	}
	
}
