package com.weiss.thread;

import com.weiss.thread.activeObject.ActiveObject;
import com.weiss.thread.activeObject.ActiveObjectFactory;

public class Client {
	public static void main(String[] args) {
		//MoneyContainer客户端
		/*MoneyContainer mc=new MoneyContainer(1000);
		Hasband h=new Hasband(mc);
		Wife w=new Wife(mc);
		Thread t1=new Thread(h);
		Thread t2=new Thread(w);
		t1.start();
		t2.start();
		System.out.println("最后余额是："+mc.getMoney());*/
		
		//Log客户端
		/*System.out.println("系统准备打印！！！");
		for(int i=0;i<10;i++){
			Log.print(Thread.currentThread().toString() + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("系统准备退出！！！");
		Log.print("系统准备退出");
		Log.close();*/
		
		//Log2客户端
		/*Log2 log=new Log2();
		System.out.println("系统准备打印！！！");
		new ClientThread("weiss",log).start();
		new ClientThread("mike",log).start();
		new ClientThread("steven",log).start();*/
		
		//ActiveObject客户端
		ActiveObject activeObject = ActiveObjectFactory.createObject();
		new MakerClientThread("alice", activeObject).start();
		new MakerClientThread("boby", activeObject).start();
		new DisplayClientThread("chris", activeObject).start();
		
		
		
		
		
		
	}
}
