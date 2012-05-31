package com.weiss.thread;

public class Log2 {
	private ThreadLocal<TSLog> container=new ThreadLocal<TSLog>();
	
	public TSLog getLog(){
		TSLog log=container.get();
		if(null==log){
			log=new TSLog(Thread.currentThread().getName());
			container.set(log);
		}
		return log;
	}
	
}
