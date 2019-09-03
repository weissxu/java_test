package com.weiss.jdk.jdk8;

import java.util.function.BiPredicate;

/**
 * description
 *
 * @Author: siwei
 * @Date: 2018/7/16
 */
public class PredicateDemo {
    public static void main(String[] args) {
        //x是否大于y的lambda表达式
        BiPredicate<Integer, Integer> bi = (x, y) -> x > y;
        //x减去2之后是否还大于y
        BiPredicate<Integer, Integer> an = (x, y) -> x - 2 > y;
        System.out.println(bi.test(2, 3));
        //是否同时满足
        System.out.println(bi.and(an).test(4, 3));
        System.out.println(bi.and(an).test(8, 3));

        BiPredicate<String, String> biPre = String::equals;               //[使用方法引用]
        System.out.println(biPre.test("aaa", "aaa"));
    }

}
