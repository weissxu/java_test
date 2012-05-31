package com.weiss.singletonAndmutiton;

public class Client {
	/*static KeyGenerator keygen=null;
	public static void main(String[] args) {
		keygen=KeyGenerator.getInstance();
		for(int i=0;i<10;i++){
			System.out.println( i+"(one): "+keygen.getKeyNext("one"));
			System.out.println( i+"(two): "+keygen.getKeyNext("two"));
		}
	}*/
	
	public static void main(String[] args) {
		Singleton singleton=Singleton.getInstance();
		System.out.println(singleton.getM());
		System.out.println(singleton.getN());
	}

}
