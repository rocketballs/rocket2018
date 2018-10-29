package com.rocketball.alg.list;

import com.rocketball.alg.struct.ListNode;

public class RemoveNthList {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int listSize =0 ;
        ListNode sizeNode = head;
        while (sizeNode!=null){
            listSize++;
            sizeNode = sizeNode.next;
        }
        System.out.println("size:"+listSize);
        n=listSize-n+1;

        ListNode pre = head;
        ListNode pcur = head;
        if(n==1)
            return head.next;
        for (int i = 1; i <= n && pre.next != null; i++) {
            if (i == n) {
                pre.next = pcur.next;
                break;
            }
            pre = pcur;
            pcur = pcur.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode la1 = new ListNode(1);
        ListNode la2 = new ListNode(2);
        ListNode la3 = new ListNode(3);
        ListNode la4 = new ListNode(4);
        ListNode la5 = new ListNode(5);
        ListNode la6 = new ListNode(6);
        la1.next = la2;
        la2.next = la3;
        la3.next = la4;
        la4.next = la5;
        la5.next = la6;
        ListNode last = removeNthFromEnd(la1, 1);
        ListUtils.printList(last);
         last = removeNthFromEnd(la1, 5);
        ListUtils.printList(last);
    }
}
