package com.weiss.thread;

/**
 * 线程可以正常启动，但是有一个线程mike始终停不下来～～
 * @author acer
 *
 */
public class WorkerThreadTest {
	public static void main(String[] args) {
		Channel channel=new Channel(3);
		channel.startWork();
		ClientThread2 weiss=new ClientThread2("weiss",channel);
		weiss.start();
		ClientThread2 mike=new ClientThread2("mike",channel);
		mike.start();
//		new ClientThread("alice",channel).start();
		try {
			Thread.sleep(20000);
			weiss.stopThread();
			mike.stopThread();
			channel.stopWork();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}

class ClientThread2 extends Thread {
	private volatile boolean flag=true;
	private Channel channel;
	private int i=0;
	public ClientThread2(String name,Channel channel) {
		super(name);
		this.channel = channel;
	}
	public void stopThread(){
		flag=false;
		interrupt();
	}
	
	public void run() {
		while (flag) {
			Request task = new Request("任务"+Thread.currentThread()+(i++));
			channel.putTask(task);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class WorkerThread extends Thread{
	private Channel channel;
	private volatile boolean flag=true;
	public WorkerThread(String name,Channel channel) {
		super(name);
		this.channel = channel;
	}
	public void setFlag(boolean f){
		this.flag=f;
	}
	
	public void run() {
		while (flag) {
			Request task = channel.getTask();
			task.execute();
		}
	}
}

class Channel {
	private final int len=5;
	private WorkerThread[] workers;
	private  Request[] queue;
	private int head;
	private int tail;
	private int count;
	public Channel(int num) {
		this.workers=new WorkerThread[num];
		for(int i=0;i<num;i++){
			workers[i]=new WorkerThread("worker"+i, this);
		}
		
		
		queue=new Request[len];
		head=0;
		tail=0;
		count=0;
		
	}
	public void startWork(){
		for(int i=0;i<workers.length;i++){
			workers[i].start();
		}
	}
	public void stopWork(){
		for(int i=0;i<workers.length;i++){
			workers[i].setFlag(false);
		}
	}
	
	public synchronized void putTask(Request task){
		while(count>=len){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		queue[tail]=task;
		tail=(tail+1)%len;
		count++;
		System.out.println("任务"+task.getName()+"加入了，加载任务的是线程："+Thread.currentThread());
		notifyAll();
	}
	
	public synchronized Request getTask(){
		while(count<=0){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Request task=queue[head];
		head=(head+1)%len;
		count--;
		notifyAll();
		return task;
	}
	
}

class Request {
	private String name;

	public Request(String name) {
		super();
		this.name = name;
	}
	 public String getName(){
		 return name;
	 }
	public void execute(){
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("任务"+name+"被执行了，执行该任务的是线程："+Thread.currentThread());
	}
}
