package com.weiss.ord;

public class Person implements Comparable<Person>,Cloneable {
	private String name;
	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	
	public String toString() {
		return "name:" + this.name + "  age:" + this.age;
	}

	
	public int compareTo(Person p) {
		if (this.age > p.age) {
			return 1;
		} else if (this.age < p.age) {
			return -1;
		}
		return 0;
	}

}
