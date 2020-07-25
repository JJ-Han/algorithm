package com.jj.practice;

// https://practice.geeksforgeeks.org/problems/find-a-pair-with-given-target-in-bst/1
// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
// https://leetcode.com/problems/two-sum-iv-input-is-a-bst/discuss/106061/Java-Simple-AC-with-Time-O(n)-Space-O(log-n)-in-Average

import java.util.ArrayDeque;

public class SumPairBST {
    private class Node{
        private int data;
        private Node left, right;
        public Node(int x) {
            data = x;
            left = right = null;
        }
    }

    public boolean isPairPresent(Node root, int target)
    {
        if(root == null || (root.left == null && root.right == null)) return false;
        ArrayDeque<Node> deque = new ArrayDeque<>();
        initDeque(deque, root);
        while(deque.peekFirst() != deque.peekLast()) {
            int sum = deque.peekFirst().data + deque.peekLast().data;
            if      (sum < target)   nextLo(deque);
            else if (sum > target)   nextHi(deque);
            else   return true;
        }
        return false;
    }

    private void initDeque(ArrayDeque<Node> deque, Node root) {
        Node curr = root;
        while(curr != null) {
            deque.addFirst(curr);
            curr = curr.left;
        }
        curr = root.right;
        while(curr != null) {
            deque.addLast(curr);
            curr = curr.right;
        }
    }
    private void nextLo(ArrayDeque<Node> deque) {
        Node x = deque.removeFirst().right;
        if(x != null) {
            if(x == deque.peekFirst())   x = x.left;
            while(x != null) {
                deque.addFirst(x);
                x = x.left;
            }
        }
    }
    private void nextHi(ArrayDeque<Node> deque) {
        Node x = deque.removeLast().left;
        if(x != null) {
            if(x == deque.peekLast())   x = x.right;
            while(x != null) {
                deque.addLast(x);
                x = x.right;
            }
        }
    }
}
