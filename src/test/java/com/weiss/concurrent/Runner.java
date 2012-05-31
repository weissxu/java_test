package com.weiss.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.weiss.model.Person;

public class Runner implements Runnable {
	private Person person;
	private static Lock lock = new ReentrantLock(false);

	public Runner(Person person) {
		super();
		this.person = person;
	}

	public void run() {
		lock.lock();
		try {
			person.setAge(person.getAge() + 1);
			try {
				Thread.sleep((long) (Math.random() * 1000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread() + "the person age is: " + person.getAge());
		} finally {
			lock.unlock();
		}

	}

}
