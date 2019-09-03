package com.weiss.jdk.jdk8;

/**
 * description
 *
 * @Author: siwei
 * @Date: 2018/7/16
 */
public class LamdaDemo1 {

    public static void main(String args[]) {

        final int num = 1;

        Converter<Integer, String> s = (param) -> {
            return String.valueOf(param + num);
        };

        System.out.println(s.convert(10));


        System.out.println("----------------------");
        MathOperation substration = (a, b) -> {
            return a - b;
        };

        System.out.println(substration.operation(10, 3));
    }

    public interface Converter<T1, T2> {
        T2 convert(T1 i);
    }

    interface MathOperation {
        int operation(int a, int b);

        default int addition(int a, int b) {
            return a + b;
        }
    }
}
