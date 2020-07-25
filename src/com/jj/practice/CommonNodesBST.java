package com.jj.practice;

// https://practice.geeksforgeeks.org/problems/print-common-nodes-in-bst/1
// https://www.geeksforgeeks.org/print-common-nodes-in-two-binary-search-trees/

import java.util.ArrayList;
import java.util.Stack;

public class CommonNodesBST {
    private class Node {
        private int data;
        private Node left = null, right = null;
        public Node(int x) {
            data = x;
        }
    }

    public static void main(String[] args) {

    }

    public static ArrayList<Integer> printCommon(Node root1, Node root2) {
        if (root1 == null || root2 == null)  return null;
        ArrayList<Integer> al = new ArrayList<>();
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        while (true) {
            if (root1 != null) {
                do {
                    s1.push(root1);
                    root1 = root1.left;
                }while (root1 != null);
            }
            if (root2 != null) {
                do {
                    s2.push(root2);
                    root2 = root2.left;
                }while (root2 != null);
            }
            if (!s1.isEmpty() && !s2.isEmpty()) {
                root1 = s1.peek();
                root2 = s2.peek();
                if (root1.data == root2.data) {
                    al.add(root1.data);
                    s1.pop();
                    s2.pop();
                    root1 = root1.right;
                    root2 = root2.right;
                }
                else if (root1.data < root2.data) {
                    s1.pop();
                    root1 = root1.right;
                    root2 = null;
                }
                else {
                    s2.pop();
                    root1 = null;
                    root2 = root2.right;
                }
            }
            else    break;
        }
        return al;
    }
}
