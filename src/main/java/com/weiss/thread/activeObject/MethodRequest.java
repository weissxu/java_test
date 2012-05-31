package com.weiss.thread.activeObject;

public abstract class MethodRequest {
	protected final Servant servant;
	protected final FutureResult future;
	protected MethodRequest(Servant servant, FutureResult future) {
		super();
		this.servant = servant;
		this.future = future;
	}
	public abstract void execute();
}
