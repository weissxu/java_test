package com.weiss.thread.activeObject;

public class Proxy implements ActiveObject {
	private final ScheduledThread scheduler;
	private final Servant servant;
	
	public Proxy(ScheduledThread scheduler, Servant servant) {
		super();
		this.scheduler = scheduler;
		this.servant = servant;
	}

	
	public void display(String str) {
		scheduler.invoke(new DisplayStringReq(servant,str));

	}

	
	public Result makeString(int count, char ch) {
		FutureResult future=new FutureResult();
		scheduler.invoke(new MakeStringReq(servant, future, count, ch));
		return future;
	}

}
