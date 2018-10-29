package com.rocketball.alg.list;

import com.rocketball.alg.struct.ListNode;

public class ListUtils {

    public static void  printList(ListNode last){
        while (true) {
            System.out.println(last.val);
            if (last.next == null) {
                return;
            }
            last = last.next;
        }
    }
}
