package com.jj.practice;

import java.util.*;

public class BuildTree {
    static private class Node {
        private int data;
        Node left = null, right = null;
        Node(int x) {
            data = x;
        }
    }
    public static void main(String[] args) {
        int[] in =      {3, 1, 4, 0, 5, 2};
        int[] pre =     {0, 1, 3, 4, 2, 5};
        int[] post =    {3, 4, 1, 5, 2, 0};

        int[] in2 =     {4, 8, 2, 5, 1, 6, 3, 7};
        int[] post2 =   {8, 4, 5, 2, 6, 7, 3, 1};
        int[] pre2 =    {1, 2, 4, 8, 5, 3, 6, 7};

        // BST TC
        int[] postBST1 = {1, 7, 5, 50, 40, 10};                         // inorder 1 5 7 10 40 50
        int[] postBST2 = {216, 823, 476, 429, 850, 93, 18, 975, 862};   // inorder 18 93 216 429 476 823 850 862 975
        int[] postBST3 = {7, 12, 10, 18, 24, 22, 15, 31, 44, 35, 66, 90, 70, 50, 25};

        int[] preBST1 = {40, 30, 35, 80, 100};                          // post 35 30 100 80 40
        int[] preBST2 = {40, 30, 32, 35, 80, 90, 100, 120};             // post 35 32 30 120 100 90 80 40
        int[] preBST3 = {261, 142, 106, 41, 114, 121, 232, 157, 149, 178, 931, 867, 653, 328, 304, 345, 428, 657, 752, 766, 799, 918, 940}; // leaf 41 121 149 178 304 428 799 918 940
        int[] preBST4 = {419, 371, 126, 63, 357, 324, 222, 901, 898, 617, 555, 549, 547, 831, 764, 622, 891};                               // leaf 63 222 547 622 891
        //System.out.println(printPreOrder(buildInPost(in2, post2)));
        printLeaf(preBST3);
        printLeaf(preBST4);
        //printPostfromPre(preBST2);
        //System.out.println(isPreOrder(preBST2));

    }

    // https://practice.geeksforgeeks.org/problems/construct-tree-1/1
    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
    // https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/discuss/34555/The-iterative-solution-is-easier-than-you-think!
    static public Node buildPreIn(int[] preorder, int[] inorder) {
        if(preorder.length != inorder.length || preorder.length == 0)   return null;

        Node root = new Node(preorder[0]), curr = root;
        Stack<Node> st = new Stack<>();
        for(int i = 1, j = 0; i < preorder.length; i++){
            if (curr.data != inorder[j]) {
                curr.left = new Node(preorder[i]);
                st.push(curr);
                curr = curr.left;
            }
            else {
                j++;
                while(!st.isEmpty() && st.peek().data == inorder[j]) {
                    curr = st.pop();
                    j++;
                }
                curr = curr.right = new Node(preorder[i]);
            }
        }
        return root;
    }

    // https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    // https://practice.geeksforgeeks.org/problems/tree-from-postorder-and-inorder/1
    // https://www.geeksforgeeks.org/construct-a-binary-tree-from-postorder-and-inorder/
    public static Node buildInPost(int[] inorder, int[] postorder) {
        int n = postorder.length;
        if (inorder.length != n || n == 0) return null;

        Node root = new Node(postorder[n-1]), curr = root;
        Stack<Node> st = new Stack<>();
        for (int i = n-2, j = n-1; i >= 0; i--) {
            if(curr.data != inorder[j]) {
                curr.right = new Node(postorder[i]);
                st.push(curr);
                curr = curr.right;
            }else {
                j--;
                while(!st.isEmpty() && st.peek().data == inorder[j]) {
                    curr = st.pop();
                    j--;
                }
                curr = curr.left = new Node(postorder[i]);
            }
        }
        return root;
    }

