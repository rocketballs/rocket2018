package com.rocketball.alg.tree;

import java.util.Arrays;

public class ListTree {

    public static void main(String[] args) {
        Tree t1 = new Tree(1);
        Tree t2 = new Tree(2);
        Tree t3 = new Tree(3);
        Tree t4 = new Tree(4);
        Tree t5 = new Tree(5);
        Tree t6 = new Tree(6);
        Tree t7 = new Tree(7);
        Tree t8 = new Tree(8);
        Tree t9 = new Tree(9);
        Tree t10 = new Tree(10);
        Tree t11 = new Tree(11);
        Tree t12 = new Tree(12);
        Tree t13 = new Tree(13);
        Tree t14 = new Tree(14);
        Tree t15 = new Tree(15);
        t1.setLeft(t2);
        t1.setRight(t3);
        t2.setRight(t4);
        t3.setRight(t5);
        t3.setLeft(t6);
        t4.setLeft(t7);
        t7.setRight(t8);
        t7.setLeft(t9);
        t9.setRight(t10);
        t8.setRight(t11);
        t11.setLeft(t12);
        t12.setLeft(t13);
        t13.setRight(t14);
        t10.setRight(t15);


    }

    private void deepList() {

    }

    private void widthList() {

    }


}
