package com.rocketball.alg.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortList {
    public static void main(String[] args) {
        List<Integer> lists = new ArrayList<>();
        lists.add(7);
        lists.add(6);
        lists.add(9);
        lists.add(10);
        lists.add(5);
        lists.add(7);
        lists.add(4);
        lists.add(4);
        lists.add(3);
        lists.add(9);
//        Arrays.sort();
        Integer [] values = (Integer[]) lists.toArray();
    }

    private void quickSort(Integer [] value,int left,int right){
        if(left==right)
            return;
        int base = value[left];




    }
}
