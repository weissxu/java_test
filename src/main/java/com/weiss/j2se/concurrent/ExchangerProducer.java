package com.weiss.j2se.concurrent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Exchanger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ExchangerProducer implements Runnable {
	private static final Log logger = LogFactory.getLog(ExchangerProducer.class);
	private List<Object> list;
	private Exchanger<Object> exchanger;

	public ExchangerProducer(Exchanger<Object> exchanger) {
		list = new ArrayList<Object>();
		this.exchanger = exchanger;
	}

	public void run() {
		for (int i = 0; i < 5; i++) {
			logger.info("produce a data ,use 1s!!");
			list.add(new Date());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Object exchanged = exchanger.exchange(list);
			logger.info("after exchange:" + exchanged);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
