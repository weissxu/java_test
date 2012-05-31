package com.weiss.thread.activeObject;

public class DisplayStringReq extends MethodRequest {
	private final String str;
	
	public DisplayStringReq(Servant servant, String str) {
		super(servant, null);
		this.str = str;
	}

	
	public void execute() {
		servant.display(str);

	}

}
