package com.jj.practice;

// https://leetcode.com/problems/add-two-numbers/
// https://www.geeksforgeeks.org/add-two-numbers-represented-by-linked-lists/

public class ListAdd {
    private static class Node {
        private int data;
        private Node next;
        public Node() {
            data = 0;
        }
        public Node(int data) {
            this.data = data;
        }
        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
        public String toString() {
            StringBuilder sb = new StringBuilder();
            Node curr = this;
            while(curr != null) {
                sb.append(curr.data).append(" ");
                curr = curr.next;
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        Node l1 = new Node(1);
        Node l2 = new Node(9);
        l2.next = new Node(9);
        System.out.println(addList(l1, l2));
    }

    public static Node addList(Node l1, Node l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        Node ans = new Node(), curr = ans;
        int sum = 0;
        while(l1 != null || l2 != null) {
            sum /= 10;
            if(l1 != null) {
                sum += l1.data;
                l1 = l1.next;
            }
            if(l2 != null) {
                sum += l2.data;
                l2 = l2.next;
            }
            curr.next = new Node(sum % 10);
            curr = curr.next;
        }
        if(sum >= 10)   curr.next = new Node(1);
        return ans.next;
    }
}
