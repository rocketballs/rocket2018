package com.rocketball.java.jdkproxy;

public class TargetImp implements  Target {


    @Override
    public int test(int i) {
        return i + 3;
    }
}
