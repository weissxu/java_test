package com.weiss.j2se.date;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class TestDate {
	// 11173339 89eaf0865cl
	// 11319136 3450b8a716l
	// 11319136 361c9186bel
	// 11319136 5133e4af83l
	// 11319136 60a41b8b2al
	public static long[] nos = { 1300499511404L, 1300499517551L, 1300499538900L };

	@Test
	public void testLong2Date() {
		Date date = new Date(1337823260816L);
		System.out.println(date);
	}

	@Test
	public void testGetTimestamp() throws ParseException {

		Date now = new Date();
		System.out.println("now:  " + now);
		System.out.println(now.getTime());

		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse("2012-05-24 08:00:17");
		System.out.println("2012-05-24 08:00:17:  " + date);
		System.out.println(date.getTime());

		System.out.println("currentTimeMillis: " + System.currentTimeMillis());
	}

	@Test
	public void testNOs() {
		for (long num : nos) {
			System.out.println(new Date(num));
		}
	}

	@Test
	public void testNO() {
		System.out.println(System.nanoTime());
		System.out.println(System.currentTimeMillis());
	}

	public static void main(String[] args) throws ParseException {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		System.out.println(format.parse("2009-09-09 12:21:12"));
		System.out.println(format.format(date));
		System.out.println(new java.sql.Date(date.getTime()));
		System.out.println(new Timestamp(date.getTime()));
	}
}
