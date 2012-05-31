package com.weiss.j2se.concurrent;

import java.util.concurrent.Exchanger;

public class ExchangerMain {
	public static void main(String[] args) {
		Exchanger<Object> exchanger=new Exchanger<Object>();
		
		ExchangerProducer producer=new ExchangerProducer(exchanger);
		ExchangerConsumer consumer=new ExchangerConsumer(exchanger);
		new Thread(producer).start();
		new Thread(consumer).start();
	}
}
