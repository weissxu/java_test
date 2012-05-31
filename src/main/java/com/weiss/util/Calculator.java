package com.weiss.util;

public class Calculator {
    public int add(int i,int j){
        return i+j;
    }
    public int subtract(int i,int j){
        return i-j;
    }
    public int multiply(int i,int j){
        return j*i;
    }
    public int divide(int i,int j){
        if(j==0){
            throw new RuntimeException("除数不能为0");
        }
        return i/j;
    }
}
