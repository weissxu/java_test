package com.weiss.guava;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.google.common.base.Splitter;

public class SpliterTest {

	@Test
	public void testSpliter2() {
		Splitter niceCommaSplitter = Splitter.on(',').omitEmptyStrings().trimResults();
		Iterable<String> iterable1 = niceCommaSplitter.split("one,, two,  three"); // "one","two","three"
		System.out.println("========one,, two,  three  result:========");
		showItems(iterable1);
		Iterable<String> iterable2 = niceCommaSplitter.split("  four  ,  five  "); // "four","five"
		System.out.println("=======  four  ,  five  =  result:========");
		showItems(iterable2);
	}

	private void showItems(Iterable<String> iterable) {
		Iterator<String> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	@Test
	public void testSpliter1() {
		// Apache StringUtils...
		String[] tokens1 = StringUtils.split("one,two,three", ',');
		System.out.println("----------StringUtils.split  result:---------------");
		for (String token : tokens1) {
			System.out.println(token);
		}

		// Google Guava splitter...
		Iterable<String> tokens2 = Splitter.on(',').split("one,two,three");
		System.out.println("----------Splitter.on  result:---------------");
		showItems(tokens2);
	}
}
