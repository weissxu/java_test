package com.weiss.j2se.concurrent;

import java.util.concurrent.Exchanger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExchangerConsumer implements Runnable {
	private static final Log logger = LogFactory.getLog(ExchangerConsumer.class);
	private Object obj;
	private Exchanger<Object> exchanger;

	public ExchangerConsumer(Exchanger<Object> exchanger) {
		// list = new ArrayList<Object>();
		this.exchanger = exchanger;
	}

	public void run() {
		logger.info("consume 5 ,and return the check!!");
		obj = new String("a check!!");
		try {
			Thread.sleep((long) Math.random() * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		try {
			Object exchanged = exchanger.exchange(obj);
			logger.info("after exchange:" + exchanged);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
