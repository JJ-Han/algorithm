package com.jj.practice;

// https://www.geeksforgeeks.org/huffman-coding-greedy-algo-3/
// https://practice.geeksforgeeks.org/problems/huffman-encoding/0

import java.util.PriorityQueue;

class HuffmanNode implements Comparable<HuffmanNode>{
    private char c;
    private int freq;
    private HuffmanNode left;
    private HuffmanNode right;

    // default constructor
    HuffmanNode(char c, int freq) {
        this.c = c;
        this.freq = freq;
        left = null;
        right = null;
    }

    // encoding constructor
    HuffmanNode(HuffmanNode left, HuffmanNode right) {
        c = '-';
        freq = left.freq + right.freq;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode huffmanNode) {
        int cmp = freq - huffmanNode.freq;
        return cmp != 0 ? cmp : 1;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrder(this, "", sb);
        return sb.toString();
    }
    private void preOrder(HuffmanNode root, String s, StringBuilder sb) {
        if (root.left == null && root.right == null) {
            sb.append(s + " ");
            return;
        }
        preOrder(root.left, s + "0", sb);
        preOrder(root.right, s + "1", sb);
    }

    static String decode(HuffmanNode root, String encodedStr){
        StringBuilder sb = new StringBuilder();
        HuffmanNode curr = root;
        for (int i = 0; i < encodedStr.length(); i++) {
            if (encodedStr.charAt(i) == '0')    curr = curr.left;
            else                                curr = curr.right;
            if(curr.left == null && curr.right == null) {
                sb.append(curr.freq);
                curr = root;
            }
        }
        return sb.toString();
    }
}

public class Huffman {
    public static void main(String[] args) {
        char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] charFreq = { 5, 9, 12, 13, 16, 45 };

        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(6);
        for (int i = 0; i < charArray.length; i++)
            pq.add(new HuffmanNode(charArray[i], charFreq[i]));

        while (pq.size() > 1)
            pq.add(new HuffmanNode(pq.poll(), pq.poll()));

        System.out.println(pq.peek());
    }
}
