package com.jj.practice;

import java.lang.reflect.Array;
import java.util.ArrayDeque;

public class BSTwDuplicate {
    private Node root;
    private class Node {
        private int val;
        private Node left, right, thread;

        public Node(int val) {
            this.val = val;
            left = right = thread = null;
        }
    }
    public BSTwDuplicate() {}
    public BSTwDuplicate(int[] a) {
        root = arrToBST(a, 0, a.length-1);
    }
    private Node arrToBST(int[] a, int lo, int hi) {
        Node temp = null;
        if(lo <= hi) {
            int mid = lo + (hi-lo)/2;
            temp = new Node(a[mid]);
            temp.left = arrToBST(a, lo, mid-1);
            temp.right = arrToBST(a, mid+1, hi);
        }
        return temp;
    }

    public void add(int val) {
        root = add(root, val);
    }
    private Node add(Node x, int val) {
        if(x == null)   return new Node(val);
        if(x.val >= val)    x.left = add(x.left, val);
        else                x.right = add(x.right, val);
        return x;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        inOrder(root, sb);
        return sb.toString();
    }
    private void inOrder(Node root, StringBuilder sb) {
        if(root != null) {
            inOrder(root.left, sb);
            sb.append(root.val).append(" ");
            inOrder(root.right, sb);
        }
    }
    private void levelOrder() {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        queue.add(root);
        while(!queue.isEmpty()) {
            Node x = queue.remove();
            //if (x == null)  continue;
            sb.append(x.val).append(" ");
            if(x.left != null) queue.add(x.left);
            if(x.right != null) queue.add(x.right);
        }
        System.out.println(sb.toString());
    }

    // https://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/
    public void enableThread() {            // Morris traversal
        Node curr = root;
        while(curr != null) {
            if(curr.left == null)   curr = curr.thread;
            else {
                Node prev = curr.left;
                while((prev.right != null || prev.thread != null) && prev.thread != curr) prev = prev.right;
                if(prev.thread == null) {
                    prev.thread = curr;
                    curr = curr.left;
                }else {
                    curr = curr.right != null ? curr.right : curr.thread;
                }
            }
        }
    }
    public void inOrderMorris() {
        boolean toThread = true;
        Node curr = root;
        StringBuilder sb = new StringBuilder();
        while(curr != null) {
            if(toThread)    while (curr.left != null) curr = curr.left;
            sb.append(curr.val).append(" ");
            if(curr.thread != null) {
                curr = curr.thread;
                toThread = false;
            }
            else {
                curr = curr.right;
                toThread = true;
            }
        }
        System.out.println(sb.toString());
    }

    public boolean isThreadValid() {
        boolean toThread = true;
        Node currT = root, currR = root;
        ArrayDeque<Node> st = new ArrayDeque<>();
        while((currR != null  || !st.isEmpty()) && currT != null) {
            while(currR != null) {
                st.push(currR);
                currR = currR.left;
            }
            currR = st.pop();
            if(toThread)    while (currT.left != null) currT = currT.left;
            if(currR != currT)  return false;
            currR = currR.right;
            if(currT.thread != null) {
                currT = currT.thread;
                toThread = false;
            }
            else {
                currT = currT.right;
                toThread = true;
            }
        }
        return st.isEmpty();
    }

    public int height() {
        return height(root);
    }
    private int height(Node x) {
        if(x == null)   return 0;
        return 1 + Math.max(height(x.left), height(x.right));
    }

    // check if it is valid BST which has duplicates
    public boolean isBST() {
        ArrayDeque<Node> st = new ArrayDeque<>();
        Node prev = null;
        boolean isLeft = true;
        Node curr = root;
        while(curr != null || !st.isEmpty()) {
            while(curr != null) {
                st.push(curr);
                curr = curr.left;
            }
            curr = st.pop();
            if(prev != null) {
                if      (isLeft && prev.val > curr.val)     return false;
                else if (!isLeft && prev.val >= curr.val)   return false;
            }
            prev = curr;
            curr = curr.right;
            isLeft = curr == null;
        }
        return true;
    }

    public static void main(String[] args) {
        BSTwDuplicate bst = new BSTwDuplicate();

        bst.add(10);
        bst.add(5);
        bst.add(20);
        bst.add(10);
        bst.add(10);
        bst.add(10);
        bst.add(20);
        System.out.println(bst);
        System.out.println(bst.height());
        bst.levelOrder();

        System.out.println(bst.isBST());
//        bst.enableThread();
//        bst.inOrderMorris();
//        System.out.println(bst.isThreadValid());
    }
}
