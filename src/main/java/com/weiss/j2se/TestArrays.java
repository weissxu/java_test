package com.weiss.j2se;

import java.util.Arrays;

public class TestArrays {

	public static void main(String[] args) {
		int[] a={1,4,3,5,6,2,9,8};
		Arrays.sort(a);
		System.out.println("�����"+Arrays.toString(a));
		System.out.println("����3����λ�ã�"+Arrays.binarySearch(a, 3));
	}

}
