package com.weiss.j2se.loader;

public class Client {
	public static void main(String[] args) throws Exception {
		MyClassLoader loader1=new MyClassLoader("loader1");
		loader1.setPath("e:/testLoader/loader1/");
		MyClassLoader loader2=new MyClassLoader(null,"loader2");
 		loader2.setPath("e:/testLoader/loader2/");
		MyClassLoader loader3=new MyClassLoader(null,"loader3");
		loader3.setPath("e:/testLoader/loader3/");
		
		
		
		
// 		/*MyClassLoader.testLoad(loader1);
 		/*loader2.loadClass("Dog").newInstance();
 		System.out.println("================");*/
		Class<?> clazz2=testLoad(loader2);
		Class<?> clazz3=testLoad(loader3);
		System.out.println(clazz2);
		System.out.println(clazz3);
		System.out.println(clazz2==clazz3);
	}
	
	
	public static Class<?>   testLoad(ClassLoader loader) throws Exception{
		Class<?> clazz1=loader.loadClass("Dog");
		return clazz1;
//		clazz1.newInstance()
//		System.out.println("clazz1.newInstance(): "+clazz1.newInstance());
	}
	
}
