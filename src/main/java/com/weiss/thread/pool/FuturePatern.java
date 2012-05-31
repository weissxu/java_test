package com.weiss.thread.pool;

public class FuturePatern {
	public static void main(String[] args) {
		Host host = new Host();
		FutureData d1 = host.request(10, 'A');
		FutureData d2 = host.request(20, 'B');
		FutureData d3 = host.request(30, 'C');
		try {
			System.out
					.println("-------------doing other thing begin----------");
			Thread.sleep(1000);
			System.out
					.println("-------------doing other thing end!!----------");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(d1.getContent());
		System.out.println(d2.getContent());
		System.out.println(d3.getContent());
	}

}

final class Host {
	public FutureData request(final int num, final char ch) {
		final FutureData future = new FutureData();
		new Thread() {
			
			public void run() {
				RealData real = new RealData(num, ch);
				future.SetContent(real);
			}
		}.start();
		return future;
	}
}

interface Data {
	public String getContent();
}

class FutureData implements Data {
	private RealData data = null;
	private boolean flag = false;

	public synchronized void SetContent(RealData data) {
		if (!flag) {
			this.data = data;
		} else {
			return;
		}
		flag = true;
		notifyAll();
	}

	
	public synchronized String getContent() {
		if (!flag) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return data.getContent();
	}
}

class RealData implements Data {
	private char[] str = null;

	public RealData(int num, char ch) {
		System.out.println(Thread.currentThread() + "开始初始化对象： realData" + num);
		try {
			Thread.sleep(1000);
			str = new char[num];
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			str = ("出现错误：  " + e.getClass().getName()).toCharArray();
		}
		for (int i = 0; i < num; i++) {
			str[i] = ch;
		}
		System.out.println(Thread.currentThread() + "结束初始化对象： realData" + num);
	}

	
	public String getContent() {
		return new String(str);
	}
}
