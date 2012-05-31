package com.weiss.j2se.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class TestAnnotation {

	public static void main(String[] args) throws Exception {
		/*Class<?> cl = Class.forName("com.weiss.annotation.Model");
		Method method = cl.getMethod("toString");
		Annotation[] anns = method.getDeclaredAnnotations();
		System.out.println(anns.length);
		for (Annotation ann : anns) {
			System.out.println(ann.getClass().getName());
		}
		if(method.isAnnotationPresent(com.weiss.annotation.MyAnnotation.class)){
			MyAnnotation ma=method.getAnnotation(MyAnnotation.class);
			System.out.println(ma.key());
			System.out.println(ma.value());
			System.out.println(ma.name());
		}*/
		Class<?> cl=Class.forName("com.weiss.annotation.Real");
		Annotation[] anns = cl.getAnnotations();
		for (Annotation ann : anns) {
			System.out.println(ann);
		}
	}

	
}
