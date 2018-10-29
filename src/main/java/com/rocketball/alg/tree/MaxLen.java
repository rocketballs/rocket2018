package com.rocketball.alg.tree;

import java.util.*;

//二次递归

public class MaxLen {
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
        MaxLen maxLen = new MaxLen();
        maxLen.listTree(t1);
//        System.out.println(maxLen.listTree(t1).getValue());


    }

    //    public int maxLen(Tree start){
//
//        start.getLeft()!=null
//
//    }
    public Tree listTree(Tree start) {
//        System.out.println(start.getValue());//遍历所有的点

        int left = 0;
        List<Integer> ln = new ArrayList<>();
        ln.add(0);
        int right =0 ;
        List<Integer> rn = new ArrayList<>();
        rn.add(0);
        if(start.getLeft()!=null){
            listLen(start.getLeft(),0,ln);
            Collections.sort(ln);
            Collections.reverse(ln);
            left=ln.stream().findFirst().get();
        }
        if(start.getRight()!=null){
             listLen(start.getRight(),0,rn);
             Collections.sort(rn);
             Collections.reverse(rn);
             right=rn.stream().findFirst().get();
        }
        System.out.println(start.getValue()+","+(left+right)+","+left+","+right);

        if (start.getLeft() == null && start.getRight() == null) {
            return start;
        }
        if (start.getLeft() != null)
            listTree(start.getLeft());
        if (start.getRight() != null)
            listTree(start.getRight());
        return start;
    }

    public int listLen(Tree tree,int  num,List<Integer> values){
        values.add(num);
        if(tree.getRight()==null && tree.getLeft()==null)
            return num;
        if(tree.getRight()!=null)
            listLen(tree.getRight(),num+1,values);
        if(tree.getLeft()!=null)
            listLen(tree.getLeft(),num+1,values);
        return num;
    }
}



class Tree {
    private Tree left;
    private Tree right;
    private int value;
    public Tree(int value) {
        this.value = value;
    }

    public Tree getLeft() {
        return left;
    }

    public void setLeft(Tree left) {
        this.left = left;
    }

    public Tree getRight() {
        return right;
    }

    public void setRight(Tree right) {
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
