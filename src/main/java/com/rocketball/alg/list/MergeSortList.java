package com.rocketball.alg.list;

public class MergeSortList {

    public static void main(String[] args) {
        ListNode la1 = new ListNode(19);
        ListNode la2 = new ListNode(111);
        ListNode la3 = new ListNode(113);
        la1.next = la2;
        la2.next = la3;
        ListNode lb1 = new ListNode(1110);
        ListNode lb2 = new ListNode(1112);
        ListNode lb3 = new ListNode(1115);
        lb1.next = lb2;
        lb2.next = lb3;
//        ListNode last = mergeTwoLists(lb1, la1);
        ListNode last = mergeRecur(lb1, la1);

    }


    public static ListNode mergeRecur(ListNode l1,ListNode l2){
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        ListNode list0= l1.val>l2.val?l2:l1;
        if(l1.val<l2.val){
            list0.next = mergeRecur(l1.next,l2);
        }else{
            list0.next = mergeRecur(l1,l2.next);
        }
        return list0;
    }


    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode t1 = l1;
        ListNode t2 = l2;
        ListNode head = new ListNode(0);
        ListNode headptr = head;

        while (t1 != null && t2 != null) {
            if(t1.val<= t2.val){
                head.next = t1;
                head = head.next;
                t1=head.next;
            }else{
                head.next = t2;
                head  = head.next;
                t2=head.next;
            }
            if(t1==null){
                head.next=t2;
                break;
            }
            if(t2==null){
                head.next=t1;
                break;
            }
        }
        return headptr;

    }


    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
        }
    }
}


