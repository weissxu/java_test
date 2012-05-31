package com.weiss.thread.activeObject;

public class FutureResult implements Result {
	private Result rr;
	private boolean ready = false;

	public synchronized void setResult(Result result) {
		if (!ready) {
			this.rr = result;
			this.ready = true;
			notifyAll();
		}
	}

	
	public synchronized String getResultValue() {
		while(!ready){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return rr.getResultValue();
	}

}
