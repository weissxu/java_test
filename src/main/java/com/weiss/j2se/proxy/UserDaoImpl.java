package com.weiss.j2se.proxy;

public class UserDaoImpl implements UserDao {

	
	public void addUser(String name) {
		System.out.println("addUser:"+name);

	}

	
	public void findUser(String id) {
		System.out.println("findUser:"+id);

	}

}