    // https://www.geeksforgeeks.org/construct-a-bst-from-given-postorder-traversal-using-stack/
    // https://practice.geeksforgeeks.org/problems/construct-bst-from-post-order/1
    // https://www.geeksforgeeks.org/construct-a-binary-search-tree-from-given-postorder/
    public static Node buildBSTfromPost(int[] postorder)
    {
        if (postorder.length == 0) return null;
        Stack<Node> st = new Stack<>();
        Node root = new Node(postorder[postorder.length-1]), curr = root;
        for (int i = postorder.length-2; i >= 0; i--) {
            if (postorder[i] > curr.data) {
                curr.right = new Node(postorder[i]);
                st.push(curr);
                curr = curr.right;
            }else {
                while (!st.isEmpty() && postorder[i] < st.peek().data) curr = st.pop();
                curr = curr.left = new Node(postorder[i]);
            }
        }
        return root;
    }
    public static Node buildBSTfromPost2 (int[] postorder) {     // slower than the first one 6155 vs 10622 ms
        if (postorder.length == 0) return null;
        Stack<Node> st = new Stack<>();

        Node root = new Node(postorder[postorder.length-1]);
        st.push(root);
        for (int i = postorder.length-2; i >= 0; i--) {
            Node x = new Node(postorder[i]);
            Node parent = null;
            while (!st.isEmpty() && postorder[i] < st.peek().data)   parent = st.pop();
            if (parent != null)     parent.left = x;
            else                    st.peek().right = x;
            st.push(x);
        }
        return root;
    }

    // https://practice.geeksforgeeks.org/problems/preorder-to-postorder/0
    // https://www.geeksforgeeks.org/find-postorder-traversal-of-bst-from-preorder-traversal/
    private static int IDX;
    public static void printPostfromPre(int[] pre) {
        if(pre.length == 0 || !isPreOrder(pre)) throw new IllegalArgumentException();
        IDX = 0;
        StringBuilder sb = new StringBuilder();
        preToPost(pre, null, null, sb);
        System.out.println(sb.toString());
    }
    private static void preToPost(int[] pre, Integer min, Integer max, StringBuilder sb) {
        if(IDX == pre.length || (min != null && pre[IDX] < min) || (max != null && pre[IDX] > max)) return;
        int x = pre[IDX++];
        preToPost(pre, min, x, sb);
        preToPost(pre, x, max, sb);
        sb.append(x).append(" ");
    }

    // https://practice.geeksforgeeks.org/problems/preorder-traversal-and-bst/0
    // https://www.geeksforgeeks.org/check-if-a-given-array-can-represent-preorder-traversal-of-binary-search-tree/
    public static boolean isPreOrder(int[] pre) {
        ArrayDeque<Integer> st = new ArrayDeque<>(pre.length);
        Integer root = null;
        for(int i = 0; i < pre.length; i++) {
            if(root != null && pre[i] < root)   return false;
            while(!st.isEmpty() && st.peek() < pre[i])    root = st.removeFirst();
            st.addFirst(pre[i]);
        }
        return true;
    }

    // https://practice.geeksforgeeks.org/problems/print-leaf-nodes-from-preorder-traversal-of-bst/0
    public static void printLeaf(int[] pre) {
        int n = pre.length;
        if(n == 1) System.out.println(pre[0]);
        ArrayDeque<Integer> st = new ArrayDeque<>(n);
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < n; i++) {
            if(pre[i-1] > pre[i])   st.addLast(pre[i-1]);
            else if(!st.isEmpty() && pre[i] > st.peekLast()) {
                sb.append(pre[i-1]).append(" ");
                while(!st.isEmpty() &&  pre[i] > st.peekLast())    st.removeLast();
            }
        }
        System.out.println(sb.toString() + pre[n-1]);
    }
    public static void printLeafRec(int[] pre) {
        if(pre.length == 0) return;
        StringBuilder sb = new StringBuilder();
        printLeafRec(pre, 0, pre.length-1, sb);
        System.out.println(sb.toString());
    }
    private static void printLeafRec(int[] pre, int lo, int hi, StringBuilder sb) {
        if(lo == hi)    sb.append(pre[lo]).append(" ");
        else if(lo < hi) {
            int i = lo + 1;
            while (i <= hi && pre[lo] > pre[i]) i++;
            printLeafRec(pre, ++lo, i - 1, sb);
            printLeafRec(pre, i, hi, sb);
        }
    }


    public static String printPreOrder(Node root) {
        StringBuilder sb = new StringBuilder();
        PreOrder(root, sb);
        return sb.toString();
    }
    private static void PreOrder(Node root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.data).append(" ");
            PreOrder(root.left, sb);
            PreOrder(root.right, sb);
        }
    }

    public static String printPostOrder(Node root) {
        StringBuilder sb = new StringBuilder();
        postOrder(root, sb);
        return sb.toString();
    }
    private static void postOrder(Node root, StringBuilder sb) {
        if (root != null) {
            postOrder(root.left, sb);
            postOrder(root.right, sb);
            sb.append(root.data).append(" ");
        }
    }

    public static void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static void shuffle(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int r = (int) (Math.random() * (i+1));
            exch(a, i, r);
        }
    }
}