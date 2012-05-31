package com.weiss.j2se.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class Test {

	public static void main(String[] args) {
		ResourceBundle res=ResourceBundle.getBundle("app", Locale.CHINA);
		System.out.println(res.getString("welcome"));
		ResourceBundle res1=ResourceBundle.getBundle("app", Locale.US);
		System.out.println(res1.getString("welcome"));
	}

}
