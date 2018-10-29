package com.rocketball.alg.list;

import com.rocketball.alg.struct.ListNode;

public class DelDuplicateList {
    public static void main(String[] args) {
        ListNode la1 = new ListNode(1);
        ListNode la2 = new ListNode(1);
        ListNode la3 = new ListNode(1);
        ListNode la4 = new ListNode(2);
        ListNode la5 = new ListNode(2);
        ListNode la6 = new ListNode(2);
        la1.next = la2;
        la2.next = la3;
        la3.next = la4;
        la4.next = la5;
        la5.next = la6;
        ListNode last = deleteDuplicates(la1);
        ListUtils.printList(last);
    }


    public static ListNode deleteDuplicates(ListNode head) {
        ListNode pPre = head;//将链表的第一个节点赋给pPre
        ListNode pCur;
        while (pPre != null) {
            pCur = pPre.next;
            if (pCur != null && pCur.val == pPre.val)
            {
                pPre.next = pCur.next;
            }else{
                pPre = pCur;
            }
        }
        return head;
    }

}
