package com.weiss.j2se.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class SortTest {
	private static final String idsStr = "2,10,3,10,5,2,9,8,7,8,8";

	@Test
	public void testOrderedCollection() {
		Set<String> itemIdSet = new HashSet<String>();
		List<Integer> itemIdList = new ArrayList<Integer>();
		// for (String line : lines) {
		String[] split = idsStr.split(",");
		for (String itemId : split) {
			itemId = itemId.trim();
			if (itemIdSet.contains(itemId))
				continue;
			itemIdList.add(Integer.parseInt(itemId));
			itemIdSet.add(itemId);
		}
		System.out.println("list: " + itemIdList);
		System.out.println("set: " + itemIdSet);
	}

	@Test
	public void testTreeSet() {
		Set<Integer> intSet = new TreeSet<Integer>();
		intSet.add(2);
		intSet.add(9);
		intSet.add(5);
		intSet.add(2);
		intSet.add(4);
		intSet.add(3);
		for (int num : intSet) {
			System.out.println(num);
		}
	}

	@Test
	public void testOrder() {
		List<Integer> nums = new ArrayList<Integer>();
		nums.add(2);
		nums.add(3);
		nums.add(4);
		nums.add(6);
		nums.add(5);
		for (int num : nums) {
			System.out.println(num);
		}
	}

	public static void main(String[] args) {
		String[] ids = idsStr.split(",");
		List<Integer> idList = new ArrayList<Integer>();
		for (String id : ids) {
			idList.add(Integer.parseInt(id));
		}

		Collections.sort(idList);

		for (int id : idList) {
			System.out.println(id);
		}
	}
}
