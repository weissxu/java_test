package com.weiss.jdk.jdk8;

/**
 * description
 *
 * @Author: siwei
 * @Date: 2018/7/16
 */
public class DefaultMethodDemo {
    public static void main(String args[]) {
        Vehicle vehicle = new Car();
        vehicle.print();
    }

    interface Vehicle {
        default void print() {
            System.out.println("我是一辆车!");
        }

        static void blowHorn() {
            System.out.println("按喇叭!!!");
        }
    }

    interface FourWheeler {
        default void print() {
            System.out.println("我是一辆四轮车!");
        }
    }

    public static class Car implements Vehicle, FourWheeler {
        public void print() {
            Vehicle.super.print();
            FourWheeler.super.print();
            Vehicle.blowHorn();
            System.out.println("我是一辆汽车!");
        }
    }
}
