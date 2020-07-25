package com.jj.practice;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class MergeBST {
    private Node root;
    private class Node {
        private int data;
        private Node left, right, thread;

        public Node(int val) {
            this.data = val;
            left = right = thread = null;
        }
    }
    // Stack Implement
    public static ArrayList<Integer> merge1(Node root1, Node root2)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayDeque<Node> st1 = new ArrayDeque<>(), st2 = new ArrayDeque<>();
        push(root1, st1);
        push(root2, st2);
        while(!st1.isEmpty() || !st2.isEmpty()) {
            int cmp;
            if      (st1.isEmpty()) cmp = 1;
            else if (st2.isEmpty()) cmp = -1;
            else                    cmp = st1.peek().data - st2.peek().data;

            if(cmp < 0) {
                ans.add(st1.peek().data);
                push(st1.removeFirst().right, st1);
            }else {
                ans.add(st2.peek().data);
                push(st2.removeFirst().right, st2);
            }
        }
        return ans;
    }
    private static void push(Node x, ArrayDeque<Node> st) {
        while(x != null) {
            st.addFirst(x);
            x = x.left;
        }
    }

    // Morris Traversal
    public static ArrayList<Integer> merge2(Node root1, Node root2)
    {
        ArrayList<Integer> ans = new ArrayList<>();
        Node curr1 = next(root1), curr2 = next(root2);
        while(curr1 != null && curr2 != null) {
            int cmp = curr1.data - curr2.data;
            if(cmp < 0) {
                ans.add(curr1.data);
                curr1 = next(curr1.right);
            }else {
                ans.add(curr2.data);
                curr2 = next(curr2.right);
            }
        }
        curr1 = (curr1 != null) ? curr1 : curr2;
        while(curr1 != null) {
            ans.add(curr1.data);
            curr1 = next(curr1.right);
        }
        return ans;
    }
    private static Node next(Node x) {
        while(x != null && x.left != null) {
            Node t = x.left;
            while(t.right != null && t.right != x) t = t.right;
            if(t.right != null) {
                t.right = null;
                break;
            }
            t.right = x;
            x = x.left;
        }
        return x;
    }

    public static void main(String[] args) {

    }
}
