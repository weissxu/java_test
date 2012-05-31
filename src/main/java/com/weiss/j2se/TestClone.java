package com.weiss.j2se;

class Person implements Cloneable {
	public String name;
	public Person(String name){
		this.setName(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	public String toString() {
		return this.getName();
	}
	
	protected Object clone() throws CloneNotSupportedException{
		return super.clone();
	}
	
}
public class TestClone implements Cloneable {
	public static void main(String[] args) throws CloneNotSupportedException{
		Person p1=new Person("����");
		Person p2=(Person)p1.clone();
		p2.setName("����");
		System.out.println(p1);
		System.out.println(p2);
	}
}
