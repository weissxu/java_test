package com.weiss.thread.activeObject;

public class Servant implements ActiveObject {

	
	public void display(String str) {
		System.out.println("displayString: "+str);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public Result makeString(int count, char ch) {
		char[] chars=new char[count];
		for(int i=0;i<count;i++){
			chars[i]=ch;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return new RealResult(new String(chars));
	}

}
