package com.jj.practice;

// https://www.cl.cam.ac.uk/teaching/2005/Algorithms/skiplists.pdf
// https://docs.microsoft.com/en-us/previous-versions/ms379573(v=vs.80)?redirectedfrom=MSDN
// TODO: make this generic as Comparable
// TODO: implement Iterable and visualize() to show the entire layers of data structure
// TODO: implement constructor with random seed

import java.util.Random;

class SkipList {
    private static final int INIT_CAPACITY = 8;
    private final int MIN_BOUNDARY;     // lower boundary of headCapacity
    private final int MAX_LEVEL = 140;  // 2 ^ h = # of elements, where h is optimal maximum level, when P = 0.5
    private final double P = 0.5;
    private final Node head;
    private int headCapacity;
    private int headLevel = 0;

    private class Node {
        private final int val;
        private Node[] next;

        private Node(int val, int level) {
            this.val = val;
            next = new Node[level];
        }
    }

    public SkipList() {
        this(INIT_CAPACITY);
    }

    public SkipList(int size) {
        if (size == 0) throw new IllegalArgumentException("size should be greater than 0");
        MIN_BOUNDARY = size/2;
        headCapacity = size;
        head = new Node(0, size);
    }

    public boolean search(int target) {
        Node curr = head;
        for (int i = headLevel-1; i >= 0; i--) {
            while (curr.next[i] != null) {
                int cmp = target - curr.next[i].val;
                if (cmp < 0) break;
                else if (cmp > 0) curr = curr.next[i];
                else return true;
            }
        }
        return false;
    }

    public int getLevel() {
        return headLevel;
    }

    public void add(int num) {
        Node[] update = new Node[headLevel + 1];
        update[headLevel] = head;
        buildUpdate(num, update);

        int level = getRandomLevel();
        if (level > headLevel) {
            if (headLevel == headCapacity) resizeHead(2 * headCapacity);
            headLevel++;
        }

        Node x = new Node(num, level);
        for (int i = 0; i < level; i++) {
            Node n = update[i].next[i];
            update[i].next[i] = x;
            x.next[i] = n;
        }
    }

    public boolean erase(int num) {
        if (headLevel == 0) return false;
        Node[] update = new Node[headLevel];
        buildUpdate(num, update);
        if (update[0].next[0] == null || update[0].next[0].val != num) return false;

        for (int i = 0; i < headLevel; i++) {
            if (update[i].next[i] == null || update[i].next[i].val != num) break;
            update[i].next[i] = update[i].next[i].next[i];
        }

        if (head.next[headLevel-1] == null && --headLevel >= MIN_BOUNDARY && headLevel == headCapacity/4) resizeHead(headCapacity/2);
        return true;
    }

    private void buildUpdate(int x, Node[] update) {
        Node curr = head;
        for (int i = headLevel - 1; i >= 0; i--) {
            while (curr.next[i] != null && curr.next[i].val < x)
                curr = curr.next[i];
            update[i] = curr;
        }
    }

    // Fixed the dice implement: generate a random level that is more than one greater than the current level
    private int getRandomLevel() {
        int level = 1, limit = Math.min(MAX_LEVEL, headLevel + 1);
        while (Math.random() < P && level < limit) level++;
        return level;
    }

    // resize head level
    private void resizeHead(int size) {
        Node[] copy = new Node[size];
        System.arraycopy(head.next, 0, copy, 0, headLevel);
        head.next = copy;
        headCapacity = size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (head.next[0] == null) return sb.toString();
        sb.append("[");
        for (Node curr = head.next[0]; curr != null; curr = curr.next[0])
            sb.append(" ").append(curr.val).append(",");
        sb.setCharAt(sb.length()-1, ']');
        return sb.toString();
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        Random rand = new Random();
        for (int i = 0; i < 100; i++) {
            skipList.add(rand.nextInt(200));
            System.out.println(skipList.getLevel());
        }
        System.out.println(skipList);
    }
}
