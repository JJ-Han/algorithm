package com.jj.practice;

// https://leetcode.com/problems/reverse-nodes-in-k-group/

public class ReverseKGroup {
    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int[] vals) {
            this(vals[0]);
            ListNode curr = this;
            for (int i = 1; i < vals.length; i++) {
                curr.next = new ListNode(vals[i]);
                curr = curr.next;
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            ListNode curr = this;
            while (curr != null) {
                sb.append(curr.val).append(" ");
                curr = curr.next;
            }
            return sb.toString();
        }
    }

    // reverse the nodes of a linked list k at a time and return its modified list
    //  If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is
    public static ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || head.next == null || k <= 1) return head;
        ListNode dummy = new ListNode(0), curr = dummy;
        dummy.next = head;
        while(true) {
            ListNode tail = findNextK(curr, k);
            if(tail == null) break;
            ListNode t = curr.next;
            curr.next = reverseList(curr.next, tail.next);
            curr = t;
        }
        return dummy.next;
    }
    private static ListNode findNextK(ListNode head, int k) {
        while(k-- > 0 && head != null)
            head = head.next;
        return head;
    }
    private static ListNode reverseList(ListNode head, ListNode tail) {
        ListNode prev = tail, curr = head;
        while(curr != tail) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // test client
    public static void main(String[] args) {
        ListNode test = new ListNode(new int[] {1, 2, 3, 4, 5});
        ListNode test2 = new ListNode(new int[] {1, 2, 3, 4, 5});
        ListNode test3 = new ListNode(new int[] {1, 2, 3, 4, 5, 6});
        System.out.println(reverseKGroup(test, 2));             // 2 1 4 3 5
        System.out.println(reverseKGroup(test2, 3));            // 3 2 1 4 5
        System.out.println(reverseKGroup(test3, 3));            // 3 2 1 6 5 4
    }
}
