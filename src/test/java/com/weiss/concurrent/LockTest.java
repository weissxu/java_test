package com.weiss.concurrent;

import com.weiss.model.Person;

public class LockTest {
	public static void main(String[] args) {
		Person person = new Person(1, "weiss");
		person.setAge(10);

		Runnable runner1 = new Runner(person);
		Runnable runner2 = new Runner(person);
		Runnable runner3 = new Runner(person);
		new Thread(runner1).start();
		new Thread(runner2).start();
		new Thread(runner3).start();
	}
}
