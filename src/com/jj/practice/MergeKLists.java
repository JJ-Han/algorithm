package com.jj.practice;

// https://leetcode.com/problems/merge-k-sorted-lists/

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKLists {
    private static class ListNode {
        private int val;
        private ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
        public ListNode(int[] vals) {
            this(vals[0]);
            ListNode curr = this;
            for(int i = 1; i < vals.length; i++) {
                curr.next = new ListNode(vals[i]);
                curr= curr.next;
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

    private static class NodeComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode l1, ListNode l2) {
            if      (l1.val < l2.val)   return -1;
            else if (l1.val > l2.val)   return 1;
            else                        return 0;
        }
    }

    // test client
    public static void main(String[] args) {
        int[] test1 = {1, 2, 3, 4, 5};
        int[] test2 = {-1, 3, 4, 4, 10};
        int[] test3 = {-9, -8, -4, 0, 20};
        ListNode[] test = {new ListNode(test1), new ListNode(test2), new ListNode(test3)};
        System.out.println(isSorted(mergeKLists(test)));
    }

    // method to merge all the lists in array
    public static ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)   return null;
        return mergeKLists(lists, lists.length);
    }
    private static ListNode mergeKLists(ListNode[] lists, int n) {
        if(n == 1)  return lists[0];
        for(int i = 0; i*2 < n; i++)
            lists[i] = mergeLists(lists[i*2], i*2+1 < n ? lists[i*2+1] : null);
        return mergeKLists(lists, ++n/2);
    }

    // method to merge two lists
    private static ListNode mergeLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode dummy = new ListNode(0), curr = dummy;
        while(l1 != null || l2 != null) {
            if(l1 == null) {
                curr.next = l2;
                l2 = l2.next;
            }else if(l2 == null) {
                curr.next = l1;
                l1 = l1.next;
            }else
            if(l1.val > l2.val) {
                curr.next = l2;
                l2 = l2.next;
            }else {
                curr.next = l1;
                l1 = l1.next;
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    // method to merge lists using Priority Queue
    public static ListNode mergeKListsPQ(ListNode[] lists) {
         if(lists.length == 0)   return null;
         if(lists.length == 1)   return lists[0];
         ListNode dummy = new ListNode(0), curr = dummy;
         PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (a, b) -> a.val - b.val);    // w. lambda
//         PriorityQueue<ListNode> queue = new PriorityQueue(lists.length, new NodeComparator());       // w. Comparator
//         recommended by IntelliJ
//         PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
         for(ListNode l : lists)
             if(l != null) queue.offer(l);

         while(!queue.isEmpty()) {
             curr.next = queue.poll();
             curr = curr.next;
             if(curr.next != null) queue.offer(curr.next);
         }
         return dummy.next;
    }



    // utility method if the list is sorted
    public static boolean isSorted(ListNode node) {
        while(node.next != null) {
            if(node.val > node.next.val)    return false;
            node = node.next;
        }
        return true;
    }
}


