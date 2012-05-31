package com.weiss.thread.activeObject;

public class ActiveObjectFactory {
	public static ActiveObject createObject(){
		Servant servant=new Servant();
		ActivationQueue queue=new ActivationQueue(50);
		ScheduledThread scheduler=new ScheduledThread(queue);
		Proxy proxy=new Proxy(scheduler,servant);
		scheduler.start();
		return null;
	}

}
