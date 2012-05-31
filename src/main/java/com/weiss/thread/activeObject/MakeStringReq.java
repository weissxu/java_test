package com.weiss.thread.activeObject;

public class MakeStringReq extends MethodRequest {
	private final int count;
	private final char ch;
	
	public MakeStringReq(Servant servant, FutureResult future, int count,
			char ch) {
		super(servant, future);
		this.count = count;
		this.ch = ch;
	}

	
	public void execute() {
		Result result=servant.makeString(count, ch);
		future.setResult(result);

	}

}
