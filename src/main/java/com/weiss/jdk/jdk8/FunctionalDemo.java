package com.weiss.jdk.jdk8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * description
 *
 * @Author: siwei
 * @Date: 2018/7/16
 */
public class FunctionalDemo {
//    @FunctionalInterface
//    public interface Supplier<T> {
//        T get();
//    }

    private static class Car {

        private String name;

        public Car() {

        }

        public Car(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        //Supplier是jdk1.8的接口，这里和lamda一起使用了
        public static Car create(final Supplier<Car> supplier) {
            return supplier.get();
        }

        public void collide(final Car car) {
            System.out.println("Collided " + car.toString());
        }

        public void follow(final Car another) {
            System.out.println("Following the " + another.toString());
        }

        public void repair() {
            System.out.println("Repaired " + this.toString());
        }

        @Override
        public String toString() {
            return "Car: " + name;
        }
    }

    public static void main(String[] args) {
        Car car = Car.create(Car::new);
        car.setName("one");
        Car another = Car.create(Car::new);
        another.setName("another");

        car.follow(another);
        car.repair();

        System.out.println("=======================");

        List names = new ArrayList();

        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");

        names.forEach(System.out::println);
    }
}
