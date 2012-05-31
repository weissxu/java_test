package com.weiss.guava;

import java.util.HashSet;
import java.util.Map;
import java.util.SortedMap;

import org.junit.Test;

import com.google.common.collect.MapDifference;
import com.google.common.collect.MapDifference.ValueDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;

public class CollectionTest {

	@Test
	public void testMap() {
		SortedMap<String, String> mapA = Maps.newTreeMap();
		mapA.put("hello", "world");
		mapA.put("hi", "world1");
		mapA.put("welcome", "world");
		SortedMap<String, String> mapB = Maps.newTreeMap();
		mapB.put("hello", "world");
		mapB.put("hello", "world2");
		mapB.put("good", "world1");
		mapB.put("well", "world");
		MapDifference<String, String> differenceMap = Maps.difference(mapA, mapB);

		System.out.println("differenceMap.areEqual(): " + differenceMap.areEqual());
		Map<String, ValueDifference<String>> entriesDiffering = differenceMap.entriesDiffering();
		System.out.println("entriesDiffering" + entriesDiffering);
		Map<String, String> entriesOnlyOnLeft = differenceMap.entriesOnlyOnLeft();
		System.out.println("entriesOnlyOnLeft" + entriesOnlyOnLeft);
		Map<String, String> entriesOnlyOnRight = differenceMap.entriesOnlyOnRight();
		System.out.println("entriesOnlyOnRight" + entriesOnlyOnRight);
		Map<String, String> entriesInCommon = differenceMap.entriesInCommon();
		System.out.println("entriesInCommon" + entriesInCommon);
	}

	@Test
	public void testSetView() {
		HashSet<Integer> setA = Sets.newHashSet(1, 2, 3, 4, 5);
		HashSet<Integer> setB = Sets.newHashSet(4, 5, 6, 7, 8);

		SetView<Integer> union = Sets.union(setA, setB);
		System.out.println("union:");
		for (Integer integer : union)
			System.out.println(integer);

		SetView<Integer> difference = Sets.difference(setA, setB);
		System.out.println("difference:");
		for (Integer integer : difference)
			System.out.println(integer);

		SetView<Integer> intersection = Sets.intersection(setA, setB);
		System.out.println("intersection:");
		for (Integer integer : intersection)
			System.out.println(integer);
	}
}
