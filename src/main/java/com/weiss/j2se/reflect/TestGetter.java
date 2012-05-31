package com.weiss.j2se.reflect;

import java.lang.reflect.Method;


public class TestGetter {

	public static void main(String[] args) {
//		try {
//			Person per=(Person)Class.forName("temp.Person").newInstance();
//			Class<?> cl=per.getClass();
//			System.out.println(cl);
//			System.out.println("============");
//			int[] ints=new int[2];
//			int[] ints1=new int[]{};
//			int[] ints2={1};
//			System.out.println(ints.length);
//			System.out.println(ints1.length);
//			System.out.println(ints2.length);
////			int[] ints2=new int[]();error!
//			
//			/*Method m=cl.getMethod("sayHello",String.class,int.class);
//			m.invoke(per,"weiss",23);*/
//			Method mg=cl.getMethod("get"+initString("name"));
//			Class<?>[] cls={String.class};
//			Method ms=cl.getMethod("set"+initString("name"),cls);
//			ms.invoke(per,"weiss");
//			System.out.println(mg.invoke(per));
//		}  catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	public static String initString(String str){
		String newStr=Character.toUpperCase(str.charAt(0))+str.substring(1);
		return newStr;
	}
}
