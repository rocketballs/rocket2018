package com.rocketball.alg.stack;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class TopKStack {
    public static void main(String[] args) {
        List<Integer> values = Lists.newArrayList(7,9,1,4,6,22,55,3,7,9,8,14,19,-4);
        int k=7;
        for(int i=0;i<values.size()&&i<7;i++){
            MinStack min = new MinStack();
            for(int j=0;j<values.size();j++){
                min.push(values.get(j));
            }
            int top = min.getMin();
            System.out.println("index:"+i+":"+top);
            for(int j=0;j<values.size();j++){
               if(values.get(j)==top){
                   values.remove(j);
                   break;
               }

            }
        }



    }
}
