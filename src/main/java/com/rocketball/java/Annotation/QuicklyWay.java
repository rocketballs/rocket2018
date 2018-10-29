package com.rocketball.java.Annotation;

import java.util.concurrent.atomic.AtomicInteger;

public class QuicklyWay {
    //当只想给value赋值时,可以使用以下快捷方式
    @IntegerVaule(20)
    public int age;
    //当name也需要赋值时必须采用key=value的方式赋值
    @IntegerVaule(value = 10000,name = "MONEY")
    public int money;

    public  static void main(String []args){
        QuicklyWay way = new QuicklyWay();
        System.out.println(way.age);
//        AtomicInteger
    }
}
