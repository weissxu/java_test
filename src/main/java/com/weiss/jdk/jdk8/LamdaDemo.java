package com.weiss.jdk.jdk8;

/**
 * description
 *
 * @Author: siwei
 * @Date: 2018/7/16
 */
public class LamdaDemo {

    public static void main(String args[]) {
        LamdaDemo tester = new LamdaDemo();

        MathOperation addition = (a, b) -> {
            return a + b;
        };
        MathOperation subtraction = (int a, int b) -> {
            return a - b;
        };

        MathOperation multiplication = (a, b) -> {
            return a * b;
        };

        MathOperation division = (a, b) -> {
            return a / b;
        };

        System.out.println("10 + 5 = " + tester.operate(10, 5, addition));
        System.out.println("10 - 5 = " + tester.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + tester.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + tester.operate(10, 5, division));

        // 不用括号
        GreetingService greetService1 = message -> {
            System.out.println("Hello " + message);
        };

        // 用括号
        GreetingService greetService2 = (message) -> {
            System.out.println("Hello " + message);
        };

        greetService1.sayMessage("baidu");
        greetService2.sayMessage("Google");
    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }
}
