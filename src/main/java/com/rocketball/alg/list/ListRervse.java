package com.rocketball.alg.list;

import java.util.ArrayList;
import java.util.List;

public class ListRervse {


    public static void main(String[] args) {
        Node start =null;
        Node node1 =new Node(1);
        Node node2 =new Node(2);
        Node node3 =new Node(3);
        Node node4 =new Node(4);
        Node node5 =new Node(5);
        start = node1;
        node1.next=node2;
        node2.next=node3;
        node3.next=node4;
        node4.next=node5;



        start = reverseLinkedList(start);
        while (true){
            System.out.println(start.value);
            if(start.next==null) {
                break;
            }
            start = start.next;
        }

    }

    static Node reverseLinkedList(Node node) {
        if (node == null || node.next == null) {
            return node;
        } else {
            Node headNode = reverseLinkedList(node.next);
            node.next.next = node;
            node.next = null;
            return headNode;
        }
    }


}

class Node {
    int value;
    Node next;

    public Node(int value) {
        this.value = value;
    }

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Node() {

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}