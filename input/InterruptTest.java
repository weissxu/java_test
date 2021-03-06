package com.weiss.j2se;

public class InterruptTest extends Thread{  
    static int result=0;  
    public InterruptTest(String name)  
    {  
        super(name);  
    }  
  
    public static void main(String args[])  
    {  
        System.out.println("开始主线程");  
        Thread t1 = new InterruptTest("计算机线程");  
        t1.start();  
        try {  
            long time1 = System.nanoTime();  
            t1.join();  
            long time2 = System.nanoTime();  
            t1.interrupt();//中断线程  
            System.out.println((time2-time1)/1000000+"毫秒后"+result);  
        } catch (InterruptedException e) {  
            e.printStackTrace();  
        }  
  
    }  
  
    public void run()  
    {  
        System.out.println(this.getName()+"开始计算......");  
        try {  
            Thread.sleep(10000);  
        } catch (InterruptedException e) {  
            System.out.println(this.getName()+"线程被中断......");  
            return;  
        }  
        result = (int)(Math.random()*10000);  
        System.out.println("------"+result);  
        System.out.println(this.getName()+"结束计算.....");  
    }  
}  
