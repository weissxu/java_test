package com.weiss.j2se.collection;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class CollectionTest {
	public static void main(String[] args) {
		/*
		 * Set<String> set=new HashSet<String>(); set.add("a"); set.add("b");
		 * set.add("c"); set.add("d"); set.add("e"); set.add("f");
		 * Iterator<String> itr=set.iterator(); System.out.println(itr);
		 * System.out.println("---------------"); for(;itr.hasNext();){ String
		 * temp=itr.next(); System.out.println(temp); if("a".equals(temp)){
		 * itr.remove(); } } System.out.println(set);
		 */
		// ArrayList<Integer> arr=new ArrayList<Integer>();

		Set<String> set = new HashSet<String>();
		System.out.println(set.iterator());
		LinkedList<Integer> list = new LinkedList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		Comparator comp = Collections.reverseOrder();
		Collections.sort(list);
		System.out.println(list);
		System.out.println("-------------");
		Collections.sort(list, comp);
		System.out.println(list);

		// System.out.println(Collections.min(list));

		/*
		 * HashSet<Student> hs = new HashSet<Student>(); Student st1 = new
		 * Student(1, "zhao"); Student st2 = new Student(2, "qian"); Student st3
		 * = new Student(3, "sun"); hs.add(st1); hs.add(st2); hs.add(st3);
		 * System.out.println(hs); st1.num = 4; // 可以试着注释掉这一行看一看结果
		 * hs.remove(st1); System.out.println(hs);
		 */
	}
}

class Student {
	public Student(int num, String name) {
		this.num = num;
		this.name = name;
	}

	public int hashCode() {
		return new Integer(num).hashCode();
	}

	
	public boolean equals(Object st) {
		Student tempStudent = (Student) st;
		if (num == tempStudent.num)
			return true;
		else
			return false;
	}

	public String toString() {
		return "student " + num + " name:" + name;
	}

	int num;
	String name;
}