package com.rocketball.alg.list;

import com.rocketball.alg.struct.ListNode;

//将链表每个节点向右移动 k 个位置
public class RotateRightList {
    public static ListNode rotateRight(ListNode head, int k) {
        if (k <= 0 || head==null || head.next==null)
            return head;
        ListNode start = head;
        ListNode last = head;
        int size = 1;
        while (true) {
            if (head.next == null) {
                head.next = start;
                break;
            }
            size++;
            head = head.next;
        }
        k = size - k % size;
        System.out.println("k:" + k);
        for (int i = 1; i <= k; i++) {
            if (i == k) {
                last = start.next;
                start.next = null;
                return last;
            }
            start = start.next;
        }
        return last;
    }

    public static void main(String[] args) {
        ListNode la1 = new ListNode(1);
        ListNode la2 = new ListNode(2);
        ListNode la3 = new ListNode(3);
        ListNode la4 = new ListNode(4);
        ListNode la5 = new ListNode(5);
        la1.next = la2;
        la2.next = la3;
        la3.next = la4;
        la4.next = la5;
        ListNode last = rotateRight(la1, 6);
        ListUtils.printList(last);
    }
}
