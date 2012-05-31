package com.weiss.j2se.proxy;

public class Client {
	public static void main(String[] args) {
		Object proxy=new LogHandler().newProxyObject(new UserDaoImpl());
		System.out.println(proxy.getClass());
		UserDao userDao=(UserDao)proxy ;
		userDao.addUser("weiss");
		userDao.findUser("weiss");
	}
}
