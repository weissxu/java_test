package com.weiss.action;

import java.util.ArrayList;
import java.util.List;

public class ActionInterceptor {
	List<Interceptor> interceptors=new ArrayList<Interceptor>();
	Action a=new Action();
	int index=-1;
	public ActionInterceptor(){
		this.interceptors.add(new Interceptor1());
		this.interceptors.add(new Interceptor2());
		System.out.println(this.interceptors.size());
	}
	public void invoke(){
		index++;
		if(index>=interceptors.size()){
			a.execute();
		}else{
			interceptors.get(index).intercept(this);
			
		}
		/*while(index<interceptors.size()){
			interceptors.get(index).intercept(this);
		}
		a.execute();*/
	}
	public static void main(String[] args){
		new ActionInterceptor().invoke();
	}
}
