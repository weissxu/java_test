package com.weiss.j2se;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class CalendarTest {
	@Test
	public void test1() {
		int yyyy, mm, dd, hh;
		Calendar c = Calendar.getInstance();
		yyyy = c.get(Calendar.YEAR);
		mm = c.get(Calendar.MONTH) + 1;
		dd = c.get(Calendar.DAY_OF_MONTH);
		hh = c.get(Calendar.HOUR_OF_DAY);
		System.out.println("year = " + yyyy);
		System.out.println("month = " + mm);
		System.out.println("day = " + dd);
		System.out.println("hour = " + hh);

//		 1. System.currentTimeMillis() 获取系统时间的代码
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String dateStr1 = ts.toString();
		System.out.println("System.currentTimeMillis() = " + dateStr1);

		// 2. date 为获得系统时间的另外一种方法
		Date date = new Date();
		String dateStr2 = new Timestamp(date.getTime()).toString();
		System.out.println(dateStr2);

		// 得到JAVA运行环境的一些基本属性
		// System.getProperties().list(System.out);
	}
}
