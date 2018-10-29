package com.rocketball.alg.list;

import com.rocketball.alg.struct.ListNode;

public class SwapList {

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode f1 = head;
        ListNode sec = head.next;
        f1.next = sec.next;
        sec.next = f1;
        f1.next = swapPairs(sec.next.next);
        return sec;
    }

    public static void main(String[] args) {
        ListNode la1 = new ListNode(1);
        ListNode la2 = new ListNode(2);
        ListNode la3 = new ListNode(3);
        ListNode la4 = new ListNode(4);
        ListNode la5 = new ListNode(5);
//        ListNode la6 = new ListNode(6);
        la1.next = la2;
        la2.next = la3;
        la3.next = la4;
        la4.next = la5;
//        la5.next = la6;
        ListNode last = swapPairs(la1);
        ListUtils.printList(last);
    }
}
