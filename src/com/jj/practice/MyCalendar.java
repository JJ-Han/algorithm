package com.jj.practice;

// https://leetcode.com/problems/my-calendar-i/
// using Red-Black BST, implemented booking system which does not allow conflict double booking

class MyCalendar {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    private boolean updated;
    private class Node {
        private final int start, end;
        private Node left, right;
        private boolean color;

        public Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.color = RED;
        }
    }
    public MyCalendar() {

    }

    public boolean book(int start, int end) {
        return add(start, end);
    }

    private boolean add(int start, int end) {
        updated = false;
        root = add(root, start, end);
        return updated;
    }

    private Node add(Node h, int start, int end) {
        if (h == null) {
            updated = true;
            return new Node(start, end);
        }

        if (start >= h.end) h.right = add(h.right, start, end);
        else if (end <= h.start) h.left = add(h.left, start, end);

        if (updated) {
            if (!isRed(h.left) && isRed(h.right)) h = rotateLeft(h);
            if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
            if (isRed(h.left) && isRed(h.right)) flip(h);
        }
        return h;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flip(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }
}

