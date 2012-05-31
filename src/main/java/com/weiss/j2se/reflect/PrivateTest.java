package com.weiss.j2se.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.weiss.model.Person;

public class PrivateTest {
	public static void main(String[] args) throws Exception {
		Person p=new Person();
		Class<?> clazz=p.getClass();
		Field field=clazz.getDeclaredField("age");
		Method method=clazz.getDeclaredMethod("sayHi", new Class<?>[]{});
		System.out.println(field);
		System.out.println(method);
		System.out.println("===========");
		System.out.println(field.getName());
		System.out.println("===========");
		method.setAccessible(true);
		method.invoke(p, new Object[]{});
		
	}
}
