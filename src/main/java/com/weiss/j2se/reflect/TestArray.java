package com.weiss.j2se.reflect;

import java.lang.reflect.Array;

import com.weiss.model.Person;
/*
 * 2011.04.02
 */
public class TestArray {

	public static void main(String[] args) {

		/*int[] temp = { 1, 2, 3, 4 };

		Class<?> cl = temp.getClass().getComponentType();
		System.out.println("cl的类型："+cl);
		System.out.println(Array.getLength(temp));

		Array.set(temp, 0, 10);
		for (int i : temp) {
			System.out.println(i);
		}*/

		
//		 Person[] temp = { new Person("mike", 23), new Person("weiss", 24),
//		 new Person("steve", 23) }; temp = (Person[]) arrayInc(temp, 6);
//		 Array.set(temp, 4, new Person("gaga", 31)); for (Person i : temp) {
//		 System.out.println(i); }
		 
	}

	public static Object arrayInc(Object obj, int len) {
		Class<?> c = obj.getClass();
		System.out.println("-----------打印c：" + c);
		Class<?> arr = c.getComponentType(); // 得到数组的
		System.out.println("-----------------打印arr：" + arr);
		Object newO = Array.newInstance(arr, len); // 开辟新的大小
		int co = Array.getLength(obj);
		System.arraycopy(obj, 0, newO, 0, co); // 拷贝内容
		return newO;
	}
}
