package com.weiss.mail;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MailsSenderTest {
	public static void main(String[] args) {
		try {
			System.out.println("稍后更精彩！！");
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ExecutorService executor=new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5),
				new ThreadPoolExecutor.CallerRunsPolicy());
		for(int i=0;i<10;i++){
			MailSender comm=new MailSender(null);
			MailSender comm1=new MailSender("635647295@qq.com");
			MailSender comm2=new MailSender("xusiwei666@gmail.com");
			executor.execute(comm);
			executor.execute(comm1);
			executor.execute(comm2);
		}
		try {
			Thread.sleep(20000);
			System.out.println("准备关闭！！！");
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
