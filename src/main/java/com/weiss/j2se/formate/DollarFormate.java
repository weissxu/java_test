package com.weiss.j2se.formate;

import java.text.NumberFormat;
import java.util.Locale;

public class DollarFormate {
	public void display(int amount,Locale locale){
		NumberFormat formate=NumberFormat.getCurrencyInstance(locale);
		System.out.println(formate.format(amount)+locale.getDisplayName());
	}
	public void finalize(){
		System.out.println("---------end----");
	}

	public static void main(String[] args) {
		DollarFormate df=new DollarFormate();
		df.display(12345,new Locale("en","US"));
		df.display(12345,new Locale("zh","CN"));
		df.display(12345,new Locale("de","DE"));
	}

}
