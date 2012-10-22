package com.weiss.msgpack.rpc;

import java.io.Serializable;
import java.util.Date;

import org.msgpack.annotation.Message;

@Message
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Date birthday;
	private int age;

	public User() {
	}

	public User(int id, String name, Date birthday, int age) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
